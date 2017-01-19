package com.hotmart.cristiano.challenge.service;

import java.util.List;

import com.hotmart.cristiano.challenge.model.UserContact;

public interface UserContactService {

	public void save(UserContact userContact);
	public List<UserContact> getAll();
	public UserContact getById(Long id);
}
