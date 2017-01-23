package com.hotmart.cristiano.challenge.controller.socket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotmart.cristiano.challenge.enumtype.MessageType;
import com.hotmart.cristiano.challenge.model.History;
import com.hotmart.cristiano.challenge.model.UserContact;
import com.hotmart.cristiano.challenge.service.HistoryService;
import com.hotmart.cristiano.challenge.service.UserContactService;

@ServerEndpoint(value = "/chat")
public class ChatEndPoint {
	
	UserContactService userContactService = null;
	HistoryService historyService = null;
	
	private Logger log = Logger.getLogger(ChatEndPoint.class.getSimpleName());
	private Chat chat = Chat.getChat();
	
	private void getBeanUserContactService(){
		if(userContactService == null){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(
						"/META-INF/spring/app-context.xml");
		userContactService = context.getBean("userContactServiceImpl", UserContactService.class);
		}
	}
	
	private void getBeanHistoryService(){
		if(historyService == null){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(
						"/META-INF/spring/app-context.xml");
		historyService = context.getBean("historyServiceImpl", HistoryService.class);
		}
	}
	
	@OnOpen
    public void open(final Session session, EndpointConfig config) {
	}
	
	@OnMessage
    public void onMessage(final Session session, final String messageJson) {
        ObjectMapper mapper = new ObjectMapper();
        ChatMessage chatMessage = null;
        try {
            chatMessage = mapper.readValue(messageJson, ChatMessage.class);
        } catch (IOException e) {
            String message = "Badly formatted message";
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, message));
            } catch (IOException ex) { log.severe(ex.getMessage()); }
        } ;

        if (chatMessage.getMessageType().equals(MessageType.OPEN)) {
            chat.addChat(chatMessage.getUserContactId(), session);
            addMessagesFromHistory(chatMessage.getUserContactId(), session);
        }
        else {
        	Date dateNow = new Date();
        	SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        	String date = formatDate.format(dateNow);
        	String message = date + "  " + chatMessage.getSender() + ": " + chatMessage.getMessage();
            chat.sendMessage(chatMessage.getUserContactId(), message);
            saveHistory(chatMessage.getUserContactId(), message, 
            		chatMessage.getSender(), chatMessage.getReceiver(), dateNow);
        }
    }
	
	@OnClose
    public void onClose(Session session, CloseReason reason) {
    }

    @OnError
    public void onError(Session session, Throwable ex) { log.info("Error: " + ex.getMessage()); }

    private void saveHistory(Long userContactId, String message, String sender, String receiver, Date dateNow){
    	getBeanUserContactService();
    	getBeanHistoryService();
    	UserContact userContact = userContactService.getById(userContactId);
    	History history = new History();
    	history.setSender(sender);
    	history.setReceiver(receiver);
    	history.setUserContact(userContact);
    	history.setMessage(message);
    	history.setDateHour(dateNow);
    	historyService.save(history);
    }
    
    private void addMessagesFromHistory(Long userContactId, Session session){
    	getBeanHistoryService();
    	List<String> messages = historyService.getMessagesByUserContact(userContactId);
    	for(String message : messages){
    		chat.sendMessageFromHystory(message, session);
    	}
    }
}
