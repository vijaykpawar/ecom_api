package com.app.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Role;
import com.app.utils.EComUtils;
@Repository
@Transactional
public class RoleDaoImpl extends BaseDao {

	
	public Role getConsumerRole(){
		String jpql = "select u from Role u where u.roleKey=:roleKey";
		Role role=null;
		try {
			role = (Role) sf.getCurrentSession().createQuery(jpql).setParameter("roleKey",EComUtils.CONSUMER_ROLE).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
	
	
	
}
