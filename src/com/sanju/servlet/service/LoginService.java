package com.sanju.servlet.service;

import java.util.HashMap;

import com.sanju.servlet.model.User;


public class LoginService {
	
	HashMap<String, String> userList;
	
	public LoginService() {
		User user = new User();
		userList = user.getUserList();
	}

	public boolean authenticate(String name, String pass) {
		
		if(userList.containsKey(name) && userList.get(name).equals(pass)) {
			return true;
		}
		return false;
	}
	
	public boolean validate(String name, String pass, String pass2) {
		if(isEmptyorNull(name) || isEmptyorNull(pass) || isEmptyorNull(pass2))
		  return false;
		else if (!pass.equals(pass2))
			return false;
		else
			return true;
	}
	
	private boolean isEmptyorNull(String val) {
		if(val != null && !val.isEmpty())
			return false;
		else
			return true;
	}
	
	public void addUser(String name, String pass) {
		userList.put(name, pass);
	}
	
	public String getUser(String name) {
		return userList.get(name);
	}
	
	public boolean checkUser(String name) {
		return userList.containsKey(name);
	}
}
