package com.btpn.migration.los;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;

import com.btpn.migration.los.mapping.InformasiDebiturSMES;
import com.btpn.migration.los.mapping.Mapping;

public class Main extends AbstractMain {
	
	public void run() throws Exception {
		List<Mapping> mapping = new ArrayList<Mapping>();
		mapping.add(new InformasiDebiturSMES());
		
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

