package com.app.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Role;
import com.app.pojos.User;


@Repository
@Transactional
public class LoginDaoImpl extends BaseDao implements ILoginDao {
	
	@Autowired
	private RoleDaoImpl roleDaoImpl;
	
	public LoginDaoImpl() {
		System.out.println("in ctor of"+getClass().getName());
	}
	
	public User getUserBySessionToken(String sessionToken){
		String jpql = "select u from User u where u.sessionToken=:ss ";
		try {
			return sf.getCurrentSession().createQuery(jpql,User.class).setParameter("ss", sessionToken).getSingleResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	@Override
	public String registerUser(User u) {
		sf.getCurrentSession().save(u);
		return "User registration successful with ID "+u.getUserId();
	}
	
	@Override
	public List<User> listUsers() {
		String jpql = "select u from User u ";
		return sf.getCurrentSession().createQuery(jpql, User.class).getResultList();
	}
	@Override
	public String deleteUser(int userId) {
		org.hibernate.Session hs = sf.getCurrentSession();
		User u = hs.get(User.class,userId);
		if(u != null) {
			hs.delete(u);
			return "User deleted with ID "+userId;	
		}else
		return "USer deletion failed: Invalid Id ";
	}
	@Override
	public User save(User user) {
		//enrich user 
		Role role=roleDaoImpl.getConsumerRole();
		user.setRole(role);
		user.setDateCreated(new Date());
		user.setDateUpdated(new Date());
		sf.getCurrentSession().persist(user);
		
		return user;
	}
	@Override
	public User findbyUsernameAndPassword(String username, String password) {
		String jpql = "select u from User u where u.username=:us and u.password=:pass";
		User u=null;
		try {
		u = sf.getCurrentSession().createQuery(jpql,User.class).setParameter("us", username).setParameter("pass", password).getSingleResult();
		if(u!=null){
			String sessionToken=u.getUsername()+"_"+System.currentTimeMillis();
			u.setSessionToken(sessionToken);
			sf.getCurrentSession().persist(u);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
}