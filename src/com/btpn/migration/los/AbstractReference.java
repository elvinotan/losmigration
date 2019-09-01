package com.btpn.migration.los;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.btpn.migration.los.bean.CommonService;
import com.btpn.migration.los.bean.CommonServiceGroup;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Region;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.db.DbConnection;
import com.btpn.migration.los.mapping.Mapping;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class AbstractReference extends AbstractPrepare{
	final static Logger log = Logger.getLogger(AbstractReference.class);
	
	private Gson gson = new Gson();
	private Store store = new Store();
	
	protected void loadReference(List<Mapping> mapping) throws Exception{
		loadCommonService(store);
		loadLookup(store);
		loadRegion(store);
		clearTables(mapping);
	}
	
	protected Store getStore() { return store; }
	
	private void loadCommonService(Store store) {
		log.debug("Load CommonService data from file");
		
		File folder = new File("json");
		for (File f : folder.listFiles()) {
			try {
				JsonReader reader = new JsonReader(new FileReader(f));
				CommonServiceGroup group = gson.fromJson(reader, CommonServiceGroup.class);
				if (group != null) {
					for (CommonService s : group.getResult()) {
						s.setGroup(f.getName().replaceAll(".json", ""));
						store.add(s);
					}
				}
				reader.close();
			}catch(Exception e) {
				log.error("Fail to load CommonService data "+e.getMessage(), e);
			}
		}
	}
	
	private void loadLookup(Store store) {
		log.debug("Load lookup from database");
		
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		
		try {
			Connection connection = DbConnection.get().getConnection();
			preStmt = connection.prepareStatement("select * from dlos_lookup_detail");
			rs = preStmt.executeQuery();
			while (rs.next()) {
				String lookupId = rs.getString("lookupId");
				String key = rs.getString("key");
				String group = rs.getString("group");
				String description = rs.getString("description");
				Boolean isActive = rs.getBoolean("isActive");
				store.add(new Lookup(Long.valueOf(lookupId), key, group, description, isActive));
			}
		}catch(Exception e) {
			log.error("Fail to load Lookup "+e.getMessage(), e);
		}finally {
			if (rs != null)  try { rs.close(); }catch(Exception e) {  }
			if (preStmt != null)  try { preStmt.close(); }catch(Exception e) {  }
		}
	}
	
	private void loadRegion(Store store) {
		log.debug("Load Region from file");
		
		for (Region r : Region.getRegions()) {
			store.add(r);
		}
	}
	
	private void clearTables(List<Mapping> mapping) {
		log.debug("clearTables");
		
		PreparedStatement preStmt = null;
		List<Mapping> reverseMapping = new ArrayList<Mapping>();
		
		try {
			reverseMapping.addAll(mapping);
			Collections.reverse(reverseMapping);
			
			for (Mapping m : reverseMapping) {
				for (String sql : m.clearTable()) {
					log.debug(sql);
					writeSql(sql);
					
					preStmt = DbConnection.get().getConnection().prepareStatement(sql);
					preStmt.execute();
				}
			}
		}catch(Exception e) {
			log.error("Fail to clear Tables "+e.getMessage(), e);			
		}finally {
			reverseMapping.clear();
			reverseMapping = null;
			
			if (preStmt != null)  try { preStmt.close(); }catch(Exception e) {  }
		}
	}
}