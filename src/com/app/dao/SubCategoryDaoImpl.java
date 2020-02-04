package com.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.SubCategory;

@Repository
@Transactional
public class SubCategoryDaoImpl extends BaseDao {

	
	public SubCategory getSubCategoryById( long subCategoryId){
		String jpql = "select u from SubCategory u where u.subCategoryId=:id";
		SubCategory c=null;
		try {
			c = (SubCategory) sf.getCurrentSession().createQuery(jpql).setParameter("id",subCategoryId).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public List<SubCategory> getAllSubCategories() {
		String jpql = "select u from SubCategory u ";
		return sf.getCurrentSession().createQuery(jpql, SubCategory.class).getResultList();
	}
	
	
	
}
