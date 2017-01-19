package com.hotmart.cristiano.challenge.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hotmart.cristiano.challenge.model.UserContact;

@Repository
@Transactional(readOnly = true)
public class UserContactDaoImpl implements UserContactDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(UserContact userContact) {
		Session session = sessionFactory.getCurrentSession();
		session.save(userContact);
	}

}
