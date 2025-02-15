package com.test.feign.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.feign.ifaces.FeignInterfaces;
import com.test.feign.model.Product;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/client")
public class FeignClient {

	@Autowired
	private FeignInterfaces feignclient;
	


	@GetMapping("/allProducts")
	@CircuitBreaker(name = "client-cb", fallbackMethod = "generateResponse")
	public List<Product> getAll() {
		return feignclient.getProducts();

	}
	// http://localhost:7082/client/products-by-id/2

	@GetMapping("/products-by-id/{id}")
	public Product getProductById(@PathVariable("id") int id) {
		System.out.println(id);
		return feignclient.getProductsById(id);
	}

	// http://localhost:7082/client/products-by-category/Mobile

	@GetMapping("/products-by-category/{category}")
	public List<Product> getProductsByCategory(@PathVariable("category") String category) {
		return feignclient.getProductsByCategory(category);

	}
	
	public String generateResponse(Throwable throwable) {
		
		return "The Server is Unavailable temporarily, Please try after some time";
	}

}
