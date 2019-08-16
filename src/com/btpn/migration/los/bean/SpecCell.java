package com.btpn.migration.los.bean;

public class SpecCell {
	private String sheet;
	private String address;
	private String variable;
	private Object value;
	private boolean fix = false; // fix artinya kita sudah provide nilainya gax perlu ambil dari excel
	
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
	
	public SpecCell fix(boolean fix) {
		this.fix = fix;
		return this;
	}
	
	public boolean isFix() {
		return this.fix;
	}
	
	public boolean isMatch(String sheet, String address) {
		return (this.sheet.equals(sheet) && this.address.equals(address));
	}
	
	public boolean isVariable(String variable) {
		return this.variable.equals(variable);
	}
	
	public Object getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return this.sheet+"."+this.address+"("+this.variable+")="+value;
	}
}
