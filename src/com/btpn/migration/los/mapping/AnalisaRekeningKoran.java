package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.SpecCell;
import com.btpn.migration.los.bean.SpecTable;
import com.btpn.migration.los.constant.Sheet;

public class AnalisaRekeningKoran implements Mapping{
	private SpecTable specTable;

	@Override
	public void initMapping() {
		List<SpecCell> specCells = new ArrayList<SpecCell>();		
		specCells.add(SpecCell.get().sheet(Sheet.Analisa_Rek_Koran).address("M27").variable("Plafon"));
		specCells.add(SpecCell.get().sheet(Sheet.Analisa_Rek_Koran).address("M28").variable("Rataratasaldo"));
		
		this.specTable = new SpecTable();
		this.specTable.setSpecCells(specCells);
	}
	
	@Override
	public SpecTable getSpecTable() {
		return this.specTable;
	}
	
	@Override
	public String insert() {
		return "insert into dlos_rekening_koran(Name, Contact_Person) values ($Name, $Contact_Person);";
	}
	
	@Override
	public void clear() {
		specTable.clear();		
	}
}