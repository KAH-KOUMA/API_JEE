package com.efficom.Session;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

@ManagedBean
@SessionScoped
public class ContactSession implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int contactId;
	private String contactLastName;
	private String contactFirstName;
	private String contactEmail;
	private String contactPassword;
	private String contactTel;
	private int contactType;
	
	public ContactSession() {
		
	}
	
	public ContactSession(int id, String lastName, String firstName, String email, String password, String tel, int contactType) {
		this.contactType = contactType;
		this.contactEmail = email;
		this.contactFirstName = firstName;
		this.contactId = id;
		this.contactLastName = lastName;
		this.contactFirstName = firstName;
		this.contactPassword = password;
		this.contactTel = tel;
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
	
	public String getContactTel() {
		return contactTel;
	}
	
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	
	public int getContactType() {
		return contactType;
	}
	
	public void setContactType(int contactType) {
		this.contactType = contactType;
	}
}
