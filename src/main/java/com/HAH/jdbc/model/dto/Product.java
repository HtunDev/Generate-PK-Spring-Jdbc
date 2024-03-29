package com.HAH.jdbc.model.dto;

public class Product {

	private int id;
	private String name;
	private Category category;
	private int price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setCategoryId(int id) {
		if(null == category) {
			category = new Category();
		}
		category.setId(id);
	}

	public void setCategoryName(String Name) {
		if(null == category) {
			category = new Category();
		}
		category.setName(Name);
	}

}
