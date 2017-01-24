package com.hotmart.cristiano.challenge.controller.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.websocket.Session;

public class Chat {

	private Logger log = Logger.getLogger(Chat.class.getSimpleName());
	private static Chat instance = null;
	private Map<Long, List<Session>> chatMap = new HashMap<Long, List<Session>>();


	public synchronized void addChat(Long key, Session session) { 
		List<Session> list = null;
		if(!chatMap.containsKey(key)){
			list = new ArrayList<Session>();
			list.add(session);
			chatMap.put(key, list);
		}else {
			list = chatMap.get(key);
			list.add(session);
			chatMap.put(key, list);
		}
	}

	public synchronized void sendMessage(Long key, String message) {

		List<Session> sessions = (List<Session>) chatMap.get(key);
		for(Session session : sessions) {
			if (session.isOpen()) {
				sendMessage(message, session);
			}
		}
	}

	private void sendMessage(String message, Session session) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			log.info("Error: " + e.getMessage());
		}
	}
	
	public synchronized void sendMessageFromHystory(String message, Session session) {
		if (session.isOpen()) {
			sendMessage(message, session);
		}
	}

	public synchronized static Chat getChat() {
		if (instance == null) { instance = new Chat(); }
		return instance;
	}

}
