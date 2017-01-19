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
	}
	
	public void creatUSer(){
		User user = new User();
		user.setLogin("cris");
		try {
			user.setPassword(Criptografia.criptografarSenha("12345"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		User user2 = new User();
		user2.setLogin("fred");
		try {
			user2.setPassword(Criptografia.criptografarSenha("123456"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		userService.save(user);
		userService.save(user2);
	}
	
	
}
