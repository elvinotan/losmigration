package com.btpn.migration.los.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Store {
	final static Logger log = Logger.getLogger(Store.class);
	
	private Map<String, Object> generalMap = new HashMap<String, Object>();
	private Map<String, Lookup> lookupMap = new HashMap<String, Lookup>();
	private Map<String, CommonService> commonServiceMap =  new HashMap<String, CommonService>();
	private Map<String, Region> regionMap = new HashMap<String, Region>();
	private Map<String, Region> branchMap = new HashMap<String, Region>();
	
	public void add(Region region) {
		this.regionMap.put(clear(region.getRegion()), region);
		this.branchMap.put(clear(region.getBranch()), region);
	}
	
	public void add(Lookup lookup) {
		this.lookupMap.put(lookup.getGroup()+"~"+clear(lookup.getKey()), lookup);
		this.lookupMap.put(lookup.getGroup()+"~"+clear(lookup.getDescription()), lookup);
	}
	
	public void add(CommonService commonService) {
		this.commonServiceMap.put(commonService.getGroup()+"~"+clear(commonService.getDescription()), commonService);
	}
	
	public Lookup getLookupByKey(String group, String key) {
		return getLookupByKey(group, key, false);
	}
	
	public Lookup getLookupByKey(String group, String key, boolean throwErr) {
		Lookup lookup = this.lookupMap.get(group+"~"+clear(key));		
	
		if (lookup == null) {
			log.error("[MAPPING PROBLEM] Data Lookup Not found for ("+group+") with key ("+key+")");
			
			if (throwErr) throw new NullPointerException("Data Lookup Not found for ("+group+") with key ("+key+")");
			return null;
		}else {
			return lookup;
		}
	}
	
	public Lookup getLookupByDescription(String group, String description) {
		return getLookupByDescription(group, description, false);
	}
	
	public Lookup getLookupByDescription(String group, String description, boolean throwErr) {
		Lookup lookup = this.lookupMap.get(group+"~"+clear(description));
		
		if (lookup == null) {
			log.error("[MAPPING PROBLEM] Data Lookup Not found for ("+group+") with description ("+description+")");
			
			if (throwErr) throw new NullPointerException("Data Lookup Not found for ("+group+") with description ("+description+")");
			return null;
		}else {
			return lookup;
		}
	}
	
	public CommonService getCommonByDescription(String group, String description) {
		return getCommonByDescription(group, description, false);
	}
	
	public CommonService getCommonByDescription(String group, String description, boolean throwErr) {
		CommonService cs = this.commonServiceMap.get(group+"~"+clear(description));
		
		if (cs == null) {
			log.error("[MAPPING PROBLEM] Data CommonService Not found for ("+group+") with description ("+description+")");
			
			if (throwErr) throw new NullPointerException("Data CommonService Not found for ("+group+") with description ("+description+")");
			return null;
		}else {
			return cs;
		}
	}
	
	public Region getRegionByDescription(String description) {
		return getRegionByDescription(description, false);
	}
	
	public Region getRegionByDescription(String description, boolean throwErr) {
		Region region = this.regionMap.get(clear(description));
		region = (region == null) ? getTolerateRegion(clear(description)) : region;
		
		if (region == null) {
			log.error("[MAPPING PROBLEM] Data Region Not found for description ("+description+")");
			
			if (throwErr) throw new NullPointerException("Data Region Not found for description ("+description+")");
			return null;
		}else {
			return region;
		}
	}
	
	private Region getTolerateRegion(String description) {
		List<String> matchParts = new ArrayList<String>();
		
		for (String key : this.regionMap.keySet()) {
			if (key.contains(description)) { matchParts.add(key); }
		}
		
		if (matchParts.size() != 1) return null;
		return this.regionMap.get(matchParts.get(0));
	}
	
	public Region getBranchByDescription(String description) {
		return getBranchByDescription(description, false);
	}
	
	public Region getBranchByDescription(String description, boolean throwErr) {
		Region branch = this.branchMap.get(clear(description));
		branch = (branch == null) ? getTolerateBranch(clear(description)) : branch;
		
		if (branch == null) {
			log.error("[MAPPING PROBLEM] Data Branch Not found for description ("+description+")");
			
			if (throwErr) throw new NullPointerException("Data Branch Not found for  description ("+description+")");
			return null;
		}else {
			return branch;
		}
	}
	
	private Region getTolerateBranch(String description) {
		List<String> matchParts = new ArrayList<String>();
		
		for (String key : this.branchMap.keySet()) {
			if (key.contains(description)) { matchParts.add(key); }
		}
		
		if (matchParts.size() != 1) return null;
		return this.branchMap.get(matchParts.get(0));
	}
	
//	Jgn Pake WildCard, berbahaya
//	public CommonService getCommonByLikeDescription(String group, String description) {
//		for (String key : this.commonServiceMap.keySet()) {
//			String[] array = key.split("~");
//			if (array[0].equals(group)) {
//				if (array[1].toUpperCase().indexOf(description.toUpperCase()) >= 0) {
//					return getCommonByDescription(group, array[1]);
//				}
//			}
//		}
//		
//		throw new NullPointerException("Data CommonService Not found for "+group+" with description "+description);
//	}
	
	public void put(String key, Object object) {
		generalMap.put(key, object);
	}
	
	public Object getObject(String key) {
		return generalMap.get(key);
	}

	public String getString(String key) {
		Object obj = getObject(key);
		if (obj == null || "".equals(obj)) return null;
		
		String rvalue = (String) obj;
		return rvalue.replaceAll("\\'", "\\\\'").trim();
	}
	
	public String getString(String key, String defaultVal) {
		String rvalue = getString(key);
		if (rvalue == null) return defaultVal;
		if ("".equals(rvalue.trim())) return defaultVal;
		
		return rvalue;
	}
	
	private String clear(String data) {
		String clearData = data.replaceAll(" ", "") // Hapus space
							.replaceAll("-", "") 	// Hapus -
							.replaceAll(",", "") 	// Hapus ,
							.toUpperCase();			// Buat jadi huruf besar
//		log.debug("Clear ('"+clearData+"','"+data+"')");
		return clearData;
	}
	
	public void clear() {
		generalMap.clear();
		lookupMap.clear();
		commonServiceMap.clear();
		regionMap.clear();
		branchMap.clear();
	}
}
