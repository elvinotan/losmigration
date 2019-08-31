package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.SpecRow;

public class DataJaminan implements Mapping {
	private List<SpecRow> specRows = new ArrayList<SpecRow>();
	
	@Override
	public String[] clearTable() {
		return new String[] {
				
		};
	}

	@Override
	public void initMapping(String filename, String lobType) {
		
	}

	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		return specRows;
	}

}
