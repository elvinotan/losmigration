package com.btpn.migration.los.mapping;

import com.btpn.migration.los.bean.SpecTable;

public interface Mapping {
	public void initMapping();
	
	public SpecTable getSpecTable();
	
	public String insert();
	
	public void clear();
}
