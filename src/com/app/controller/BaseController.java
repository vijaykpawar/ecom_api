package com.app.controller;

import javax.servlet.http.HttpSession;

import com.app.pojos.User;

public class BaseController {

	
	public User getLoggedInInUser(HttpSession httpSession){
		System.out.println("getting from session");
		return (User) httpSession.getAttribute("user");
	}
}
