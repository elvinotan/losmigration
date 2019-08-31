package com.btpn.migration.los;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.btpn.migration.los.constant.IgnoreFile;
import com.btpn.migration.los.tool.DateTool;

public class AbstractPrepare {
	final static Logger log = Logger.getLogger(AbstractPrepare.class);
	
	private List<File> files = new ArrayList<File>();
	private FileWriter sqlWriter = null;
	
	public void initialize(File file) throws IOException {
		log.debug("load migration file list");
		loadMigrationFile(file);
		
		log.debug("create SQL writer");
		createSqlWriter();
	}
	
	private void loadMigrationFile(File file) throws IOException {
		if (file.isDirectory()) {
			for (File f: file.listFiles()) {
				loadMigrationFile(f);
			}
		}else {
			String absolutePath = file.getAbsolutePath();
			if (file.isFile() && !file.getName().contains("lock") && !file.getName().startsWith(".")) {
				if (absolutePath.toLowerCase().endsWith("xls") || absolutePath.toLowerCase().endsWith("xlsx")) {
					if (!IgnoreFile.isIgnore(file.getName())) {
//						if (file.getName().equals("099. PT. BPR Utomo Manunggal Sejahtera.xls"))
							files.add(file);
					}
				}
			}
		}
	}
	
	private void createSqlWriter() throws IOException {
		this.sqlWriter = new FileWriter(new File("sql", "script_"+DateTool.getCurrentYMDHS()+".sql"));
	}
	
	protected List<File> getMigratedFile() { return this.files; }
	
	protected void writeSql(String s) throws IOException { 
		this.sqlWriter.write(s+"\n"); 
	}
	
	protected void dispose() throws IOException {
		this.files.clear();
		this.files = null;
		
		this.sqlWriter.close();
	}
}
