package com.hotmart.cristiano.challenge.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hotmart.cristiano.challenge.model.History;

@Repository
@Transactional(readOnly = true)
public class HistoryDaoImpl  implements HistoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(History history) {
		Session session = sessionFactory.getCurrentSession();
		session.save(history);
	}

	@SuppressWarnings("unchecked")
	public List<String> getMessagesByUserContact(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT message FROM History history WHERE userContact.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return query.list();
	}
	
	public Date getMaxDate(Long id){
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT MAX(dateHour) FROM History history WHERE userContact.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Date dateHour = null;
		try {
			dateHour = (Date) query.uniqueResult();
		} catch (NonUniqueResultException exc) {
			dateHour = null;
		}
		return dateHour;
	}

}
