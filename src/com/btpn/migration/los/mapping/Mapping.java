package com.btpn.migration.los.mapping;

import java.util.List;

import com.btpn.migration.los.bean.SpecRow;

public interface Mapping {
	public void initMapping();
	
	public String[] clearTable();
	
	public List<SpecRow> getSpecRows();
}
