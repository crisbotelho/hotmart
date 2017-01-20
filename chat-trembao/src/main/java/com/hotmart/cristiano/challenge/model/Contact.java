package com.hotmart.cristiano.challenge.model;

import java.io.Serializable;

public class Contact implements Serializable{

	private static final long serialVersionUID = 4850967268122655730L;
	
	public Contact (){}
	
	public Contact (String userLogin, String contactLogin){
		this.userLogin = userLogin;
		this.contactLogin = contactLogin;
	}
	
	private String userLogin;
	private String contactLogin;
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getContactLogin() {
		return contactLogin;
	}
	public void setContactLogin(String contactLogin) {
		this.contactLogin = contactLogin;
	}
	
	

}
