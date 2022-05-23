package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductAddRepository;

@Service
public class ProductAddService {
	@Autowired
	private ProductAddRepository productAddRepository;
	
	public List<Product> daiName(){
		return productAddRepository.daiName();
	}
	
	public List<Product> chuName(){
		return productAddRepository.chuName();
	}
	
	public List<Product> shoName(){
		return productAddRepository.shoName();
	}
	
	public void addProduct(Product product) {
		productAddRepository.add(product);
	}
}
