package com.hotmart.cristiano.challenge.dao;

import java.util.List;

import com.hotmart.cristiano.challenge.model.UserContact;

public interface UserContactDao {

	public void save(UserContact userContact);
	public List<UserContact> getAll();
	public UserContact getById(Long id);
	public UserContact getByUserAndContact(String userLogin, String contactLogin);
	public List<UserContact> getContactByUser(String userLogin);
	public List<UserContact> getContactByContact(String contactLogin);
}
