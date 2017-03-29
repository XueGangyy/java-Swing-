package com.ynu.dinnerorder.databasedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ynu.dinnerorder.databaseConnection.Jdbc;
import com.ynu.dinnerorder.databasemodel.WaiterModel;
/*
 * @Author:段小雪
 * @TestState:测试通过
 */
public class WaiterDao {
	
	
	/*
	 * 服务员登录
	 */
	public WaiterModel loginWaiter(String w_name,String w_password){
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		//获取数据库连接
		WaiterModel wm=null;
		String sql="select waiter.w_id,waiter.w_name,waiter.w_password,"
				+ "waiter.w_telephone,waiter.w_actor,waiter.w_sex from "
				+ "waiter where w_name=? and w_password=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			//执行sql语句
			psmt.setString(1,w_name);
			//设置参数1
			psmt.setString(2, w_password);
			//设置参数2
			ResultSet rs=psmt.executeQuery();
			//进行查询
			if(rs.next()){
				//如果查询结果存在，那么创建WaiterModel对象，并且赋值
				wm=new WaiterModel();
				wm.setW_id(rs.getInt("w_id"));
				wm.setW_name(rs.getString("w_name"));
				wm.setW_password(rs.getString("w_password"));
				wm.setW_telephone(rs.getString("w_telephone"));
				wm.setW_actor(rs.getString("w_actor"));
				wm.setW_sex(rs.getString("w_sex"));
			}else{
				//如果查询结果不存在，那么则不创建对象
				wm=null;
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wm;
	}

	
	/*
	 * 服务员注册
	 */
	public boolean registerWaiter(String w_name,
			String w_password,
			String w_telephone,
			String w_actor,String w_sex)
	{
		boolean b=false;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql1="select waiter.w_name from waiter where w_name=?";
		String sql2="insert into waiter(w_name,w_password,w_telephone,"
				+ "w_actor,w_sex)values(?,?,?,?,?)";
		try {
			PreparedStatement psmt1=con.prepareStatement(sql1);
			//先执行语句1，查询用户名是否已经存在
			psmt1.setString(1, w_name);
			//
			ResultSet rs=psmt1.executeQuery();
			if(rs.next()){
				//用户名存在，把b赋值为false
				b=false;
			}else{
				//用户名不存在，把b赋值为true
				b=true;
			PreparedStatement psmt2=con.prepareStatement(sql2);
			psmt2.setString(1, w_name);
			psmt2.setString(2, w_password);
			psmt2.setString(3, w_telephone);
			psmt2.setString(4, w_actor);
			psmt2.setString(5, w_sex);
			psmt2.execute();
			psmt2.close();
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
	
	
	/*
	 * 删除服务员所有信息
	 */
	public boolean deleteWaiter(int w_id){
		boolean b=false;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql1="select waiter.w_id from waiter where w_id=?";
		String sql2="delete from waiter where w_id=?";
		PreparedStatement psmt1;
		PreparedStatement psmt2;
		try {
			psmt1 = con.prepareStatement(sql1);
			//先执行语句1，查询用户是否已经存在
			psmt1.setInt(1, w_id);
			//设置参数
			ResultSet rs=psmt1.executeQuery();
			if(rs.next()){
				//如果id不存在那么设置b为false
				//如果id存在那么设置b为true
				b=true;
				psmt2= con.prepareStatement(sql2);
				psmt2.setInt(1, w_id);
				psmt2.execute();
				//执行操作
				psmt2.close();
			}else{
				//如果id存在那么设置b为true
				b=false;
				
			}
			rs.close();
			psmt1.close();
			con.close();
			//关闭所有数据库资源
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	
	/*
	 * 更新服务员信息
	 */
	public WaiterModel updateWaiter(String w_newname,
			String w_newpassword,
			String w_newtelephone,
			String w_newactor,int w_id){
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		
		WaiterModel wm=null;
		
		String sql="update waiter set w_name=?,w_password=?,w_telephone=?,"
				+ "w_actor=? where w_id=?";
		//对数据库中数据进行更新
		PreparedStatement psmt2;
		try {
			
				wm=new WaiterModel();
				psmt2=con.prepareStatement(sql);
				psmt2.setString(1, w_newname);
				psmt2.setString(2, w_newpassword);
				psmt2.setString(3,w_newtelephone);
				psmt2.setString(4,w_newactor);
				
				psmt2.setInt(5,w_id);
				wm.setW_name(w_newname);
				wm.setW_password(w_newpassword);
				wm.setW_telephone(w_newtelephone);
				wm.setW_actor(w_newactor);
				
				psmt2.execute();
				//执行操作
				psmt2.close();
				//关闭执行操作
			
			con.close();
			//关闭所有数据库资源
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wm;
	}
}
