package com.ynu.dinnerorder.databasemodel;

public class FlowModel {
	/*
	 * @Author:余跃
	 * @Test:通过
	 */
	
	private int Flow_id;//流水的id,没有外键的关联
	
	private OrderModel orderM;//外键关联的属性,包括订单的id,和订单的日期
	
	public FlowModel(){
		
	}

	public FlowModel(int flow_id, OrderModel orderM) {
		super();
		Flow_id = flow_id;
		this.orderM = orderM;
	}

	public int getFlow_id() {
		return Flow_id;
	}

	public void setFlow_id(int flow_id) {
		Flow_id = flow_id;
	}

	public OrderModel getOrderM() {
		return orderM;
	}

	public void setOrderM(OrderModel orderM) {
		this.orderM = orderM;
	}
	
	
}
