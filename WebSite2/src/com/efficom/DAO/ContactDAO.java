package com.efficom.DAO;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.efficom.entities.Contact;

@Stateless
@Local
public class ContactDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Contact getContact(String email, String password) {
        TypedQuery<Contact> query = this.entityManager.createQuery("SELECT u FROM Contact u WHERE u.email = :email AND u.password = :password", Contact.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.getResultList().stream().findFirst().orElse(null);
	}
	
	public Contact createContact(String email, String lastName, String firstName, String password, String tel, int contactType) {
		Contact contact = new Contact();
		contact.setEmail(email);
		contact.setContactType(contactType);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setPassword(password);
		contact.setTel(tel);
		this.entityManager.persist(contact);
		
		return this.getContact(email, password);
	}
}
