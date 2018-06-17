package com.chinasofti.etc.hiq.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MessagePackge implements Serializable{
	private int messageType;
    private String message;
    
    
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessagePackge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessagePackge(int messageType, String message) {
		super();
		this.messageType = messageType;
		this.message = message;
	}
    
    
}
