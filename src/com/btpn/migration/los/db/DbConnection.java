package com.btpn.migration.los.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static DbConnection dbConnection;
    
    private DbConnection() {}
    
    public static DbConnection get() {
    	if (dbConnection == null) dbConnection = new DbConnection();
    	return dbConnection;
    }
    
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection != null) return connection;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dlos_core?user=dlos_sme&password=dlos_sme");
		return connection;
	}
	
	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			}catch(Exception e) {}
		}
	}
	
	private void close(Connection con, PreparedStatement stmt) {
		if (stmt != null) {
			try {
				close(con);
				stmt.close();
			}catch(Exception e) {}
		}
	}
	
	private void close(Connection con, PreparedStatement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				close(con, stmt);
				rs.close();
			}catch(Exception e) {}
		}
	}
}
