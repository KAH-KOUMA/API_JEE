package com.efficom.Beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.efficom.DAO.ProductDAO;
import com.efficom.utils.ProductView;
import com.efficom.utils.Utils;

import fr.efficom.entities.Product;

@Named
@RequestScoped
public class EntrepriseBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String productName;
	private String productDescription;
	private int productCategory;
	private float productPrice;
	private int productStock;
	private String productImg = "test.jpg";
	private Part file;
	private String path = System.getProperty("jboss.server.data.dir");
	
	private String imgDirectory;
	private List<Product> products;
	private List<ProductView> productsView;
	private boolean addProduct = false;
	private ProductView productView;
	
	@EJB
	ProductDAO productDao;
	
	@PostConstruct
	public void init() {
		final HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        this.imgDirectory = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img/";
		refresh();
	}

	private void refresh() {
		this.productsView = new ArrayList<ProductView>();
		this.products = this.productDao.getAllProduct();
		for(Product p: this.products) {
			this.productsView.add(new ProductView(p));
		}
	}
	
	public void saveFile() {
		try (InputStream input = this.file.getInputStream()) {
            this.productImg= Utils.generateString(10);
            Files.copy(input, new File(this.path, this.productImg).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public String addingProduct() {
		this.saveFile();
		this.productDao.createProduct(productName, productDescription, productCategory, productPrice, productStock, productImg);
		this.addProduct = false;
		this.refresh();
		return "Entreprise";
	}
	
	public String searchByHome() {
		this.productsView = new ArrayList<ProductView>();
		this.products = this.productDao.searchByNameOrAndCategory(this.productCategory, this.productName);
		for(Product p: this.products) {
			this.productsView.add(new ProductView(p));
		}
		return "Home";
	}
	
	public String searchByEntreprise() {
		this.productsView = new ArrayList<ProductView>();
		this.products = this.productDao.searchByNameOrAndCategory(productCategory, productName);
		for(Product p: this.products) {
			this.productsView.add(new ProductView(p));
		}
		this.addProduct = false;
		return "Entreprise";
	}

	public String enableAddProduct() {
		this.addProduct = true;
		return "Entreprise";
	}
	
	public String disableAddProduct() {
		this.addProduct = false;
		return "Entreprise";
	}

	public String displayProduct(String pId) {
		
		return "Product";
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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

	public boolean isAddProduct() {
		return addProduct;
	}

	public void setAddProduct(boolean addProduct) {
		this.addProduct = addProduct;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(int productCategory) {
		this.productCategory = productCategory;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public List<ProductView> getProductsView() {
		return productsView;
	}

	public void setProductsView(List<ProductView> productsView) {
		this.productsView = productsView;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public ProductView getProductView() {
		return productView;
	}

	public void setProductView(ProductView productView) {
		this.productView = productView;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getImgDirectory() {
		return imgDirectory;
	}

	public void setImgDirectory(String imgDirectory) {
		this.imgDirectory = imgDirectory;
	}
}
