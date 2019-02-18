package com.efficom.utils;

import javax.persistence.Column;

import fr.efficom.entities.Product;

public class ProductView {
	private int id;
	private String name;
	private String description;
	private int category;
	private float price;
	private int stock;
	private String imageUrl;
	private String categoryName;
	
	public ProductView() {
		
	}
	
	public ProductView(Product p) {
		this.category = p.getCategory();
		this.description = p.getDescription();
		this.id = p.getId();
		this.imageUrl = p.getImageUrl();
		this.name = p.getName();
		this.price = p.getPrice();
		this.stock = p.getStock();
		this.categoryName = this.defineCategoryName(p.getCategory());
	}
	
	private String defineCategoryName(int category) {
		return "Category " + category;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
