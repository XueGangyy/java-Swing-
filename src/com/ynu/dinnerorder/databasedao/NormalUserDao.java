package com.ynu.dinnerorder.databasedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ynu.dinnerorder.databaseConnection.Jdbc;
import com.ynu.dinnerorder.databasemodel.NormalUserModel;


public class NormalUserDao {
	/*
	 * @Author:段小雪
	 * @TestState:通过
	 */
	
	
	/*
	 * 普通用户登陆
	 */
	public NormalUserModel loginNormalUser(String nu_name,String nu_password){
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		//获取数据库连接
		NormalUserModel num=null;
		String sql="select * from "
				+ "normaluser where nu_name=? and nu_password=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			//执行sql语句
			psmt.setString(1,nu_name);
			//设置参数1
			psmt.setString(2, nu_password);
			//设置参数2
			ResultSet rs=psmt.executeQuery();
			//进行查询
			if(rs.next()){
				//如果查询结果存在，那么创建WaiterModel对象，并且赋值
				num=new NormalUserModel();
				num.setNu_id(rs.getInt("nu_id"));
				num.setNu_name(rs.getString("nu_name"));
				num.setNu_password(rs.getString("nu_password"));
				num.setNu_telephone(rs.getString("nu_telephone"));
				num.setNu_address(rs.getString("nu_address"));
			}else{
				//如果查询结果不存在，那么则不创建对象
				num=null;
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}
	
	
	/*
	 * 普通用户注册
	 */
	public boolean registerNormalUser(String nu_name,String nu_password,
			String nu_telephone,String nu_address){
		boolean b=false;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql1="select * from normaluser where nu_name=?";
		//从普通用户表中查询出用户名是否存在
		String sql2="insert into normaluser(nu_name,nu_password,nu_telephone,nu_address)values"
				+ "(?,?,?,?)";
		//写进普通用户表里面
		PreparedStatement psmt1;
		PreparedStatement psmt2;
		try {
			psmt1=con.prepareStatement(sql1);
			psmt1.setString(1, nu_name);
			ResultSet rs=psmt1.executeQuery();
			if(rs.next()){
				b=false;
			}else{
				b=true;
				psmt2=con.prepareStatement(sql2);
				//执行sql语句
				psmt2.setString(1, nu_name);
				//设置参数1
				psmt2.setString(2, nu_password);
				//设置参数2
				psmt2.setString(3, nu_telephone);
				//设置参数3
				psmt2.setString(4, nu_address);
				//设置参数4
				
				psmt2.execute();
				//执行操作
				psmt2.close();
				//关闭执行对象
			}
			rs.close();
			psmt1.close();
			con.close();
			//关闭数据库连接所有操作
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	/*
	 * 更新普通用户信息
	 */
	public NormalUserModel updateNormalUser(String nu_newname,String nu_newpassword,
			String nu_newtelephone,String nu_newaddress,int nu_id)
	{
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		NormalUserModel num=null;
		String sql="update normaluser set nu_name=?,nu_password=?,nu_telephone=?,"
				+ "nu_address=? where nu_id=?";
		PreparedStatement psmt;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, nu_newname);
			psmt.setString(2, nu_newpassword);
			psmt.setString(3, nu_newtelephone);
			psmt.setString(4, nu_newaddress);
			psmt.setInt(5, nu_id);
			psmt.execute();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	
	/*
	 * 通过普通用户id来查询出这个用户的所有信息
	 */
	public NormalUserModel equeryN_User(int nu_id){
		NormalUserModel NUM=null;
		//创建指针
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		//获取数据库连接
		String sql="select * from normaluser where nu_id=?";
		PreparedStatement psmt;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, nu_id);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()){
				NUM=new NormalUserModel();
				NUM.setNu_id(nu_id);
				NUM.setNu_name(rs.getString("nu_name"));
				NUM.setNu_telephone(rs.getString("nu_telephone"));
				NUM.setNu_address(rs.getString("nu_address"));
			}else{
				NUM=null;
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NUM;
	}
	
	/*
	 * 删除普通用户的信息
	 */
	public boolean deleteNormalUser(int nu_id){
		boolean b=false;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql1="select * from normaluser where nu_id=?";
		String sql2="delete from normaluser where nu_id=?";
		PreparedStatement psmt1;
		PreparedStatement psmt2;
		
		try {
			psmt1=con.prepareStatement(sql1);
			psmt1.setInt(1, nu_id);
			ResultSet rs=psmt1.executeQuery();
			if(rs.next()){
				b=true;
				psmt2=con.prepareStatement(sql2);
				psmt2.setInt(1,nu_id);
				psmt2.execute();
				psmt2.close();
			}else{
				b=false;
			}
			rs.close();
			psmt1.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	public NormalUserModel equeryN_UserByName(String nu_name){
		NormalUserModel NUM=null;
		//创建指针
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		//获取数据库连接
		String sql="select * from normaluser where nu_name=?";
		PreparedStatement psmt;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, nu_name);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()){
				NUM=new NormalUserModel();
				NUM.setNu_id(rs.getInt("nu_id"));
				NUM.setNu_name(rs.getString("nu_name"));
				NUM.setNu_telephone(rs.getString("nu_telephone"));
				NUM.setNu_address(rs.getString("nu_address"));
			}else{
				NUM=null;
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NUM;
	}
}
