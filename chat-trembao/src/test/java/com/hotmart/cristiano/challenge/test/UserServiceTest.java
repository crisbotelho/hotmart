package com.hotmart.cristiano.challenge.test;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.hotmart.cristiano.challenge.model.User;
import com.hotmart.cristiano.challenge.service.UserService;
import com.hotmart.cristiano.challenge.util.Criptografia;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	
	@Test
	public void executeTest() {
		creatUSer();
		List<User> users = userService.getAll();
		Assert.notEmpty(users);
		User user = userService.getByLogin("cris");
		Assert.notNull(user);
		Assert.isTrue(user.getLogin().equals("cris"));
	}
	
	public void creatUSer(){
		User userCris = userService.getByLogin("cris");
		if(userCris == null) {
			userCris = new User();
			userCris.setLogin("cris");
			try {
				userCris.setPassword(Criptografia.criptografarSenha("12345"));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			userService.save(userCris);
		}
		
		User userFred = userService.getByLogin("fred");
		if(userFred == null) {
			userFred = new User();
			userFred.setLogin("fred");
			try {
				userFred.setPassword(Criptografia.criptografarSenha("123456"));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			userService.save(userFred);
		}
		
	}
	
	
}
