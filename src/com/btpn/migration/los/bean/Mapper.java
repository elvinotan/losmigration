package com.btpn.migration.los.bean;

import java.util.List;

public class Mapper {
	private List<SpecCell> specCells;
	
	public Mapper() {}
	
	public List<SpecCell> getSpecCells() { return specCells; }
	public void setSpecCells(List<SpecCell> specCells) { this.specCells = specCells; }
	
	public Object getObject(String key) {
		for (SpecCell cell : specCells) {
			if (cell.isVariable(key)) { return cell.getValue(); }
		}
		
		return null;
	}

	public String getString(String key) {
		Object obj = getObject(key);
		if (obj == null) return null;
		
		return "\'"+(String)obj+"\'";
	}
	
	public String getNumber(String key) {
		Object obj = getObject(key);
		if (obj == null) return null;
		
		return (String) obj;
	}
}
