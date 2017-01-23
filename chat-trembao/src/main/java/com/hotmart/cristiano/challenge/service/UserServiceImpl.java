package com.hotmart.cristiano.challenge.service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotmart.cristiano.challenge.dao.UserDao;
import com.hotmart.cristiano.challenge.enumtype.StatusType;
import com.hotmart.cristiano.challenge.model.User;
import com.hotmart.cristiano.challenge.util.Criptografia;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly = false)
	public void save(User user) {
		User existUser = userDao.getByLogin(user.getLogin());
		if(existUser == null) {
			try {
				user.setPassword(Criptografia.criptografarSenha(user.getPassword()));
				System.out.println("Chamando salvar: ");
				userDao.save(user);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
		}
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

	public User getByLoginAndPassword(String login, String password) {
		User user = null;
		try {
			String passwordCriptografada = Criptografia.criptografarSenha(password);
			user = userDao.getByLoginAndPassword(login, passwordCriptografada);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Transactional(readOnly = false)
	public void update(User user) {
		userDao.update(user);
	}

	@Transactional(readOnly = false)
	public void doLogOut(String userLogin) {
		User user = userDao.getByLogin(userLogin);
		user.setStatus(StatusType.OFFLINE.getCodigo());
		user.setLastLogout(new Date());
		userDao.update(user);
	}

}
