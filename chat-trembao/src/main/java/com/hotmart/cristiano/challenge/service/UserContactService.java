package com.hotmart.cristiano.challenge.service;

import java.util.List;

import com.hotmart.cristiano.challenge.model.Contact;
import com.hotmart.cristiano.challenge.model.UserContact;

public interface UserContactService {

	public void save(Contact contact);
	public List<UserContact> getAll();
	public UserContact getById(Long id);
	public List<Contact> getContactByUser(String userLogin);
}
