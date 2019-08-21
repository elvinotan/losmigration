package com.btpn.migration.los.bean;

import java.util.HashMap;
import java.util.Map;

public class Store {
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Map<String, Lookup> lookupMap = new HashMap<String, Lookup>();
	private Map<String, CommonService> commonServiceMap =  new HashMap<String, CommonService>();
	private Map<String, Branch> branchMap =  new HashMap<String, Branch>();
	
	public void add(Lookup lookup) {
		this.lookupMap.put(lookup.getGroup()+"~"+lookup.getKey(), lookup);
		this.lookupMap.put(lookup.getGroup()+"~"+lookup.getDescription(), lookup);
	}
	
	public void add(CommonService commonService) {
		this.commonServiceMap.put(commonService.getGroup()+"~"+commonService.getDescription(), commonService);
	}
	
	public void add(Branch branch) {
		String newBranchName = branch.getBranchName().toUpperCase().replaceAll(" ", "");
		this.branchMap.put(newBranchName+"~"+branch.getLobId(), branch);	
	}
	
	public Branch getBranchByDescription(String branchName, int lobId) {
		return getBranchByDescription(branchName, lobId, false);
	}
	
	public Branch getBranchByDescription(String branchName, int lobId, boolean throwErr) {
		String newBranchName = branchName.toUpperCase().replaceAll(" ", "");
		Branch branch = this.branchMap.get(newBranchName+"~"+lobId);
		if (branch == null && throwErr) {
			throw new NullPointerException("Data Branch Not found for "+branchName+" and lobId "+lobId);
		}else {
			return branch;
		}
	}
	
	public Lookup getLookupByKey(String group, String key) {
		return getLookupByKey(group, key, false);
	}
	
	public Lookup getLookupByKey(String group, String key, boolean throwErr) {
		Lookup lookup = this.lookupMap.get(group+"~"+key);
		if (lookup == null && throwErr) {
			throw new NullPointerException("Data Lookup Not found for "+group+" with key "+key);	
		}else {
			return lookup;
		}
	}
	
	public Lookup getLookupByDescription(String group, String description) {
		return getLookupByDescription(group, description, false);
	}
	
	public Lookup getLookupByDescription(String group, String description, boolean throwErr) {
		Lookup lookup = this.lookupMap.get(group+"~"+description);
		if (lookup == null && throwErr) {
			throw new NullPointerException("Data Lookup Not found for "+group+" with description "+description);	
		}else {
			return lookup;
		}
	}
	
	public CommonService getCommonByDescription(String group, String description) {
		return getCommonByDescription(group, description, false);
	}
	
	public CommonService getCommonByDescription(String group, String description, boolean throwErr) {
		CommonService cs = this.commonServiceMap.get(group+"~"+description);
		if (cs == null && throwErr) {
			throw new NullPointerException("Data CommonService Not found for "+group+" with description "+description);	
		}else {
			return cs;
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
		map.put(key, object);
	}
	
	public Object getObject(String key) {
		return map.get(key);
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
		lookupMap.clear();
		commonServiceMap.clear();
		map.clear();
	}
}
