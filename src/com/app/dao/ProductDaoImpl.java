package com.app.dao;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Product;

@Repository
@Transactional
public class ProductDaoImpl extends BaseDao implements IProductDao{


	@Override
	public List<Product> getAllProducts() {
		String jpql = "select u from Product u ";
		return sf.getCurrentSession().createQuery(jpql, Product.class).getResultList();
	}
	

	@Override
	public Product save(Product product) {
		
		sf.getCurrentSession().persist(product);
		return product;
	}


	@Override
	public Product findById(Long productId) {
		
		String jpql = "select u from Product u where u.productId=:productId";
		Query<Product> q=sf.getCurrentSession().createQuery(jpql);
		q.setParameter("productId", productId);
		return q.getSingleResult();
	}

	
	
	
}
