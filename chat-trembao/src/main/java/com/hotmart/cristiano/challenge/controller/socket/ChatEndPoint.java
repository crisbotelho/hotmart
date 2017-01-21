package com.hotmart.cristiano.challenge.controller.socket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint(value = "/chat")
public class ChatEndPoint {
	
	private Logger log = Logger.getLogger(ChatEndPoint.class.getSimpleName());
	private Chat chat = Chat.getChat();
	
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
        }
        else {
        	Date dateNow = new Date();
        	SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        	String date = formatDate.format(dateNow);
            chat.sendMessage(chatMessage.getUserContactId(), date + "  " + chatMessage.getSender() + ": " + chatMessage.getMessage());
        }
    }
	
	@OnClose
    public void onClose(Session session, CloseReason reason) {
    }

    @OnError
    public void onError(Session session, Throwable ex) { log.info("Error: " + ex.getMessage()); }

}
