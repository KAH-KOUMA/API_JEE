package com.efficom.Beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.Column;
import javax.servlet.http.HttpSession;

import com.efficom.DAO.AddressDAO;
import com.efficom.DAO.ContactDAO;
import com.efficom.Session.ContactSession;

import fr.efficom.entities.Address;
import fr.efficom.entities.Contact;

@Named("contactBean")
@RequestScoped
public class ContactBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String contactLastName;
	private String contactFirstName;
	private String contactEmail;
	private String contactPassword;
	private String contactTel;
	private int contactType;
	private String road;
	private String appartement;
	private String zip;
	private String city;
	private String country;

	
	@EJB
    ContactDAO contactDao;
	@EJB
	AddressDAO addressDao;
	
	public String register() {
        Contact entity = contactDao.createContact(this.contactEmail, this.contactLastName, this.contactFirstName, this.contactPassword, this.contactTel, this.contactType);
        if(entity == null) {
        	return "Register";
        }
        Address address = this.addressDao.createAddress(this.road, this.appartement, this.zip, this.city, this.country, entity.getId());
        if(address == null) {
        	return "Register";
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        ContactSession contactlogged = new ContactSession(entity.getId(), entity.getLastName(), entity.getFirstName(), entity.getEmail(), entity.getPassword(), entity.getTel(), entity.getContactType());
        session.setAttribute("contactLogged", contactlogged);
        return "Home";
    }
	
	public String connect() {
        Contact entity = contactDao.getContact(this.contactEmail, this.contactPassword);
        if(entity == null) {
            return "SignIn";
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        ContactSession contactlogged = new ContactSession(entity.getId(), entity.getLastName(), entity.getFirstName(), entity.getEmail(), entity.getPassword(), entity.getTel(), entity.getContactType());
        session.setAttribute("contactLogged", contactlogged);
        return "Home";
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPassword() {
		return contactPassword;
	}

	public void setContactPassword(String contactPassword) {
		this.contactPassword = contactPassword;
	}

	public int getContactType() {
		return contactType;
	}

	public void setContactType(int contactType) {
		this.contactType = contactType;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public ContactDAO getContactDao() {
		return contactDao;
	}

	public void setContactDao(ContactDAO contactDao) {
		this.contactDao = contactDao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getAppartement() {
		return appartement;
	}

	public void setAppartement(String appartement) {
		this.appartement = appartement;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
