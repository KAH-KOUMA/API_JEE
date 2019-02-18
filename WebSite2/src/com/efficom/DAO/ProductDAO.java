package com.efficom.DAO;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.efficom.entities.Product;

@Stateless
@Local
public class ProductDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Product> getAllProduct(){
		TypedQuery<Product> query = this.entityManager.createQuery("SELECT p FROM Product p ORDER BY p.stock DESC", Product.class);
        return query.getResultList();
	}
	
	public Product getProductbyId(int productId) {
		TypedQuery<Product> query = this.entityManager.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class);
        query.setParameter("id", productId);
        return query.getResultList().stream().findFirst().orElse(null);
	}
	
	public Product getProductByNameAndCategory(int category, String name) {
		TypedQuery<Product> query = this.entityManager.createQuery("SELECT p FROM Product p WHERE p.name = :name AND p.category = :category", Product.class);
        query.setParameter("name", name);
        query.setParameter("category", category);
        return query.getResultList().stream().findFirst().orElse(null);
	}
	
	public List<Product> searchByNameOrAndCategory(int category, String name) {
		TypedQuery<Product> query;
		if(category != 0 && name != "") {
			name = "%" + name + "%";
			query = this.entityManager.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name AND p.category = :category", Product.class);
	        query.setParameter("name", name);
	        query.setParameter("category", category);
		}else if(category == 0 && name != "") {
			name = "%" + name + "%";
			query = this.entityManager.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name", Product.class);
	        query.setParameter("name", name);
		}else {
			query = this.entityManager.createQuery("SELECT p FROM Product p WHERE p.category = :category", Product.class);
	        query.setParameter("category", category);
		}
		
        return query.getResultList();
	}
	
	public Product createProduct(String name, String description, int category, float price, int stock, String imageUrl) {
		Product product = new Product();
		product.setCategory(category);
		product.setDescription(description);
		product.setImageUrl(imageUrl);
		product.setName(name);
		product.setPrice(price);
		product.setStock(stock);
		
		this.entityManager.persist(product);
		
		return this.getProductByNameAndCategory(category, name);
	}
}
