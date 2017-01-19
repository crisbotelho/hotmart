package com.hotmart.cristiano.challenge.dao;

import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	public List<UserContact> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserContact ";
		Query query = session.createQuery(hql);
		return query.list();
	}

	public UserContact getById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserContact WHERE id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		UserContact userContact = null;
		try {
			userContact = (UserContact) query.uniqueResult();
		} catch (NonUniqueResultException exc) {
			userContact = null;
		}
		return userContact;
	}

}
