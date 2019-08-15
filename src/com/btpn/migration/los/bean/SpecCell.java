package com.btpn.migration.los.bean;

public class SpecCell {
	private String sheet;
	private String address;
	private String variable;
	private Object value;
	
	public static SpecCell get() {
		return new SpecCell();
	}
	
	public SpecCell sheet(String sheet) {
		this.sheet = sheet;
		return this;
	}
	
	public SpecCell address(String address) {
		this.address = address;
		return this;
	}
	
	public SpecCell value(String value) {
		this.value = value;
		return this;
	}
	
	public SpecCell variable(String variable) {
		this.variable = variable;
		return this;
	}
	
	public boolean is(String sheet, String address) {
		return (this.sheet.equals(sheet) && this.address.equals(address));
	}
	
	@Override
	public String toString() {
		return this.sheet+"."+this.address+"("+this.variable+")="+value;
	}
}
