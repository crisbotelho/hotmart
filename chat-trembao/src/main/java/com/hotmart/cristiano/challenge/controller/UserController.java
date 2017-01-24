package com.hotmart.cristiano.challenge.controller;

import java.util.Date;
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

import com.hotmart.cristiano.challenge.enumtype.StatusType;
import com.hotmart.cristiano.challenge.model.Contact;
import com.hotmart.cristiano.challenge.model.User;
import com.hotmart.cristiano.challenge.service.UserContactService;
import com.hotmart.cristiano.challenge.service.UserService;
import com.sun.research.ws.wadl.Response;
import com.wordnik.swagger.annotations.*;

//@Controller
@Path("/user")
@Api(value = "/chat-trembao", description = "Rest api for do operations on admin", produces = MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
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
	@ApiOperation(value = "Verifica se o usu�rio e senha existem", httpMethod = "POST", notes = "Verifica se o usu�rio e senha existem", response = Response.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "login encontrado"),
	    @ApiResponse(code = 404, message = "login n�o encontrado"),
	    @ApiResponse(code = 500, message = "Erro interno em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 400, message = "Erro no request em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 412, message = "Dados obrigat�rios n�o encontrados") })
	public void addUser(UserDto userDto) {
		User user = new User();
		user.setLogin(userDto.getLogin());
		user.setPassword(userDto.getPassword());
		user.setStatus(StatusType.OFFLINE.getCodigo());
		user.setLastLogout(new Date());
		getBeanUserService();
		userService.save(user);
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Verifica se o usu�rio e senha existem", httpMethod = "POST", notes = "Verifica se o usu�rio e senha existem", response = Response.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "login encontrado"),
	    @ApiResponse(code = 404, message = "login n�o encontrado"),
	    @ApiResponse(code = 500, message = "Erro interno em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 400, message = "Erro no request em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 412, message = "Dados obrigat�rios n�o encontrados") })
	public String login(UserDto userDto){
		String success = null;
		User user = new User();
		user.setLogin(userDto.getLogin());
		user.setPassword(userDto.getPassword());
		getBeanUserService();
		User userSuccess  = userService.getByLoginAndPassword(user.getLogin(), user.getPassword());
		if(userSuccess != null){
			userSuccess.setStatus(StatusType.ONLINE.getCodigo());
			userService.update(userSuccess);
			success = "S";
		}
		return success;		
	}
	
	@POST
	@Path("/addcontact")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Adiciona contato", httpMethod = "POST", notes = "Adiciona um contato a um usu�rio", response = Response.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "addcontact encontrado"),
	    @ApiResponse(code = 404, message = "addcontact n�o encontrado"),
	    @ApiResponse(code = 500, message = "Erro interno em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 400, message = "Erro no request em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 412, message = "Dados obrigat�rios n�o encontrados") })
	public void addContact(Contact contact) {
		getBeanUserContactService();
		userContactService.save(contact);
	}
	
	@GET
	@Path("/listonlinecontacts/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Obt�m lista de contatos onlines", httpMethod = "GET", notes = "Busca os contatos onlines de acordo com o login do usu�rio", response = Response.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "listofflinecontacts encontrado"),
	    @ApiResponse(code = 404, message = "listofflinecontacts n�o encontrado"),
	    @ApiResponse(code = 500, message = "Erro interno em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 400, message = "Erro no request em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 412, message = "Dados obrigat�rios n�o encontrados") })
	public List<Contact> listOnlineContacts(@PathParam("login") String login) {
		getBeanUserContactService();
		return userContactService.getContactByUser(login, StatusType.ONLINE.getCodigo());
	}
	
	@GET
	@Path("/listofflinecontacts/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Obt�m lista de contatos offlines", httpMethod = "GET", notes = "Busca os contatos offlines de acordo com o login do usu�rio", response = Response.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "listofflinecontacts encontrado"),
	    @ApiResponse(code = 404, message = "listofflinecontacts n�o encontrado"),
	    @ApiResponse(code = 500, message = "Erro interno em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 400, message = "Erro no request em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 412, message = "Dados obrigat�rios n�o encontrados") })
	public List<Contact> listOfflineContacts(@PathParam("login") String login) {
		getBeanUserContactService();
		return userContactService.getContactByUser(login, StatusType.OFFLINE.getCodigo());
	}
	
	@POST
	@Path("/dologout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Sai do sistema", httpMethod = "POST", notes = "Atualiza usu�rio sinalizando que o mesmo est� Offline", response = Response.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "dologout encontrado"),
	    @ApiResponse(code = 404, message = "dologout n�o encontrado"),
	    @ApiResponse(code = 500, message = "Erro interno em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 400, message = "Erro no request em fun��o da decodifica��o dos dados"),
	    @ApiResponse(code = 412, message = "Dados obrigat�rios n�o encontrados") })
	public void doLogOut(Contact contact) {
		getBeanUserService();
		userService.doLogOut(contact.getUserLogin());
	}
	
}
