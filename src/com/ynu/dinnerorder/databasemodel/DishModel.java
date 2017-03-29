package com.ynu.dinnerorder.databasemodel;

public class DishModel {
	/*
	 * @Author:余跃
	 * @Test:通过
	 */
	private int dish_id;//菜品的id
	private String dish_name;//菜品的名字
	private  double dish_price;//菜品的价格
	private String dish_description;//菜品的描述
	private String dish_image;//菜品的图片
	
	public DishModel(int dish_id, String dish_name, double dish_price, String dish_description, String dish_image) {
		super();
		this.dish_id = dish_id;
		this.dish_name = dish_name;
		this.dish_price = dish_price;
		this.dish_description = dish_description;
		this.dish_image = dish_image;
	}
	
	public DishModel(){
		
	}

	public int getDish_id() {
		return dish_id;
	}

	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}

	public String getDish_name() {
		return dish_name;
	}

	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}

	public double getDish_price() {
		return dish_price;
	}

	public void setDish_price(double dish_price) {
		this.dish_price = dish_price;
	}

	public String getDish_description() {
		return dish_description;
	}

	public void setDish_description(String dish_description) {
		this.dish_description = dish_description;
	}

	public String getDish_image() {
		return dish_image;
	}

	public void setDish_image(String dish_image) {
		this.dish_image = dish_image;
	}

	
}
