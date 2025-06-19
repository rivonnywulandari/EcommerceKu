package com.bootcamp.latihan.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBPools {
	
	public static Connection getConnection() {
				
		Connection conn = null;
		Context initContext;
		
		try {
						
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/EcommerceDB");
			conn = ds.getConnection();

			
			System.out.println(conn);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
//		Connection conn = null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection(
//			    "jdbc:mysql://localhost:3306/ecommerce_db", 
//			    "root", 
//			    ""
//			);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return conn;
		
	}

}
