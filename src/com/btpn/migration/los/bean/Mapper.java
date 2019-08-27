package com.btpn.migration.los.bean;

import java.util.List;

import org.apache.log4j.Logger;

import com.btpn.migration.los.tool.StringTool;

public class Mapper {
	final static Logger log = Logger.getLogger(Store.class);
	
	public String className = "";
	private List<SpecCell> specCells;
	private SpecCell lastCell;
	
	public Mapper() {}
	
	public List<SpecCell> getSpecCells() { return specCells; }
	public void setSpecCells(List<SpecCell> specCells) { this.specCells = specCells; }
	
	public Object getObject(String key) {
		for (SpecCell cell : specCells) {
			if (key.equals(cell.getVariable())) {
				lastCell = cell;
				return cell.getValue(); 
			}
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
	
	public String logMapperProblem(String method) {
		if (lastCell != null && !StringTool.isEmpty((String)lastCell.getValue())) {
			log.warn("[MAPPING PROBLEM LOC] "+className+">"+method+">"+lastCell.getVariable()+ " on " +lastCell.getSheet()+"."+lastCell.getAddress()+", Value: ["+lastCell.getValue()+"]");
		}
		
		return null;
	}
	
	
	public String clearDecimal(String data) {
		if (data == null) return null;
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
