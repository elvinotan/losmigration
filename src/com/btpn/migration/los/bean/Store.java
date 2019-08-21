package com.btpn.migration.los.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.btpn.migration.los.AbstractMain;

public class Store {
	final static Logger log = Logger.getLogger(Store.class);
	
	private Map<String, Object> generalMap = new HashMap<String, Object>();
	private Map<String, Lookup> lookupMap = new HashMap<String, Lookup>();
	private Map<String, CommonService> commonServiceMap =  new HashMap<String, CommonService>();
	private Map<String, Region> regionMap = new HashMap<String, Region>();
	private Map<String, Region> branchMap = new HashMap<String, Region>();
	
	public void add(Region region) {
		this.regionMap.put(region.getRegion(), region);
		this.branchMap.put(region.getBranch(), region);
	}
	
	public void add(Lookup lookup) {
		this.lookupMap.put(lookup.getGroup()+"~"+lookup.getKey(), lookup);
		this.lookupMap.put(lookup.getGroup()+"~"+lookup.getDescription(), lookup);
	}
	
	public void add(CommonService commonService) {
		this.commonServiceMap.put(commonService.getGroup()+"~"+commonService.getDescription(), commonService);
	}
	
	public Lookup getLookupByKey(String group, String key) {
		return getLookupByKey(group, key, false);
	}
	
	public Lookup getLookupByKey(String group, String key, boolean throwErr) {
		Lookup lookup = this.lookupMap.get(group+"~"+key);		
	
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
		Lookup lookup = this.lookupMap.get(group+"~"+description);
		
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
		CommonService cs = this.commonServiceMap.get(group+"~"+description);
		
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
		Region region = this.regionMap.get(description);
		
		if (region == null) {
			log.error("[MAPPING PROBLEM] Data Region Not found for description ("+description+")");
			
			if (throwErr) throw new NullPointerException("Data Region Not found for description ("+description+")");
			return null;
		}else {
			return region;
		}
	}
	
	public Region getBranchByDescription(String description) {
		return getBranchByDescription(description, false);
	}
	
	public Region getBranchByDescription(String description, boolean throwErr) {
		Region branch = this.branchMap.get(description);
		
		if (branch == null) {
			log.error("[MAPPING PROBLEM] Data Branch Not found for description ("+description+")");
			
			if (throwErr) throw new NullPointerException("Data Branch Not found for  description ("+description+")");
			return null;
		}else {
			return branch;
		}
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
	
	public void clear() {
		generalMap.clear();
		lookupMap.clear();
		commonServiceMap.clear();
		regionMap.clear();
		branchMap.clear();
	}
}
