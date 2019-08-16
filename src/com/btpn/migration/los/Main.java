package com.btpn.migration.los;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.mapping.Mapping;
import com.btpn.migration.los.mapping.smes.LaporanKeuanganSMES;

public class Main extends AbstractMain {
	
	public void run() throws Exception {
		List<Mapping> mapping = new ArrayList<Mapping>();
		mapping.add(new LaporanKeuanganSMES());
		
		for (Mapping map: mapping) {
			String[] sqls = map.clearTable();
			for (String sql : sqls) {
				System.out.println(sql);
			}			
		}
		
		String output = "C:\\Users\\19057559\\workspaces\\java\\losmigration\\input\\smes";
		File folder = new File(output);
		for (File file: folder.listFiles()) {
			if (file.isFile() && !file.getName().contains("lock")) {
				loadFile(file, mapping);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		Main main = new Main();
		main.run();
//		Long aa = new Long(42735);
//		Date aabb = new Date(aa);
//		System.out.println("- "+aabb);
	}
}

