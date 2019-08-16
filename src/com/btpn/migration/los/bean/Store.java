package com.btpn.migration.los.bean;

import java.util.HashMap;
import java.util.Map;



public class Store {
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public void put(String key, Object object) {
		map.put(key, object);
	}
	
	public Object getObject(String key) {
		return map.get(key);
	}

	public String getString(String key) {
		Object obj = map.get(key);
		if (obj == null) return null;
		
		return "\'"+(String)obj+"\'";
	}
	
	public String getNumber(String key) {
		Object obj = getObject(key);
		if (obj == null) return null;
		
		return (String) obj;
	}
}
