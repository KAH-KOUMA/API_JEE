package com.efficom.DAO;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.efficom.entities.Address;

@Stateless
@Local
public class AddressDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Address getContactAddress(int contactId) {
		TypedQuery<Address> query = this.entityManager.createQuery("SELECT a FROM Address a WHERE a.contactid = :contactid", Address.class);
        query.setParameter("contactid", contactId);
        return query.getResultList().stream().findFirst().orElse(null);
	}
	
	public Address createAddress(String road, String appartement, String zip, String city, String country, int contactId) {
		Address address = new Address();
		address.setAppartement(appartement);
		address.setCity(city);
		address.setCountry(country);
		address.setRoad(road);
		address.setZip(zip);
		address.setContactid(contactId);
		this.entityManager.persist(address);
		return this.getContactAddress(contactId);
	}
}
