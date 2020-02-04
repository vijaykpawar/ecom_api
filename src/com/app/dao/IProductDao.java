package com.app.dao;

import java.util.List;

import com.app.pojos.Product;

public interface IProductDao {

	List<Product> getAllProducts();

	Product save(Product product);

	Product findById(Long productId);

}
