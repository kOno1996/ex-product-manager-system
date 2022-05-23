package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductDetailRepository;

@Service
public class ProductDetailService {
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	public Product detail(int id) {
		return productDetailRepository.detail(id);
	}
}
