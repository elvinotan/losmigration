package com.btpn.migration.los.bean;

public class Statement {
	private String sql;
	private String pk;
	private String pkVariable;
	
	private Statement() {}

	public static Statement get() {
		return new Statement();
	}
	
	public String getSql() { return sql; }
	public Statement setSql(String sql) { this.sql = sql; return this; }
	
	public String getPk() { return pk; }
	public Statement setPk(String pk) { this.pk = pk; return this; }

	public String getPkVariable() { return pkVariable; }
	public Statement setPkVariable(String pkVariable) { this.pkVariable = pkVariable; return this; }
}
