package com.ynu.dinnerorder.databasedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.ynu.dinnerorder.databaseConnection.Jdbc;
import com.ynu.dinnerorder.databasemodel.DeskModel;

public class DeskDao {
	/*
	 * @Author:
	 * @Test:
	 */
	
	
	
	/*
	 * 管理员添加桌位到数据库中去
	 */
   public void addDesk(String desk_name,String desk_description){
	    Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql="insert into desk(desk_name,desk_description)values(?,?)";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setString(1, desk_name);
			psmt.setString(2, desk_description);
			psmt.execute();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
   }
   
   /*
    * 更新桌位的状态，根据桌位的名字
    */
   public boolean updateDeskState(int desk_state,String desk_name){
	   boolean b=false;
	   Jdbc j=new Jdbc();
	   Connection con=j.getConnection();
	   String sql="update desk set desk_state=? where desk_name=?";
	   try {
		PreparedStatement psmt=con.prepareStatement(sql);
		psmt.setInt(1, desk_state);
		psmt.setString(2, desk_name);
		psmt.execute();
		psmt.close();
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return b;
   }
   
   /*
    * 更新桌位状态通过桌位信息
    */
   public int updateDeskStateById(int desk_state,int desk_id){
	   int i=0;
	   Jdbc j=new Jdbc();
	   Connection con=j.getConnection();
	   String sql="update desk set desk_state=? where desk_id=?";
	   try {
		PreparedStatement psmt=con.prepareStatement(sql);
		psmt.setInt(1, desk_state);
		psmt.setInt(2, desk_id);
		
		i=psmt.executeUpdate();
		psmt.close();
		
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return i;
   }
   
   /*
    * 查看那些桌位预订状态，返回所有桌面信息
    */
	public List<DeskModel> equeryDesk(){
		 List<DeskModel> ldm=new ArrayList<DeskModel>();
		 DeskModel dm=null;
		 Jdbc j=new Jdbc();
		 Connection con=j.getConnection();
		 String sql="select * from desk where 1=1";
		 PreparedStatement psmt;
		try {
			psmt = con.prepareStatement(sql);
			 ResultSet rs=psmt.executeQuery();
			 while(rs.next()){
				 dm=new DeskModel();
				 dm.setDesk_id(rs.getInt("desk_id"));
				 dm.setDesk_name(rs.getString("desk_name"));
				 dm.setDesk_description(rs.getString("desk_description"));
				 dm.setDesk_state(rs.getInt("desk_state"));
				 ldm.add(dm);
			 }
			 psmt.close();
			 con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return ldm;
	}
	
	/*
	 * 删除桌位的所有信息通过桌面位的名字
	 */
	public boolean deleteDesk(String desk_name){
		boolean b=false;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql1="select * from desk where desk_name=?";
		String sql2="delete from desk where desk_name=?";
		PreparedStatement psmt1;
		PreparedStatement psmt2;
		try {
			psmt1=con.prepareStatement(sql1);
			psmt1.setString(1, desk_name);
			ResultSet rs=psmt1.executeQuery();
			if(rs.next()){
				b=true;
				psmt2=con.prepareStatement(sql2);
				psmt2.setString(1, desk_name);
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
	
	
	public DeskModel equeryDeskByName(String desk_name){
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql1="select * from desk where desk_name=?";
		DeskModel dm=null;
		PreparedStatement psmt1;
		try {
			psmt1=con.prepareStatement(sql1);
			psmt1.setString(1, desk_name);
			ResultSet rs=psmt1.executeQuery();
			if(rs.next()){
				dm=new DeskModel();
				dm.setDesk_id(rs.getInt("desk_id"));
				dm.setDesk_name(desk_name);
				dm.setDesk_description(rs.getString("desk_description"));
				dm.setDesk_state(rs.getInt("desk_state"));
			}else{
				dm=null;
			}
			rs.close();
			psmt1.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dm;
	}
	
	public DeskModel equeryDeskById(int desk_id){
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql1="select * from desk where desk_id=?";
		DeskModel dm=null;
		PreparedStatement psmt1;
		try {
			psmt1=con.prepareStatement(sql1);
			psmt1.setInt(1, desk_id);
			ResultSet rs=psmt1.executeQuery();
			if(rs.next()){
				dm=new DeskModel();
				dm.setDesk_id(rs.getInt("desk_id"));
				dm.setDesk_name(rs.getString("desk_name"));
				dm.setDesk_description(rs.getString("desk_description"));
				dm.setDesk_state(rs.getInt("desk_state"));
			}else{
				dm=null;
			}
			rs.close();
			psmt1.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dm;
	}
}
