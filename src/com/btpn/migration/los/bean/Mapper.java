package com.btpn.migration.los.bean;

import java.util.List;

public class Mapper {
	private List<SpecCell> specCells;
	
	public Mapper() {}
	
	public List<SpecCell> getSpecCells() { return specCells; }
	public void setSpecCells(List<SpecCell> specCells) { this.specCells = specCells; }
	
	public Object getObject(String key) {
		for (SpecCell cell : specCells) {
			if (key.equals(cell.getVariable())) { return cell.getValue(); }
		}
		
		return null;
	}
	
	public String getAddress(String key) {
		for (SpecCell cell : specCells) {
			if (key.equals(cell.getVariable())) { return cell.getAddress(); }
		}
		
		return null;
	}
	
	public String getLocation(String key) {
		for (SpecCell cell : specCells) {
			if (key.equals(cell.getVariable())) { return cell.getSheet()+"."+cell.getAddress(); }
		}
		
		return null;
	}

	public String getString(String key) {
		Object obj = getObject(key);
		if (obj == null || "".equals(obj)) return null;
		
		String rvalue = (String) obj;
		return rvalue.replaceAll("\\'", "\\\\'").trim();
	}
	
	public String clearDecimal(String data) {
		return data.split("\\.")[0];
	}
	
	public String getString(String key, String defaultVal) {
		String rvalue = getString(key);
		if (rvalue == null) return defaultVal;
		if ("".equals(rvalue.trim())) return defaultVal;
		
		return rvalue;
	}

	public static void main(String[] args) {
		String rvalue = "El'vi'no";
		String hasil = rvalue.replaceAll("\\'", "\\\\'");
		System.out.println("- "+hasil);
	}
}
