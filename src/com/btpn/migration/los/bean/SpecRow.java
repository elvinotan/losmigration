package com.btpn.migration.los.bean;

import java.util.ArrayList;
import java.util.List;

public class SpecRow {
	
	private List<SpecCell> specCells = new ArrayList<SpecCell>();
	private IActions action;
	
	public SpecRow(IActions action) {
		this.action = action;
	}

	public List<SpecCell> getSpecCells() { return specCells; }
	public void setSpecCells(List<SpecCell> specCells) { this.specCells = specCells; }
	
	public IActions getAction() { return action; }
	public void setAction(IActions action) { this.action = action; }

	public void clear() {
		this.specCells.clear();
		this.specCells = null;
	}
}
