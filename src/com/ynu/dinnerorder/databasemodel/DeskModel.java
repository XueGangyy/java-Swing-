package com.ynu.dinnerorder.databasemodel;

public class DeskModel {
	/*
	 * @Author:余跃
	 * @Test:通过
	 */
	private int desk_id;//桌位的id
	private String desk_name;//桌位的位置
	private String desk_description;//该桌位的描述
	private int desk_state;//判断桌位是否已经被别人占用
	
	public DeskModel(){
		
	}
	
	public DeskModel(int desk_id, String desk_name, String desk_description, int desk_state) {
		super();
		this.desk_id = desk_id;
		this.desk_name = desk_name;
		this.desk_description = desk_description;
		this.desk_state = desk_state;
	}
	
	
	public int getDesk_id() {
		return desk_id;
	}
	public void setDesk_id(int desk_id) {
		this.desk_id = desk_id;
	}
	public String getDesk_name() {
		return desk_name;
	}
	public void setDesk_name(String desk_name) {
		this.desk_name = desk_name;
	}
	public String getDesk_description() {
		return desk_description;
	}
	public void setDesk_description(String desk_description) {
		this.desk_description = desk_description;
	}
	public int getDesk_state() {
		return desk_state;
	}
	public void setDesk_state(int desk_state) {
		this.desk_state = desk_state;
	}
	
}
