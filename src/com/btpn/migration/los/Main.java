package com.btpn.migration.los;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.btpn.migration.los.mapping.InformasiDebitur;
import com.btpn.migration.los.mapping.Mapping;

public class Main extends AbstractMain {
	final static Logger log = Logger.getLogger(Main.class);
	
	public static boolean EXECUTE_SQL_STATEMENT = true;
	
	public void migrate() throws Exception{
		List<Mapping> mapping = new ArrayList<Mapping>();
		mapping.add(new InformasiDebitur());
		//mapping.add(new TujuanDanFasilitas());
		//mapping.add(new DataUsaha());
		
		// Clear All Data with createdBy = 'MIGRATION' and load data references		
		loadReference(mapping);
		
		for (int i = 0; i < getMigratedFile().size(); i++) {
			File f = getMigratedFile().get(i);
			
			log.debug("=========================================================================");
			log.debug(i +". Migrate file : "+f.getAbsolutePath());
			try {
				loadFile(f, mapping);
			}catch(Exception e) {
				log.error("Fail to process file "+f.getAbsolutePath()+" "+ e.getMessage(), e);
			}
		}
		
		dispose();
	}
	
	public static void main(String[] args) throws Exception{
		log.debug("---------- START MIGRATION ----------");
		
		Main main = new Main();
//		main.initialize(new File("input")); // Development migration
		main.initialize(new File("../Proposal")); // Real migration
		main.migrate();
		
		log.debug("---------- END MIGRATION ----------");
	}
}

