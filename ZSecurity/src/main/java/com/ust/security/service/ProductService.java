package com.ust.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.security.model.Product;
import com.ust.security.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Optional<Product> getProductById(Long id){
		return productRepository.findById(id);
	}
	
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product updateProduct(Long id,Product updatedProduct) {
		Optional<Product> existingProduct = productRepository.findById(id);
		
		if(existingProduct.isPresent()) {
			Product product = existingProduct.get();
			product.setName(updatedProduct.getName());
			product.setPrice(updatedProduct.getPrice());
			return productRepository.save(product);
		}else {
			return null;
		}
		
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	

}
