package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductListRepository;

@Service
public class ProductListService {
	@Autowired
	private ProductListRepository productListRepository;
	
	public Page<Product> showAll(Pageable pageable){
		return productListRepository.showAll(pageable);
	}
	
	public Page<Product> showPage(Pageable pageable, Integer number){
		return productListRepository.showPage(pageable, number);
	}
	
	public Page<Product> daiCategoryPage(Pageable pageable, int daiId){
		return productListRepository.daiCategoryPage(pageable, daiId);
	}
}
