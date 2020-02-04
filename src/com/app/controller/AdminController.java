package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.User;
import com.app.service.ILoginService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ILoginService service;

	public AdminController() {

		System.out.println("in ctor of " + getClass().getName());

	}

	@RequestMapping(value = "/users", produces = "application/json", method = RequestMethod.GET)
	public List<User> listUsers() {
		service.listUsers();
		return service.listUsers();
	}

	@DeleteMapping("/{udi}")
	public ResponseEntity<String> deleteUser(@PathVariable int uid) {
		try {

			return new ResponseEntity<String>(service.deleteUser(uid), HttpStatus.OK);

		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
