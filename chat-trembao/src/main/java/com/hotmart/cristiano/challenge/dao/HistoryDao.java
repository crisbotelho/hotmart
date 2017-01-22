package com.hotmart.cristiano.challenge.dao;

import java.util.List;

import com.hotmart.cristiano.challenge.model.History;

public interface HistoryDao {

	public void save(History history);
	public List<String> getMessagesByUserContact(Long id);
}
