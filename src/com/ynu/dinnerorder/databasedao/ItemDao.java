package com.ynu.dinnerorder.databasedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import com.ynu.dinnerorder.databaseConnection.Jdbc;
import com.ynu.dinnerorder.databasemodel.DeskModel;
import com.ynu.dinnerorder.databasemodel.DishModel;
import com.ynu.dinnerorder.databasemodel.ItemModel;
import com.ynu.dinnerorder.databasemodel.NormalUserModel;

public class ItemDao {
	
	/*
	 * 添加物品栏
	 */
	public int addItem(Date Item_date,int Item_num,int nu_id,int dish_id)
	{    int i = 0;
		 Jdbc j=new Jdbc();
		 Connection con=j.getConnection();
		 String sql="insert into item(Item_date,Item_num,nu_id,dish_id)values(?,?,?,?)";
		 try {
			PreparedStatement psmt=con.prepareStatement(sql);
			Timestamp sqlTimestamp=new Timestamp(Item_date.getTime());
			psmt.setTimestamp(1, sqlTimestamp);
			psmt.setInt(2, Item_num);
			psmt.setInt(3, nu_id);
			
			psmt.setInt(4, dish_id);
			
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
	 * 根据id删除所有信息
	 */
	public int deleteItem(int item_id){
		int i=0;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql="delete from item where Item_id=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setInt(1, item_id);
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
	 * 根据id和状态更新订单状态
	 */
	public int updateState(int item_id,int item_state){
		int i=0;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql="update item set Item_state=? where Item_id=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setInt(1,item_state);
			psmt.setInt(2, item_id);
			i=psmt.executeUpdate();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	//查询流水，根据年月日来算
	public List<ItemModel> equeryItemByDay(Date now,Date next){
		List<ItemModel> lim=new ArrayList<ItemModel>();
		Timestamp t=new Timestamp(now.getTime());
		Timestamp t1=new Timestamp(next.getTime());
		System.out.println(t);
		System.out.println(t1);
		ItemModel im=null;
		Jdbc j=new Jdbc();
		 Connection con=j.getConnection();
		 String sql1="select * from item where Item_date between ? and ?";
		 String sql2="";
		 try {
			PreparedStatement psmt=con.prepareStatement(sql1);
			psmt.setTimestamp(1, t);
			psmt.setTimestamp(2, t1);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				im=new ItemModel();
				im.setItem_id(rs.getInt("Item_id"));
				im.setItem_num(rs.getInt("Item_num"));
				im.setItem_date(rs.getTimestamp("Item_date"));
				im.setItem_state(rs.getInt("Item_state"));
				
				DishDao DD=new DishDao();
				DishModel dm=DD.equeryDishInfBy_id(rs.getInt("dish_id"));
				im.setDishm(dm);
				DeskDao deskDao=new DeskDao();
				DeskModel desk=deskDao.equeryDeskById(rs.getInt("desk_id"));
				im.setDeskm(desk);
				NormalUserDao nud=new NormalUserDao();
				NormalUserModel num=nud.equeryN_User(rs.getInt("nu_id"));
				im.setN_user_m(num);
				
				im.setOrderId(rs.getString("OrderId"));
				im.setItem_totalprice(rs.getDouble("Item_totalprice"));
				
			    lim.add(im);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lim;
	}
	
	/*
	 * 根据item_id查询所有信息
	 */
	public ItemModel equeryItemById(int Item_id){
		ItemModel im=null;
		Jdbc j=new Jdbc();
		 Connection con=j.getConnection();
		 String sql1="select * from item where Item_id=?";
		 try {
			PreparedStatement psmt=con.prepareStatement(sql1);
			psmt.setInt(1, Item_id);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()){
				im=new ItemModel();
				im.setItem_id(rs.getInt("Item_id"));
				im.setItem_num(rs.getInt("Item_num"));
				im.setItem_date(rs.getTimestamp("Item_date"));
				im.setItem_state(rs.getInt("Item_state"));
				DeskDao deskDao=new DeskDao();
				DeskModel desk=deskDao.equeryDeskById(rs.getInt("desk_id"));
				im.setDeskm(desk);
				DishDao DD=new DishDao();
				DishModel dm=DD.equeryDishInfBy_id(rs.getInt("dish_id"));
				im.setDishm(dm);
				
				im.setOrderId(rs.getString("OrderId"));
				im.setItem_totalprice(rs.getDouble("Item_totalprice"));
				NormalUserDao nud=new NormalUserDao();
				NormalUserModel num=nud.equeryN_User(rs.getInt("nu_id"));
				im.setN_user_m(num);
			}else{
				im=null;
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return im;
	}
	
	/*
	 * 查询所有订单的信息
	 */
	public List<ItemModel> equeryItemAll(){
		List<ItemModel> lim=new ArrayList<ItemModel>();
		ItemModel im=null;
		Jdbc j=new Jdbc();
		 Connection con=j.getConnection();
		 String sql1="select * from item where 1=1";
		 String sql2="";
		 try {
			PreparedStatement psmt=con.prepareStatement(sql1);
			
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				im=new ItemModel();
				im.setItem_id(rs.getInt("Item_id"));
				im.setItem_num(rs.getInt("Item_num"));
				im.setItem_date(rs.getTimestamp("Item_date"));
				im.setItem_state(rs.getInt("Item_state"));
				
				DishDao DD=new DishDao();
				DishModel dm=DD.equeryDishInfBy_id(rs.getInt("dish_id"));
				im.setDishm(dm);
				DeskDao deskDao=new DeskDao();
				DeskModel desk=deskDao.equeryDeskById(rs.getInt("desk_id"));
				im.setDeskm(desk);
				NormalUserDao nud=new NormalUserDao();
				NormalUserModel num=nud.equeryN_User(rs.getInt("nu_id"));
				im.setN_user_m(num);
				im.setOrderId(rs.getString("OrderId"));
				im.setItem_totalprice(rs.getDouble("Item_totalprice"));
			    lim.add(im);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lim;
	}
	
	
	/*
	 * 根据用户id来查询所有的订单
	 */
	public List<ItemModel> equeryItemByUserId(int u_id){
		List<ItemModel> lim=new ArrayList<ItemModel>();
		ItemModel im=null;
		Jdbc j=new Jdbc();
		 Connection con=j.getConnection();
		 String sql1="select * from item where nu_id=?";
		 String sql2="";
		 try {
			PreparedStatement psmt=con.prepareStatement(sql1);
			psmt.setInt(1, u_id);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				im=new ItemModel();
				im.setItem_id(rs.getInt("Item_id"));
				im.setItem_num(rs.getInt("Item_num"));
				im.setItem_date(rs.getTimestamp("Item_date"));
				im.setItem_state(rs.getInt("Item_state"));
				
				DishDao DD=new DishDao();
				DishModel dm=DD.equeryDishInfBy_id(rs.getInt("dish_id"));
				im.setDishm(dm);
				DeskDao deskDao=new DeskDao();
				DeskModel desk=deskDao.equeryDeskById(rs.getInt("desk_id"));
				im.setDeskm(desk);
				NormalUserDao nud=new NormalUserDao();
				NormalUserModel num=nud.equeryN_User(rs.getInt("nu_id"));
				im.setN_user_m(num);
				im.setOrderId(rs.getString("OrderId"));
				im.setItem_totalprice(rs.getDouble("Item_totalprice"));
			    lim.add(im);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lim;
	}
	
	
	/*
	 * 根据用户的id和订单的状态来查询所有订单的信息
	 */
	public List<ItemModel> equeryItemByUserId_state(int u_id,int state){
		List<ItemModel> lim=new ArrayList<ItemModel>();
		ItemModel im=null;
		Jdbc j=new Jdbc();
		 Connection con=j.getConnection();
		 String sql1="select * from item where nu_id=? and Item_state=?";
		 String sql2="";
		 try {
			PreparedStatement psmt=con.prepareStatement(sql1);
			psmt.setInt(1, u_id);
			psmt.setInt(2, state);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				im=new ItemModel();
				im.setItem_id(rs.getInt("Item_id"));
				im.setItem_num(rs.getInt("Item_num"));
				im.setItem_date(rs.getTimestamp("Item_date"));
				im.setItem_state(rs.getInt("Item_state"));
				
				DishDao DD=new DishDao();
				DishModel dm=DD.equeryDishInfBy_id(rs.getInt("dish_id"));
				im.setDishm(dm);
				DeskDao deskDao=new DeskDao();
				DeskModel desk=deskDao.equeryDeskById(rs.getInt("desk_id"));
				im.setDeskm(desk);
				NormalUserDao nud=new NormalUserDao();
				NormalUserModel num=nud.equeryN_User(rs.getInt("nu_id"));
				im.setN_user_m(num);
				im.setOrderId(rs.getString("OrderId"));
				im.setItem_totalprice(rs.getDouble("Item_totalprice"));
			    lim.add(im);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lim;
	}
	
	
	/*
	 * 根据订单id和用户id来更新总价格
	 */
	public int updateTotalPrice(int dish_id,int nu_id){
		int i=0;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		DishDao DD=new DishDao();
		DishModel DM=DD.equeryDishInfBy_id(dish_id);
		String sql="update item set Item_totalprice=Item_num*? where dish_id=? and nu_id=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setDouble(1, DM.getDish_price());
			psmt.setInt(2, dish_id);
			psmt.setInt(3, nu_id);
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
	 * 更新桌位，或者说添加桌位到订单表里面
	 */
	public int AddDesk(int desk_id,int nu_id,int Item_state){
		int i=0;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql="update item set desk_id=? where nu_id=? and Item_state=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setInt(1, desk_id);
			psmt.setInt(2,nu_id);
			psmt.setInt(3, Item_state);
			i=psmt.executeUpdate();
			
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DeskDao desk=new DeskDao();
		desk.updateDeskStateById(1, desk_id);
		return i;
	}
	
	
	/*
	 * 写入唯一识别的订单id
	 */
	public int AddOrderId(String order_id,int nu_id,int Item_state){
		 int i=0;
		 Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql="update item set OrderId=? where nu_id=? and Item_state=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setString(1, order_id);
			psmt.setInt(2, nu_id);
			psmt.setInt(3, Item_state);
			
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
	 * 根据用户id和状态查询
	 */
	public int UpdateStateByAll(int nu_id,int oldstate,int nstate){
		
		int i=0;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql="update item set Item_state=? where Item_state=? and nu_id=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setInt(1,nstate);
			psmt.setInt(2, oldstate);
			psmt.setInt(3, nu_id);
			
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
	 * 根据用户状态
	 */
	public List<ItemModel> equeryItemByState(int state){
		List<ItemModel> lim=new ArrayList<ItemModel>();
		ItemModel im=null;
		Jdbc j=new Jdbc();
		 Connection con=j.getConnection();
		 String sql1="select * from item where Item_state=? ORDER BY Item_id DESC";
		 String sql2="";
		 try {
			PreparedStatement psmt=con.prepareStatement(sql1);
			psmt.setInt(1, state);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				im=new ItemModel();
				im.setItem_id(rs.getInt("Item_id"));
				im.setItem_num(rs.getInt("Item_num"));
				im.setItem_date(rs.getTimestamp("Item_date"));
				im.setItem_state(rs.getInt("Item_state"));
				
				DishDao DD=new DishDao();
				DishModel dm=DD.equeryDishInfBy_id(rs.getInt("dish_id"));
				im.setDishm(dm);
				DeskDao deskDao=new DeskDao();
				DeskModel desk=deskDao.equeryDeskById(rs.getInt("desk_id"));
				im.setDeskm(desk);
				NormalUserDao nud=new NormalUserDao();
				NormalUserModel num=nud.equeryN_User(rs.getInt("nu_id"));
				im.setN_user_m(num);
				im.setOrderId(rs.getString("OrderId"));
				im.setItem_totalprice(rs.getDouble("Item_totalprice"));
			    lim.add(im);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lim;
	}
	
	/*
	 * 根据用户姓名查看所有信息
	 */
	public List<ItemModel> equeryItemByUserName(String nu_name){
		List<ItemModel> lim=new ArrayList<ItemModel>();
		ItemModel im=null;
		Jdbc j=new Jdbc();
		 Connection con=j.getConnection();
		 NormalUserDao NUD=new NormalUserDao();
		 NormalUserModel NUM=NUD.equeryN_UserByName(nu_name);
		 if(NUM==null){
			 JOptionPane.showMessageDialog(null, "用户名不存在哦,请重新输入");
		 }else{
		 String sql1="select * from item where nu_id=? ORDER BY Item_id DESC";
		 try {
			PreparedStatement psmt=con.prepareStatement(sql1);
			psmt.setInt(1, NUM.getNu_id());
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				im=new ItemModel();
				im.setItem_id(rs.getInt("Item_id"));
				im.setItem_num(rs.getInt("Item_num"));
				im.setItem_date(rs.getTimestamp("Item_date"));
				im.setItem_state(rs.getInt("Item_state"));
				
				DishDao DD=new DishDao();
				DishModel dm=DD.equeryDishInfBy_id(rs.getInt("dish_id"));
				im.setDishm(dm);
				DeskDao deskDao=new DeskDao();
				DeskModel desk=deskDao.equeryDeskById(rs.getInt("desk_id"));
				im.setDeskm(desk);
				NormalUserDao nud=new NormalUserDao();
				NormalUserModel num=nud.equeryN_User(rs.getInt("nu_id"));
				im.setN_user_m(num);
				im.setOrderId(rs.getString("OrderId"));
				im.setItem_totalprice(rs.getDouble("Item_totalprice"));
			    lim.add(im);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		 return lim;
	}
	
	/*
	 * 根据订单唯一识别的id来更新订单状态
	 */
public int UpdateStateByOrderId(String OrderId,int state){
		
		int i=0;
		Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql="update item set Item_state=? where OrderId=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setInt(1,state);
			psmt.setString(2, OrderId);
			
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
 * 根据唯一识别的id和桌位的状态来查询所有的订单信息
 */
 public List<ItemModel> equeryByOrderId(String OrderId,int state){
	     ItemModel im=null;
	     List<ItemModel> lim=new ArrayList<ItemModel>();
	     Jdbc j=new Jdbc();
		Connection con=j.getConnection();
		String sql="select * from item where OrderId=? and Item_state=?";
		try {
			PreparedStatement psmt=con.prepareStatement(sql);
			psmt.setString(1,OrderId);
			psmt.setInt(2, state);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				im=new ItemModel();
				im.setItem_id(rs.getInt("Item_id"));
				im.setItem_num(rs.getInt("Item_num"));
				im.setItem_date(rs.getTimestamp("Item_date"));
				im.setItem_state(rs.getInt("Item_state"));
				
				DishDao DD=new DishDao();
				DishModel dm=DD.equeryDishInfBy_id(rs.getInt("dish_id"));
				im.setDishm(dm);
				DeskDao deskDao=new DeskDao();
				DeskModel desk=deskDao.equeryDeskById(rs.getInt("desk_id"));
				im.setDeskm(desk);
				NormalUserDao nud=new NormalUserDao();
				NormalUserModel num=nud.equeryN_User(rs.getInt("nu_id"));
				im.setN_user_m(num);
				im.setOrderId(rs.getString("OrderId"));
				im.setItem_totalprice(rs.getDouble("Item_totalprice"));
				lim.add(im);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lim;
 }
 
 /*
  * 根据订单唯一识别的ＩＤ来查询出所有的订单的信息
  */
 public List<ItemModel> equeryByOrderId(String OrderId){
     ItemModel im=null;
     List<ItemModel> lim=new ArrayList<ItemModel>();
     Jdbc j=new Jdbc();
	Connection con=j.getConnection();
	String sql="select * from item where OrderId=?";
	try {
		PreparedStatement psmt=con.prepareStatement(sql);
		psmt.setString(1,OrderId);
		
		ResultSet rs=psmt.executeQuery();
		while(rs.next()){
			im=new ItemModel();
			im.setItem_id(rs.getInt("Item_id"));
			im.setItem_num(rs.getInt("Item_num"));
			im.setItem_date(rs.getTimestamp("Item_date"));
			im.setItem_state(rs.getInt("Item_state"));
			
			DishDao DD=new DishDao();
			DishModel dm=DD.equeryDishInfBy_id(rs.getInt("dish_id"));
			im.setDishm(dm);
			DeskDao deskDao=new DeskDao();
			DeskModel desk=deskDao.equeryDeskById(rs.getInt("desk_id"));
			im.setDeskm(desk);
			NormalUserDao nud=new NormalUserDao();
			NormalUserModel num=nud.equeryN_User(rs.getInt("nu_id"));
			im.setN_user_m(num);
			im.setOrderId(rs.getString("OrderId"));
			im.setItem_totalprice(rs.getDouble("Item_totalprice"));
			lim.add(im);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return lim;
}
}
