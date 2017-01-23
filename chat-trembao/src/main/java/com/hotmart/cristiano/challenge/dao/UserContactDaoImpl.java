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

	public UserContact getByUserAndContact(String userLogin, String contactLogin) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserContact WHERE user.login = :userLogin and contact.login = :contactLogin ";
		Query query = session.createQuery(hql);
		query.setParameter("userLogin", userLogin);
		query.setParameter("contactLogin", contactLogin);
		UserContact userContact = null;
		try {
			userContact = (UserContact) query.uniqueResult();
		} catch (NonUniqueResultException exc) {
			userContact = null;
		}
		return userContact;
	}
	
	public List<UserContact> getContactByUser(String userLogin, Short statusId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserContact WHERE user.login = :userLogin AND contact.status = :statusId ";
		Query query = session.createQuery(hql);
		query.setParameter("userLogin", userLogin);
		query.setParameter("statusId", statusId);
		return query.list();
	}
	
	public List<UserContact> getContactByContact(String contactLogin, Short statusId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserContact WHERE contact.login = :contactLogin AND user.status = :statusId";
		Query query = session.createQuery(hql);
		query.setParameter("contactLogin", contactLogin);
		query.setParameter("statusId", statusId);
		return query.list();
	}
}
