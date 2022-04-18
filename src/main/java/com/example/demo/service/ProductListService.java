package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductListRepository;

@Service
public class ProductListService {
	@Autowired
	private ProductListRepository productListRepository;
	
	public List<Product> showAll(){
		return productListRepository.showAll();
	}
	
}
