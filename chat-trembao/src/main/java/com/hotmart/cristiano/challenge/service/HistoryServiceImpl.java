package com.hotmart.cristiano.challenge.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotmart.cristiano.challenge.dao.HistoryDao;
import com.hotmart.cristiano.challenge.model.History;

@Service
@Transactional(readOnly = true)
public class HistoryServiceImpl implements HistoryService{

	@Autowired
	private HistoryDao historyDao;
	
	@Transactional(readOnly = false)
	public void save(History history) {
		historyDao.save(history);
	}

	public List<String> getMessagesByUserContact(Long id) {
		return historyDao.getMessagesByUserContact(id);
	}
	
	public Date getMaxDate(Long id){
		return historyDao.getMaxDate(id);
	}

}
