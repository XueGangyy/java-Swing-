package com.ynu.dinnerorder.databasedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ynu.dinnerorder.databaseConnection.Jdbc;
import com.ynu.dinnerorder.databasemodel.DishModel;

public class DishDao {
	
	/*
	 * @Author:段小雪
	 * @Test:通过
	 */

	/*
	 * 管理员添加菜品,测试通过
	 */
	public void addDish(String dish_name,double dish_price,String dish_description,
			String dish_image)
	{
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql="insert into dish(dish_name,dish_price,dish_description,dish_image)values"
				+ "(?,?,?,?)";
		PreparedStatement psmt;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, dish_name);
			psmt.setDouble(2,dish_price);
			psmt.setString(3,dish_description);
			psmt.setString(4,dish_image);
			psmt.execute();
			
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 管理员通过菜品id来更新菜品信息，测试通过
	 */
	public boolean updateDishInfBy_Id(String dish_newname,double dish_newprice,String dish_newdescription,
			String dish_newimage,int dish_id){
		boolean b=false;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql1="select * from dish where dish_id=?";
		String sql2="update dish set dish_name=?,dish_price=?,dish_description=?,"
				+ "dish_image=? where dish_id=?";
		PreparedStatement psmt1;
		PreparedStatement psmt2;
		
		try {
			psmt1=con.prepareStatement(sql1);
			psmt1.setInt(1, dish_id);
			ResultSet rs=psmt1.executeQuery();
			if(rs.next()){
				b=true;
				psmt2=con.prepareStatement(sql2);
				psmt2.setString(1, dish_newname);
				psmt2.setDouble(2, dish_newprice);
				psmt2.setString(3, dish_newdescription);
				psmt2.setString(4, dish_newimage);
				psmt2.setInt(5, dish_id);
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
	
	
	/*
	 * 管理员通过菜品名字来更新菜品信息，测试通过
	 */
	public boolean updateDishInfBy_Name(String dish_newname,double dish_newprice,String dish_newdescription,
			String dish_newimage,String dish_name){
		boolean b=false;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql1="select * from dish where dish_name=?";
		String sql2="update dish set dish_name=?,dish_price=?,dish_description=?,"
				+ "dish_image=? where dish_id=?";
		PreparedStatement psmt1;
		PreparedStatement psmt2;
		
		try {
			psmt1=con.prepareStatement(sql1);
			psmt1.setString(1, dish_name);
			ResultSet rs=psmt1.executeQuery();
			if(rs.next()){
				b=true;
				psmt2=con.prepareStatement(sql2);
				psmt2.setString(1, dish_newname);
				psmt2.setDouble(2, dish_newprice);
				psmt2.setString(3, dish_newdescription);
				psmt2.setString(4, dish_newimage);
				psmt2.setString(5, dish_name);
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
	
	/*
	 * 根据id查出菜品信息,测试通过
	 */
	public DishModel equeryDishInfBy_id(int dish_id){
		DishModel dm=null;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql="select * from dish where dish_id=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setInt(1, dish_id);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()){
				dm=new DishModel();
				dm.setDish_id(dish_id);
				dm.setDish_name(rs.getString("dish_name"));
				dm.setDish_price(rs.getDouble("dish_price"));
				dm.setDish_description(rs.getString("dish_description"));
				dm.setDish_image(rs.getString("dish_image"));
			}else{
				dm=null;
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dm;
	}
	
	
	/*
	 * 通过菜品名字查出菜品的信息
	 */
	public List<DishModel> equeryDishInfBy_name(String dish_name){
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		List<DishModel> ldm=new ArrayList<DishModel>();
		DishModel dm=null;
		String sql="select * from dish where dish_name like ? ORDER BY dish_price DESC";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setString(1, "%"+dish_name+"%");
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				dm=new DishModel();
				dm.setDish_id(rs.getInt("dish_id"));
				dm.setDish_name(rs.getString("dish_name"));
				dm.setDish_price(rs.getDouble("dish_price"));
				dm.setDish_description(rs.getString("dish_description"));
				dm.setDish_image(rs.getString("dish_image"));
				ldm.add(dm);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ldm;
		
	}
	
	
	/*
	 * 查询出所有菜品的信息，测试通过
	 */
	public List<DishModel> equeryAllDish(){
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		List<DishModel> ldm=new ArrayList<DishModel>();
		DishModel dm=null;
		String sql="select * from dish where 1=1";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				dm=new DishModel();
				dm.setDish_id(rs.getInt("dish_id"));
				dm.setDish_name(rs.getString("dish_name"));
				dm.setDish_price(rs.getDouble("dish_price"));
				dm.setDish_description(rs.getString("dish_description"));
				dm.setDish_image(rs.getString("dish_image"));
				ldm.add(dm);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ldm;
	}
	
	/*
	 * 根据菜品id删除菜品所有信息,测试通过
	 * 
	 */
	public boolean deleteDish(int dish_id){
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		boolean b=false;
		String sql1="select * from dish where dish_id=?";
		String sql2="delete from dish where dish_id=?";
		PreparedStatement psmt1;
		PreparedStatement psmt2;
		
		try {
			psmt1=con.prepareStatement(sql1);
			psmt1.setInt(1, dish_id);
			ResultSet rs=psmt1.executeQuery();
			if(rs.next()){
				b=true;
				psmt2=con.prepareStatement(sql2);
				psmt2.setInt(1, dish_id);
				psmt2.execute();
				psmt2.close();
			}else{
				b=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return b;	
	}
}
