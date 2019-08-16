package com.btpn.migration.los;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.mapping.AnalisaRekeningKoran;
import com.btpn.migration.los.mapping.InformasiDebitur;
import com.btpn.migration.los.mapping.Mapping;

public class Main extends AbstractMain {
	
	public void run() throws Exception {
		List<Mapping> mapping = new ArrayList<Mapping>();
		mapping.add(new InformasiDebitur());
		mapping.add(new AnalisaRekeningKoran());
		
		for (Mapping map: mapping) {
			String[] sqls = map.clearTable();
			for (String sql : sqls) {
				System.out.println("- "+sql);
			}			
		}
		
		String output = "C:\\Users\\19057559\\workspaces\\java\\losmigration\\input";
		File folder = new File(output);
		for (File file: folder.listFiles()) {
			if (file.isFile()) {
				loadFile(file, mapping);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		Main main = new Main();
		main.run();
	}
}

