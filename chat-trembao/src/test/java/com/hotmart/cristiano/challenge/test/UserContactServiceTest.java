package com.hotmart.cristiano.challenge.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.hotmart.cristiano.challenge.enumtype.StatusType;
import com.hotmart.cristiano.challenge.model.Contact;
import com.hotmart.cristiano.challenge.model.User;
import com.hotmart.cristiano.challenge.model.UserContact;
import com.hotmart.cristiano.challenge.service.UserContactService;
import com.hotmart.cristiano.challenge.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class UserContactServiceTest {

	@Autowired
	private UserService userService;
	@Autowired
	private UserContactService userContactService;


	@Test
	public void executeTest() {
		creatUSerContact();
		List<UserContact> contacts = userContactService.getAll();
		Assert.notEmpty(contacts);
		UserContact userContact = userContactService.getById(1l);
		Assert.notNull(userContact);
		UserContact userContact2 = userContactService.getByUserAndContact("ana", "fred");
		Assert.notNull(userContact2);
		Assert.isTrue(userContact2.getUser().getLogin().equals("ana"));
		Assert.isTrue(userContact2.getContact().getLogin().equals("fred"));
		List<Contact> listUserContact = userContactService.getContactByUser("ana", 
				StatusType.OFFLINE.getCodigo());
		Assert.notEmpty(listUserContact);
		
	}

	private void creatUSerContact() {
		User user = userService.getByLogin("ana");
		if(user == null) {
			user = new User();
			user.setLogin("ana");
			user.setPassword("12345");
			user.setStatus(StatusType.OFFLINE.getCodigo());
			userService.save(user);
		}

		User user2 = userService.getByLogin("fred");
		if(user2 == null) {
			user2 = new User();
			user2.setLogin("fred");
			user2.setPassword("123456");
			user2.setStatus(StatusType.OFFLINE.getCodigo());
			userService.save(user2);
		}

		userContactService.save(new Contact(0l, user.getLogin(), user2.getLogin()));
	}
}
