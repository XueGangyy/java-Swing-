package com.ynu.dinnerorder.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
		 private String url="jdbc:mysql://localhost:3306/dinnerorder" ;
		 private String user="root";
		 private String password="wty199612291234";
		 private Connection con;
		 
		 public Jdbc(){
		 }
		
		public Connection getConnection()
		{
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();//获得数据库的连接
				con = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return con;
		}
		
	
	
}
