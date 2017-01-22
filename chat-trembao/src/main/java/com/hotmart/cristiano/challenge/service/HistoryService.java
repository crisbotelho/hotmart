package com.hotmart.cristiano.challenge.service;

import java.util.List;

import com.hotmart.cristiano.challenge.model.History;

public interface HistoryService {

	public void save(History history);
	public List<String> getMessagesByUserContact(Long id);
}
