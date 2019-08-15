package com.btpn.migration.los.bean;

import java.util.ArrayList;
import java.util.List;

public class SpecTable {
	
	private List<SpecCell> specCells = new ArrayList<SpecCell>();

	public List<SpecCell> getSpecCells() { return specCells; }
	public void setSpecCells(List<SpecCell> specCells) { this.specCells = specCells; }
	
	public void clear() {
		this.specCells.clear();
		this.specCells = null;
	}
}
