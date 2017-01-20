package com.hotmart.cristiano.challenge.test;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.Assert;
//
//import com.hotmart.cristiano.challenge.model.User;
//import com.hotmart.cristiano.challenge.model.UserContact;
//import com.hotmart.cristiano.challenge.service.UserContactService;
//import com.hotmart.cristiano.challenge.service.UserService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class UserContactServiceTest {
//
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private UserContactService userContactService;
//
//
//	@Test
//	public void executeTest() {
//		creatUSerContact();
//		List<UserContact> contacts = userContactService.getAll();
//		Assert.notEmpty(contacts);
//	}
//
//	private void creatUSerContact() {
//		User userCris = userService.getByLogin("cris");
//		if(userCris == null) {
//			userCris = new User();
//			userCris.setLogin("cris");
//			userCris.setPassword("12345");
//			userService.save(userCris);
//		}
//
//		User userFred = userService.getByLogin("fred");
//		if(userFred == null) {
//			userFred = new User();
//			userFred.setLogin("fred");
//			userFred.setPassword("123456");
//			userService.save(userFred);
//		}
//
//		User user3 = new User();
//		user3.setLogin("nic");
//		user3.setPassword("1234567");
//
//		userService.save(user3);
//
//		UserContact userContact = new UserContact();
//		userContact.setUser(userCris);
//		userContact.setContact(userFred);
//
//		UserContact userContact2 = new UserContact();
//		userContact2.setUser(userCris);
//		userContact2.setContact(user3);
//
//		userContactService.save(userContact);
//		userContactService.save(userContact2);
//	}
}
