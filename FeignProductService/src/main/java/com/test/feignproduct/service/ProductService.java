package com.test.feignproduct.service;

import java.util.List;

import com.test.feignproduct.model.Product;

public interface ProductService {
	
	List<Product> findAll();
	List<Product> findByCategory(String category);
	Product findById(int id);

}
