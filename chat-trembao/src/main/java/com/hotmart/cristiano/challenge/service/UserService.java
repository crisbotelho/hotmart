package com.hotmart.cristiano.challenge.service;

import java.util.List;

import com.hotmart.cristiano.challenge.model.User;

public interface UserService {

	public void save(User user);
	public void update(User user);
	public List<User> getAll();
	public User getById(Long id);
	public User getByLogin(String login);
	public User getByLoginAndPassword(String login, String password);
	public void doLogOut(String userLogin);
}
