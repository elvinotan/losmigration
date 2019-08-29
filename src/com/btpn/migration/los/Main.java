package com.btpn.migration.los;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.btpn.migration.los.constant.IgnoreFile;
import com.btpn.migration.los.mapping.DataJaminan;
import com.btpn.migration.los.mapping.DataUsaha;
import com.btpn.migration.los.mapping.InformasiDebitur;
import com.btpn.migration.los.mapping.Mapping;
import com.btpn.migration.los.mapping.TujuanDanFasilitas;
import com.btpn.migration.los.tool.DateTool;

public class Main extends AbstractMain {
	final static Logger log = Logger.getLogger(Main.class);
	
	public static boolean EXECUTE_INSERT = false;
	
	public void runMapping(File folder) throws Exception {
		// Define all mapping
		List<Mapping> mapping = new ArrayList<Mapping>();
		mapping.add(new InformasiDebitur());
		mapping.add(new TujuanDanFasilitas());
		mapping.add(new DataUsaha());
		mapping.add(new DataJaminan());
		
		// Clear All Data with createdBy = 'MIGRATION' and load data refrence		
		initilize(mapping);
		
		// Process migration
		for (File file: folder.listFiles()) {
			if (file.isFile() && !file.getName().contains("lock")) {
				loadFile(file, mapping);
			}
		}
	}
	
	private List<File> files = new ArrayList<File>();
	
	public void runInitilize(File file) {
		if (file.isDirectory()) {
			for (File f: file.listFiles()) {
				runInitilize(f);
			}
		}else {
			String absolutePath = file.getAbsolutePath();
			if (file.isFile() && !file.getName().contains("lock")) {
				if (absolutePath.toLowerCase().endsWith("xls") || absolutePath.toLowerCase().endsWith("xlsx")) {
					if (!IgnoreFile.isIgnore(file.getName())) {
//						if (file.getName().equals("112. PT One Connect Indonesia.xls"))
						files.add(file);
					}
				}
			}
		}
	}
	
	public void runMigration() throws Exception{
		File folder = new File("C:/Users/19057559/workspaces/java/losmigration/sql/"+DateTool.getCurrentYMDHS());
		if (!folder.exists()) folder.mkdir();
		super.fileWriter = new FileWriter(new File(folder, "script.sql"));
		
		// Define all mapping
		List<Mapping> mapping = new ArrayList<Mapping>();
		mapping.add(new InformasiDebitur());
//		mapping.add(new TujuanDanFasilitas());
//		mapping.add(new DataUsaha());
		
		// Clear All Data with createdBy = 'MIGRATION' and load data refrence		
		initilize(mapping);
		
		for (int i = 0; i < files.size(); i++) {
			File file = files.get(i);
			
			log.debug(i +" Processing "+file.getAbsolutePath());
			try {
				loadFile(file, mapping);
			}catch(Exception e) {
				log.error("Fail to process "+file.getAbsolutePath()+" "+ e.getMessage(), e);
			}
		}
		
		fileWriter.close();
	}
	
	public static void main(String[] args) throws Exception{
		log.error("---------- START MIGRATION ----------");
		
		BasicConfigurator.configure();
		
		Main main = new Main();
		
		// Run Untuk Development
//		main.runMapping(new File("C:/Users/19057559/workspaces/java/losmigration/input"));
		
		// Run Untuk Migration		
		main.runInitilize(new File("C:/Users/19057559/workspaces/java/Proposal"));
		main.runMigration();
		
		log.error("---------- END MIGRATION ----------");
	}
}

