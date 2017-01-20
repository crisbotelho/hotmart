package com.hotmart.cristiano.challenge.dao;

import java.util.List;

import com.hotmart.cristiano.challenge.model.User;

public interface UserDao {

	public void save(User user);
	public List<User> getAll();
	public User getById(Long id);
	public User getByLogin(String login);
	public User getByLoginAndPassword(String login, String password);
}
