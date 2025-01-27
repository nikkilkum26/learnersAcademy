package com.learnersacademy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.learnersacademy.model.Admin;

public class Admin_db {
	private String dbUrl = "jdbc:mysql://localhost:3306/learnersacademy?useSSL=false";
	private String dbUname = "root";
	private String dbPassword = "root@123";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public boolean validate(Admin loginBean)
	{
		boolean status = false;
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		
		String sql = "select * from admin where username = ? and password =?";
		PreparedStatement ps;
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1, loginBean.getUserName());
		ps.setString(2, loginBean.getPassword());
		ResultSet rs = ps.executeQuery();
		status = rs.next();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}
