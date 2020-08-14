package com.test.dbconn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {

	private DataSource dataSource;

	public static Connection getConnection() throws SQLException, NamingException, ClassNotFoundException {

		Context context = new InitialContext();
		DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc_mariadb");
		Connection conn = dataSource.getConnection();

		return conn;

	}
}