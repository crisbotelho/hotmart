package com.hotmart.cristiano.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotmart.cristiano.challenge.dao.UserDao;
import com.hotmart.cristiano.challenge.model.User;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly = false)
	public void save(User user) {
		userDao.save(user);
	}

	public List<User> getAll() {
		return userDao.getAll();
	}

	public User getById(Long id) {
		return userDao.getById(id);
	}

	public User getByLogin(String login) {
		return userDao.getByLogin(login);
	}

}
