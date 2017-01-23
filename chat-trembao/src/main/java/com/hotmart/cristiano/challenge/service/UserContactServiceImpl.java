package com.hotmart.cristiano.challenge.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotmart.cristiano.challenge.dao.HistoryDao;
import com.hotmart.cristiano.challenge.dao.UserContactDao;
import com.hotmart.cristiano.challenge.dao.UserDao;
import com.hotmart.cristiano.challenge.enumtype.OfflineMessage;
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
	
	@Autowired
	private HistoryDao historyDao;
	
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

	public List<Contact> getContactByUser(String userLogin, Short statusId) {
		List<UserContact> userContactList = userContactDao.getContactByUser(userLogin, statusId);
		List<UserContact> contactUserList = userContactDao.getContactByContact(userLogin, statusId);
		
		List<Contact> contacts = new ArrayList<Contact>();
		for(UserContact userContact : userContactList){
			Date lastLogout = userContact.getUser().getLastLogout();
			Contact contact = null;
			Date MaxHistory = historyDao.getMaxDate(userContact.getId());
			System.out.println("lastLogout: " + lastLogout);
			System.out.println("max: " + MaxHistory);
			if(MaxHistory != null  && lastLogout.before(MaxHistory))
				contact  = new Contact(userContact.getId(), userContact.getUser().getLogin(), 
						userContact.getContact().getLogin(), OfflineMessage.SIM.toString());
			else
				contact = new Contact(userContact.getId(), userContact.getUser().getLogin(), 
						userContact.getContact().getLogin(), OfflineMessage.NAO.toString());
				contacts.add(contact);
		}
		
		for(UserContact userContact : contactUserList){
			Date lastLogout = userContact.getContact().getLastLogout();
			Contact contact = null;
			Date MaxHistory = historyDao.getMaxDate(userContact.getId());
			if(MaxHistory != null && lastLogout.before(MaxHistory))
				contact = new Contact(userContact.getId(), userContact.getContact().getLogin(), 
						userContact.getUser().getLogin(),  OfflineMessage.SIM.toString());
			else
				contact = new Contact(userContact.getId(), userContact.getContact().getLogin(), 
						userContact.getUser().getLogin(),  OfflineMessage.NAO.toString());
			contacts.add(contact);
		}
		
		return contacts;
	}

	public UserContact getByUserAndContact(String userLogin, String contactLogin) {
		return userContactDao.getByUserAndContact(userLogin, contactLogin);
	}
	
}
