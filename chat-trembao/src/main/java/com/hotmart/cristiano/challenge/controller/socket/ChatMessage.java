package com.hotmart.cristiano.challenge.controller.socket;

public class ChatMessage {

	private String sender;
	private Long userContactId;
	private MessageType messageType;
    private String message;
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getUserContactId() {
		return userContactId;
	}
	public void setUserContactId(Long userContactId) {
		this.userContactId = userContactId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
}
