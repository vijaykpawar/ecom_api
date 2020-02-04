package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.ILoginDao;
import com.app.pojos.User;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	ILoginDao loginDao;


	@PostMapping("/valid")
	public User isvaliduser(@Valid @RequestBody User user) {
		System.out.println(user);
		User u = loginDao.findbyUsernameAndPassword(user.getUsername(), user.getPassword());

		System.out.println("returning user ::" + u);
		return u;
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return loginDao.listUsers();
	}

	@PostMapping("/register")
	public User createUser(@Valid @RequestBody User user) {
		return loginDao.save(user);
	}

}
