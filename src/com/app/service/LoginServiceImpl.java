package com.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ILoginDao;
import com.app.dao.RoleDaoImpl;
import com.app.pojos.Role;
import com.app.pojos.User;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginDao dao;
	@Autowired
	private RoleDaoImpl roleDao;
	
	
	
	@Override
	public String registerUser(User u) {
		Role role=roleDao.getConsumerRole();
		u.setRole(role);
		u.setDateCreated(new Date());
		u.setDateUpdated(new Date());
		return dao.registerUser(u);
	}
	
	@Override
	public List<User> listUsers() {
		return dao.listUsers();
	}
	@Override
	public String deleteUser(int userId) {
		System.out.println("in ctor of "+getClass().getName());
		return dao.deleteUser(userId);
	}

}
