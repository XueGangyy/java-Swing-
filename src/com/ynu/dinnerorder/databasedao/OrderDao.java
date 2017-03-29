package com.ynu.dinnerorder.databasedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ynu.dinnerorder.databaseConnection.Jdbc;
import com.ynu.dinnerorder.databasemodel.OrderModel;


public class OrderDao {

	/*
	 * 添加订单,作为流水查看
	 */
	public int addOrder(Date order_date,String nu_name,double totalprice,String OrderId,String desk_name,String u_address
			,String u_telephone,String u_dish){
		int i=0;
		  Jdbc j=new Jdbc();
		 Connection con=j.getConnection();

		 
		 String sql="insert into orderTable(dingdan,totalprice,order_time,n_name,d_name,u_address,u_telephone"
		 		+ ",u_dish)values(?,?,?,?,?,?,?,?)";
		 
		 try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setString(1, OrderId);
			psmt.setDouble(2, totalprice);
			Timestamp sqlTimestamp=new Timestamp(order_date.getTime());
			psmt.setTimestamp(3, sqlTimestamp);
			psmt.setString(4, nu_name);
			psmt.setString(5, desk_name);
			psmt.setString(6,u_address);
			psmt.setString(7, u_telephone);
			psmt.setString(8, u_dish);
			

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
	 * 删除流水
	 */
	public int deleteOrder(int order_id){
		int i=0;
		Jdbc j=new Jdbc();
		 Connection con=j.getConnection();
		 String sql2="delete from orderTable where order_id=?";
		 PreparedStatement psmt2;
		 try {
			
				psmt2=con.prepareStatement(sql2);
				psmt2.setInt(1, order_id);
			i=psmt2.executeUpdate();
			psmt2.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return i;
	}
	
	/*
	 * 根据某天来查询当天的所有流水
	 */
	public List<OrderModel> queryByDay(Date day1,Date day2){
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		Timestamp t=new Timestamp(day1.getTime());
		Timestamp t1=new Timestamp(day2.getTime());
		List<OrderModel> lom=new ArrayList<OrderModel>();
		OrderModel OM=null;
		String sql="select * from orderTable where order_time between ? and ?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setTimestamp(1, t);
			psmt.setTimestamp(2, t1);
			
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				OM=new OrderModel();
				OM.setOrder_id(rs.getInt("order_id"));
				OM.setOrderId(rs.getString("dingdan"));
				OM.setTime(rs.getTimestamp("order_time"));
				OM.setTotalprice(rs.getDouble("totalprice"));
				OM.setNu_name(rs.getString("n_name"));
				OM.setDesk_name(rs.getString("d_name"));
				OM.setU_dish(rs.getString("u_dish"));
				OM.setU_telephone(rs.getString("u_telephone"));
				OM.setU_address(rs.getString("u_address"));
				lom.add(OM);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lom;
	}
}
