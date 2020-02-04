package com.app.service;

import java.util.List;

import com.app.pojos.User;

public interface ILoginService {
	
	
	String registerUser(User u);
	List<User> listUsers();
	String deleteUser(int userId);
	
}
