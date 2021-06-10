package com.raon.androidworldcup.Communication;

import java.io.Serializable;

public class userDTO implements Serializable  {
	private static final long serialVersionUID = 1L;
	String user_id;
	String user_pwd;

	public userDTO(String id, String pw){
		user_id = id;
		user_pwd = pw;
		System.out.println("=======userDTO has CREATED=======");
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	
}
