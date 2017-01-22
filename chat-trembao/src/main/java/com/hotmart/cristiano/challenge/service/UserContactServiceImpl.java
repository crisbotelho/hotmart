package com.hotmart.cristiano.challenge.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotmart.cristiano.challenge.dao.UserContactDao;
import com.hotmart.cristiano.challenge.dao.UserDao;
import com.hotmart.cristiano.challenge.model.Contact;
import com.hotmart.cristiano.challenge.model.User;
import com.hotmart.cristiano.challenge.model.UserContact;

@Service
@Transactional(readOnly = true)
public class UserContactServiceImpl implements UserContactService {

	@Autowired
	private UserContactDao userContactDao;
	
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly = false)
	public void save(Contact contact) {
		UserContact userContactExisted = userContactDao.getByUserAndContact(
				contact.getUserLogin(), contact.getContactLogin());
		if(userContactExisted == null){
			User user = userDao.getByLogin(contact.getUserLogin());
			User user2 = userDao.getByLogin(contact.getContactLogin());
			UserContact userContact = new UserContact();
			userContact.setUser(user);
			userContact.setContact(user2);
			userContactDao.save(userContact);
		}
		
	}

	public List<UserContact> getAll() {
		return userContactDao.getAll();
	}

	public UserContact getById(Long id) {
		return userContactDao.getById(id);
	}

	public List<Contact> getContactByUser(String userLogin) {
		List<UserContact> userContactList = userContactDao.getContactByUser(userLogin);
		List<UserContact> contactUserList = userContactDao.getContactByContact(userLogin);
		
		List<Contact> contacts = new ArrayList<Contact>();
		for(UserContact userContact : userContactList){
			contacts.add(new Contact(userContact.getId(), userContact.getUser().getLogin(), 
					userContact.getContact().getLogin()));
		}
		
		for(UserContact userContact : contactUserList){
			contacts.add(new Contact(userContact.getId(), userContact.getContact().getLogin(), 
					userContact.getUser().getLogin()));
		}
		
		return contacts;
	}
	
}
