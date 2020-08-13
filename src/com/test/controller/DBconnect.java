package com.test.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect {

	public static Connection getConnection() {
		

		try {
			String url = "jdbc:mysql://localhost:3306/JSPBookDB";
			String user = "root";
			String password="1234";
			
			Class.forName("org.mariadb.jdbc.Driver");
			
		
			return DriverManager.getConnection(url, user, password);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
		
	}
}
