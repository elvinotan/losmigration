package com.btpn.migration.los.mapping.smes;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.mapping.Mapping;

public class InformasiDebiturSMES implements Mapping{
	private List<SpecRow> specRows = new ArrayList<SpecRow>();

	@Override
	public String[] clearTable() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void initMapping() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SpecRow> getSpecRows() {
		return this.specRows;
	}

}
