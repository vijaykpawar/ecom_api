package com.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Address;
import com.app.pojos.Category;
import com.app.pojos.Order;
import com.app.pojos.OrderDetails;
import com.app.pojos.Product;
import com.app.pojos.User;

@Repository
@Transactional
public class OrderDaoImpl extends BaseDao {

	@Autowired
	IProductDao pDao;

	
	public Order getOrderById ( long categoryId){
		String jpql = "select u from Category u where u.categoryId=:id";
		Order c=null;
		try {
			c = (Order) sf.getCurrentSession().createQuery(jpql).setParameter("id",categoryId).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public Order order(User u, Address address, List<Product> productList) {
		
		Order order =new Order();
		order.setAddress(address);
		order.setOrderTime(new Date());
		sf.getCurrentSession().persist(order);
		
		for (Product p : productList) {
			p=pDao.findById(p.getProductId());
			OrderDetails od=new OrderDetails();
			od.setProduct(p);
			od.setPrice(p.getPrice());
			od.setOrder(order);
			sf.getCurrentSession().persist(od);
		}	
		return order;
	}
	
	
	
	
	

	
}
