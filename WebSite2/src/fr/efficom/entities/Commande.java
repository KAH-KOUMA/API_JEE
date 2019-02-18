package fr.efficom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Commande")
public class Commande {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private int id;
	@Column
	private String commandeGUID;
	@Column
	private int clientId;
	@Column
	private int productId;
	@Column
	private int quantity;
	
	public Commande() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommandeGUIDr() {
		return commandeGUID;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
