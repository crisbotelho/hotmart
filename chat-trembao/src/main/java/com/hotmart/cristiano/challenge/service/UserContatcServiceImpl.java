package com.hotmart.cristiano.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotmart.cristiano.challenge.dao.UserContactDao;
import com.hotmart.cristiano.challenge.model.UserContact;

@Service
@Transactional(readOnly = true)
public class UserContatcServiceImpl implements UserContactService {

	@Autowired
	private UserContactDao userContactDao;
	
	@Transactional(readOnly = false)
	public void save(UserContact userContact) {
		userContactDao.save(userContact);
	}

}
