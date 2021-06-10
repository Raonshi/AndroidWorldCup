package com.raon.androidworldcup.Communication;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public Message() {}
	public Message(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
		
	}
	public void setMessage(String message) {
		this.message=message;
	}
}
