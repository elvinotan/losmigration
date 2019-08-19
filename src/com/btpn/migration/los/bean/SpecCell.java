package com.btpn.migration.los.bean;

public class SpecCell {
	private String sheet;
	private String address;
	private String variable;
	private Object value;
	private boolean fix = false; // fix artinya kita sudah provide nilainya gax perlu ambil dari excel
	private boolean pk = false; // ini artinya placeholder untuk primaryKey
	
	public String getSheet() { return sheet; }
	public void setSheet(String sheet) { this.sheet = sheet; }
	
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }

	public String getVariable() { return variable; }
	public void setVariable(String variable) { this.variable = variable; }
	
	public Object getValue() { return value; }
	public void setValue(Object value) { this.value = value; }
	
	public boolean isFix() { return fix; }
	public void setFix(boolean fix) { this.fix = fix; }
	
	public boolean isPk() { return pk; }
	public void setPk(boolean pk) { this.pk = pk; }
	
	@Override
	public String toString() {
		return "SpecCell [sheet=" + sheet + ", address=" + address + ", variable=" + variable + ", value=" + value
				+ ", fix=" + fix + "]";
	}
}
