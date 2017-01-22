package com.hotmart.cristiano.challenge.dao;

import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hotmart.cristiano.challenge.model.User;

@Repository
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User ";
		Query query = session.createQuery(hql);
		return query.list();
	}

	public User getById(Long id) {		
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		User user = null;
		try {
			user = (User) query.uniqueResult();
		} catch (NonUniqueResultException exc) {
			user = null;
		}
		return user;
	}

	public User getByLogin(String login) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE login = :login ";
		Query query = session.createQuery(hql);
		query.setParameter("login", login);
		User user = null;
		try {
			user = (User) query.uniqueResult();
		} catch (NonUniqueResultException exc) {
			user = null;
		}
		return user;
	}

	public User getByLoginAndPassword(String login, String password) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE login = :login AND password like :password ";
		Query query = session.createQuery(hql);
		query.setParameter("login", login);
		query.setParameter("password", password);
		User user = null;
		try {
			user = (User) query.uniqueResult();
		} catch (NonUniqueResultException exc) {
			user = null;
		}
		return user;
	}

	public void update(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

}
