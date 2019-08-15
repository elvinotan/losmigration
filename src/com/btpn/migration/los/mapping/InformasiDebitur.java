package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.SpecCell;
import com.btpn.migration.los.bean.SpecTable;
import com.btpn.migration.los.constant.Sheet;

public class InformasiDebitur implements Mapping{
	private SpecTable specTable;
	
	@Override
	public void initMapping() {
		List<SpecCell> specCells = new ArrayList<SpecCell>();		
		specCells.add(SpecCell.get().sheet(Sheet.Informasi_Debitur).address("C4").variable("NamaRM"));
		specCells.add(SpecCell.get().sheet(Sheet.Informasi_Debitur).address("C5").variable("KodeRm"));
		
		this.specTable = new SpecTable();
		this.specTable.setSpecCells(specCells);
	}
	
	@Override
	public SpecTable getSpecTable() {
		return this.specTable;
	}
	
	@Override
	public String insert() {
		return "insert into dlos_informasi_debitur(Name, Kode) values ($NamaRM, $KodeRm);";
	}
	
	@Override
	public void clear() {
		specTable.clear();		
	}
}
