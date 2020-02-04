package com.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Category;

@Repository
@Transactional
public class CategoryDaoImpl extends BaseDao {

	
	public Category getCategoryById( long categoryId){
		String jpql = "select u from Category u where u.categoryId=:id";
		Category c=null;
		try {
			c = (Category) sf.getCurrentSession().createQuery(jpql).setParameter("id",categoryId).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public List<Category> getAllCategories() {
		String jpql = "select u from Category u ";
		return sf.getCurrentSession().createQuery(jpql, Category.class).getResultList();
	}
	
	
	
}
