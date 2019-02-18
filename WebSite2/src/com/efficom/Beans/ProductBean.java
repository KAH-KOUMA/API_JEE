package com.efficom.Beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.efficom.DAO.ProductDAO;
import com.efficom.utils.ProductView;
import com.efficom.utils.Utils;

import fr.efficom.entities.Product;

@Named
@RequestScoped
public class ProductBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String description;
	private int category;
	private float price;
	private int stock;
	private String imageUrl;
	private String categoryName;
	private int quantity;
	
	private boolean isUpdate = false;
	private String folder = System.getProperty("jboss.server.data.dir");
	
	@EJB
	ProductDAO productDao;
	
	public String displayProduct(String id){
		this.isUpdate = false;
		int res = Integer.parseInt(id);
		this.isUpdate = true;
		Product p = this.productDao.getProductbyId(res);
		ProductView pV = new ProductView(p);
		this.id = pV.getId();
		this.category = pV.getCategory();
		this.categoryName = pV.getCategoryName();
		this.description = pV.getDescription();
		this.imageUrl = pV.getImageUrl();
		this.name = pV.getName();
		this.price = pV.getPrice();
		this.stock = pV.getStock();
		return "Product";
	}
	
	public String updateProduct(String id){
		int res = Integer.parseInt(id);
		this.isUpdate = true;
		Product p = this.productDao.getProductbyId(res);
		ProductView pV = new ProductView(p);
		this.id = pV.getId();
		this.category = pV.getCategory();
		this.categoryName = pV.getCategoryName();
		this.description = pV.getDescription();
		this.imageUrl = pV.getImageUrl();
		this.name = pV.getName();
		this.price = pV.getPrice();
		this.stock = pV.getStock();
		return "Product";
	}

	public String commander() {
		
		return "Home";
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

	public ProductDAO getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
