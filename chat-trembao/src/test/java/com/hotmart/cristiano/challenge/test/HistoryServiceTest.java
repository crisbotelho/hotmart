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
import com.hotmart.cristiano.challenge.model.History;
import com.hotmart.cristiano.challenge.model.User;
import com.hotmart.cristiano.challenge.model.UserContact;
import com.hotmart.cristiano.challenge.service.HistoryService;
import com.hotmart.cristiano.challenge.service.UserContactService;
import com.hotmart.cristiano.challenge.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class HistoryServiceTest {

	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserContactService userContactService;

	@Test
	public void executeTest() {
		createHistory();
	}
	
	private void createHistory(){
		User userAndre = userService.getByLogin("andre");
		if(userAndre == null) {
			userAndre = new User();
			userAndre.setLogin("andre");
			userAndre.setPassword("12345");
			userAndre.setStatus(StatusType.OFFLINE.getCodigo());
			userService.save(userAndre);
		}

		User userJoao = userService.getByLogin("joao");
		if(userJoao == null) {
			userJoao = new User();
			userJoao.setLogin("joao");
			userJoao.setPassword("123456");
			userJoao.setStatus(StatusType.OFFLINE.getCodigo());
			userService.save(userJoao);
		}
		
		userContactService.save(new Contact(0l, userAndre.getLogin(), userJoao.getLogin()));
		UserContact userContactExisted = userContactService.getByUserAndContact(
				userAndre.getLogin(), userJoao.getLogin());
		
		History history = new History();
		history.setMessage("Teste 01");
		history.setSender("andre");
		history.setReceiver("joao");
		history.setUserContact(userContactExisted);
		historyService.save(history);
		List<String> messages = historyService.getMessagesByUserContact(userContactExisted.getId());
		Assert.notEmpty(messages);
		Assert.isTrue(messages.size() == 1);
	}
}
