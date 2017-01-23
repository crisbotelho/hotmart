package com.hotmart.cristiano.challenge.model;

import java.io.Serializable;

public class Contact implements Serializable{

	private static final long serialVersionUID = 4850967268122655730L;
	
	public Contact (){}
	
	public Contact (Long id, String userLogin, String contactLogin, String offlineMessages){
		this.id = id;
		this.userLogin = userLogin;
		this.contactLogin = contactLogin;
		this.offlineMessages = offlineMessages;
	}
	
	public Contact (Long id, String userLogin, String contactLogin){
		this.id = id;
		this.userLogin = userLogin;
		this.contactLogin = contactLogin;
	}
	
	private Long id;
	private String userLogin;
	private String contactLogin;
	private String offlineMessages;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOfflineMessages() {
		return offlineMessages;
	}

	public void setOfflineMessages(String offlineMessages) {
		this.offlineMessages = offlineMessages;
	}
	
	

}
