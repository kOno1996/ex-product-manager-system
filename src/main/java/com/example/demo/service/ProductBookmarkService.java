package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductBookmarkRepository;

@Service
public class ProductBookmarkService {
	@Autowired
	private ProductBookmarkRepository productBookmarkRepository;
	
	public void productRegisterService(Product product) {
		productBookmarkRepository.productRegister(product);
	}
	
	public Product getProduct(int id) {
		return productBookmarkRepository.getProduct(id);
	}
	
	public List<Product> getBookmarkList(){
		return productBookmarkRepository.getBookmarkList();
	}
}
