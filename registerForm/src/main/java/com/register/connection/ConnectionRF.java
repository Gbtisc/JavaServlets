package com.register.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionRF {
	private Connection connection = null;
	
	public ConnectionRF() throws SQLException, ClassNotFoundException {
		connect();
	}
	
	public void connect() throws SQLException, ClassNotFoundException {
		String dbUrl = "jdbc:mysql://localhost:3306/enterprise";
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(dbUrl, "root", "root");
	}
	
	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	public Connection getConnection() {
		return connection;
	}
}