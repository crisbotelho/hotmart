package com.hotmart.cristiano.challenge.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotmart.cristiano.challenge.model.Contact;
import com.hotmart.cristiano.challenge.model.User;
import com.hotmart.cristiano.challenge.service.UserContactService;
import com.hotmart.cristiano.challenge.service.UserService;

//@Controller
@Path("/user")
public class UserController {

//	@Autowired
//	private UserService userService;
UserService userService = null;
UserContactService userContactService = null;
	
	private void getBeanUserService(){
		if(userService == null){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(
						"/META-INF/spring/app-context.xml");
		userService = context.getBean("userServiceImpl", UserService.class);
		}
	}
	
	private void getBeanUserContactService(){
		if(userContactService == null){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(
						"/META-INF/spring/app-context.xml");
		userContactService = context.getBean("userContactServiceImpl", UserContactService.class);
		}
	}
	
	@POST
	@Path("/adduser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addProduct(User user) {
		getBeanUserService();
		userService.save(user);
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(User user){
		getBeanUserService();
		return userService.getByLoginAndPassword(user.getLogin(), user.getPassword());		
	}
	
	@POST
	@Path("/addcontact")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addContact(Contact contatc) {
		getBeanUserContactService();
		userContactService.save(contatc);
//		System.out.println("Teste addConatct");
	}
	
	@GET
	@Path("/listcontacts/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> listProductWithChildProducts(@PathParam("login") String login) {
		getBeanUserContactService();
//		List<Contact> list = new ArrayList<Contact>();
//		list.add(new Contact(login, "B"));
//		list.add(new Contact(login, "C"));
//		list.add(new Contact(login, "A"));
//		list.add(new Contact(login, "D"));
		return userContactService.getContactByUser(login);
	}
	
}
