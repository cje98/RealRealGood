package com.kh.realgood.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCP {
	public static Connection getConnection() throws NamingException, SQLException {

		Context initContext = new InitialContext();
		Context envContxt = (Context) initContext.lookup("java:/comp/env");

		DataSource ds = (DataSource) envContxt.lookup("jdbc/oracle");

		return ds.getConnection();

	}
}
