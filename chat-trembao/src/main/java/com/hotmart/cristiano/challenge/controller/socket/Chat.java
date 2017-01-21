package com.hotmart.cristiano.challenge.controller.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import javax.websocket.Session;

public class Chat {

	private Logger log = Logger.getLogger(Chat.class.getSimpleName());
	private static Chat instance = null;
//	private Map<String, Map<String, Session>> chatMap = new HashMap<String, Map<String, Session>>();
	private Map<Long, Session> chatMap = new HashMap<Long, Session>();
	
	
	public synchronized void addChat(Long key, Session session) { 
		chatMap.put(key, session); 
	}
	
	public synchronized void sendMessage(/*String user, String contact,*/Long key, String message) {
		
		Session session = (Session) chatMap.get(key);
		sendMessage(message, session);
		
//		boolean findSession = false;
//		if(chatMap.containsKey(user)){
//			Map<String, Session> chatMapUser = chatMap.get(user);
//			if(chatMapUser.containsKey(contact)){
//				Session session = (Session) chatMap.get(contact);
//				sendMessage(message, session);
//				findSession = true;
//			}
//		}
//		
//		if(!findSession && chatMap.containsKey(contact)) {
//			if(chatMap.containsKey(contact)){
//				Map<String, Session> chatMapContact = chatMap.get(contact);
//				if(chatMapContact.containsKey(user)){
//					Session session = (Session) chatMap.get(user);
//					sendMessage(message, session);
//					findSession = true;
//				}
//			}
//		}
//		
//		if(!findSession){
//			Set<Entry<String, Map<String, Session> >> setChat = chatMap.entrySet();
//			Iterator it = setChat.iterator();
//			while(it.hasNext()){
//				Entry<String, Map<String, Session> > entry = (Entry<String, Map<String, Session> >) it.next();
//				if(entry.getValue().containsKey(user) && entry.getKey().equals(contact)){
//					Session session = (Session) entry.getValue().get(user);
//					sendMessage(message, session);
//					findSession = true;
//				}
//			}
//		}
		
	}

	private void sendMessage(String message, Session session) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			log.info("Error: " + e.getMessage());
		}
	}
	
	public synchronized static Chat getChat() {
		if (instance == null) { instance = new Chat(); }
		return instance;
	}

}
