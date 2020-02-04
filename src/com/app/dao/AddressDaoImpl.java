package com.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Address;

@Repository
@Transactional
public class AddressDaoImpl extends BaseDao{

	
	public List<Address> getAddressByUserId( long usereId){
		String jpql = "select u from Address u where u.user.userId=:id";
		List<Address> c=null;
		try {
			c =  sf.getCurrentSession().createQuery(jpql).setParameter("id",usereId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public Address persistAddress(Address address) {
		sf.getCurrentSession().persist(address);
		return address;
	}
	
	
	
	
	
}
