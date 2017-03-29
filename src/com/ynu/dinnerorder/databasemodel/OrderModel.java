package com.ynu.dinnerorder.databasemodel;

import java.util.Date;

public class OrderModel {
	/*
	 * @Author:余跃
	 * @Test:通过
	 */

	/*
	 * 本来就应该属于该model的属性，没有外键约束
	 */
	private int order_id;//订单的id
	private String OrderId;//订单的总价格
	private double totalprice;
	private Date time;
	private String nu_name;
	private String desk_name;
	private String u_address;
	private String u_telephone;
	private String u_dish;
	
	/*
	 * 本来不属于该model的属性，有外键约束的
	 */
	//只需要一个itemM对象就可以关联两张表，因为order的外键都是基于Item表的
	//这个对象下面有订单用到的属性：菜品的id,桌位id，普通用户的id,vip用户的id,菜品下订单的时间
	
	public String getU_address() {
		return u_address;
	}

	public void setU_address(String u_address) {
		this.u_address = u_address;
	}

	public String getU_telephone() {
		return u_telephone;
	}

	public void setU_telephone(String u_telephone) {
		this.u_telephone = u_telephone;
	}

	public String getU_dish() {
		return u_dish;
	}

	public void setU_dish(String u_dish) {
		this.u_dish = u_dish;
	}

	public String getDesk_name() {
		return desk_name;
	}

	public void setDesk_name(String desk_name) {
		this.desk_name = desk_name;
	}

	public OrderModel(){
		
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getNu_name() {
		return nu_name;
	}

	public void setNu_name(String nu_name) {
		this.nu_name = nu_name;
	}

	
		
}
