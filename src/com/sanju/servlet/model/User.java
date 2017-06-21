package com.sanju.servlet.model;

import java.util.HashMap;

public class User {

	HashMap<String, String> userList = new HashMap<>();
	
	public User() {
		userList.put("sanju", "1234");
		userList.put("vivek", "12345678");
	}

	public HashMap<String, String> getUserList() {
		return userList;
	}

	public void setUserList(HashMap<String, String> userList) {
		this.userList = userList;
	}
	
	
	
}
