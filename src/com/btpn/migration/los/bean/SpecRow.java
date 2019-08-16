package com.btpn.migration.los.bean;

import java.util.ArrayList;
import java.util.List;

public class SpecRow {
	
	private List<SpecCell> specCells = new ArrayList<SpecCell>();
	private IActions action;
	private String sheet;
	
	private SpecRow(IActions action) {
		this.action = action;
	}
	
	public static SpecRow get(IActions action, SpecRow specRow) {
		SpecRow row = new SpecRow(action);
		row.add(specRow);
		return row;
	}
	
	public static SpecRow get(IActions action) {
		return new SpecRow(action);
	}
	
	public List<SpecCell> getSpecCells() { return specCells; }
	public void setSpecCells(List<SpecCell> specCells) { this.specCells = specCells; }
	
	public IActions getAction() { return action; }
	public void setAction(IActions action) { this.action = action; }
	
	public SpecRow add(SpecRow specRow) {
		specCells.addAll(specRow.getSpecCells());
		return this;
	}
	
	public SpecRow setSheet(String sheet) {
		this.sheet = sheet;
		
		return this;
	}
	
	public SpecRow xls(String sheet, String variable, String address) {
		SpecCell cell = new SpecCell();
		cell.setSheet(sheet);
		cell.setVariable(variable);
		cell.setAddress(address);
		specCells.add(cell);
		
		return this;
	}
	
	public SpecRow xls(String variable, String address) {
		if (sheet == null) throw new NullPointerException("Sheet can not be null");
		return xls(sheet, variable, address);
	}
	
	public SpecRow fix(String sheet, String variable, String value) {
		SpecCell cell = new SpecCell();
		cell.setSheet(sheet);
		cell.setVariable(variable);
		cell.setValue(value);
		cell.setFix(true);
		specCells.add(cell);
		
		return this;
	}
	
	public SpecRow fix(String variable, String value) {
		if (sheet == null) throw new NullPointerException("Sheet can not be null");
		return fix(sheet, variable, value);
	}

	public void clear() {
		this.specCells.clear();
		this.specCells = null;
	}
}
