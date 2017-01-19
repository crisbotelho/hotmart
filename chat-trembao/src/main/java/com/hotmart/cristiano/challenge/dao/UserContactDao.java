package com.hotmart.cristiano.challenge.dao;

import java.util.List;

import com.hotmart.cristiano.challenge.model.UserContact;

public interface UserContactDao {

	public void save(UserContact userContact);
	public List<UserContact> getAll();
	public UserContact getById(Long id);
}
