package com.btpn.migration.los;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;

import com.btpn.migration.los.mapping.DataUsaha;
import com.btpn.migration.los.mapping.InformasiDebitur;
import com.btpn.migration.los.mapping.Mapping;
import com.btpn.migration.los.mapping.TujuanDanFasilitas;

public class Main extends AbstractMain {
	
	public void run() throws Exception {
		// Define all mapping
		List<Mapping> mapping = new ArrayList<Mapping>();
		mapping.add(new InformasiDebitur());
		mapping.add(new TujuanDanFasilitas());
		mapping.add(new DataUsaha());
		
		// Clear All Data with createdBy = 'MIGRATION' and load data refrence		
		initilize(mapping);
		
		// Process migration
		String output = "C:/Users/19057559/workspaces/java/losmigration/input";
		File folder = new File(output);
		for (File file: folder.listFiles()) {
			if (file.isFile() && !file.getName().contains("lock")) {
				loadFile(file, mapping);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BasicConfigurator.configure();
		
		Main main = new Main();
		main.run();
	}
}

