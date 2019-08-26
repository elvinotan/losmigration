package com.btpn.migration.los;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.btpn.migration.los.mapping.DataUsaha;
import com.btpn.migration.los.mapping.InformasiDebitur;
import com.btpn.migration.los.mapping.Mapping;
import com.btpn.migration.los.mapping.TujuanDanFasilitas;

public class Main extends AbstractMain {
	final static Logger log = Logger.getLogger(Main.class);
	
	public void runMapping(File folder) throws Exception {
		// Define all mapping
		List<Mapping> mapping = new ArrayList<Mapping>();
		mapping.add(new InformasiDebitur());
		mapping.add(new TujuanDanFasilitas());
		mapping.add(new DataUsaha());
		
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
					files.add(file);	
				}
			}
		}
	}
	
	public void runMigration() throws Exception{
		// Define all mapping
		List<Mapping> mapping = new ArrayList<Mapping>();
		mapping.add(new InformasiDebitur());
		mapping.add(new TujuanDanFasilitas());
		mapping.add(new DataUsaha());
		
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
	}
	
	public static void main(String[] args) throws Exception{
		BasicConfigurator.configure();
		
		Main main = new Main();
		
//		// Run Untuk Development
		main.runMapping(new File("C:/Users/19057559/workspaces/java/losmigration/input"));
		
		// Run Untuk Migration
//		main.runInitilize(new File("C:/Users/19057559/workspaces/java/losmigration/input/Proposal"));
//		main.runMigration();
	}
}

