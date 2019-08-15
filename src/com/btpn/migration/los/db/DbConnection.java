package com.btpn.migration.los.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbConnection {

	public DbConnection() {
	}
	
	public Connection getConnection() {
		return null;
	}
	
	public void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			}catch(Exception e) {}
		}
	}
	
	public void close(Connection con, PreparedStatement stmt) {
		if (stmt != null) {
			try {
				close(con);
				stmt.close();
			}catch(Exception e) {}
		}
	}
	
	public void close(Connection con, PreparedStatement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				close(con, stmt);
				rs.close();
			}catch(Exception e) {}
		}
	}
	
}
