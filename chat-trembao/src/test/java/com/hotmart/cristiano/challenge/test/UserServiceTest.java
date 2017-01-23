package com.hotmart.cristiano.challenge.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.hotmart.cristiano.challenge.enumtype.StatusType;
import com.hotmart.cristiano.challenge.model.User;
import com.hotmart.cristiano.challenge.service.UserService;

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
		user.setStatus(StatusType.ONLINE.getCodigo());		
		userService.update(user);
		user = userService.getByLogin("cris");
		Assert.isTrue(user.getStatus().equals(StatusType.ONLINE.getCodigo()));
		User user2 = userService.getByLoginAndPassword("cris", "12345");
		Assert.notNull(user2);		
		User user3 = userService.getByLoginAndPassword("cris", "123456");
		Assert.isNull(user3);
		User user4 = userService.getById(1l);
		Assert.notNull(user4);
	}

	public void creatUSer(){
		User userCris = userService.getByLogin("cris");
		if(userCris == null) {
			userCris = new User();
			userCris.setLogin("cris");
			userCris.setPassword("12345");
			userCris.setStatus(StatusType.OFFLINE.getCodigo());
			userService.save(userCris);
		}

		User userFred = userService.getByLogin("fred");
		if(userFred == null) {
			userFred = new User();
			userFred.setLogin("fred");
			userFred.setPassword("123456");
			userFred.setStatus(StatusType.OFFLINE.getCodigo());
			userService.save(userFred);
		}

	}


}
