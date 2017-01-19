package com.hotmart.cristiano.challenge.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotmart.cristiano.challenge.model.User;
import com.hotmart.cristiano.challenge.service.UserService;

@Path("/user")
public class UserController {

UserService userService = null;
	
	private void getBeanUserService(){
		if(userService == null){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(
						"/META-INF/spring/app-context.xml");
		userService = context.getBean("userService", UserService.class);
		}
	}
	
	@POST
	@Path("/addproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addProduct(User user) {
		getBeanUserService();
		userService.save(user);
	}
	
}
