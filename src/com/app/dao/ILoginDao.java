package com.app.dao;

import java.util.List;

import com.app.pojos.User;

public interface ILoginDao { 
	
	String registerUser(User u);
	List<User> listUsers();
	String deleteUser(int userId);
	User save(User user);
	User findbyUsernameAndPassword(String username, String password);
	User getUserBySessionToken(String s);
}
