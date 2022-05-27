package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductEditRepository;


@Service
public class ProductEditService {
	@Autowired
	private ProductEditRepository productEditRepository;
	
	public List<Product> editDaiCategory(){
		return productEditRepository.editDaiCategory();
	}
	
	public List<Product> editChuCategory(){
		return productEditRepository.editChuCategory();
	}
	
	public List<Product> editShoCategory(){
		return productEditRepository.editShoCategory();
	}
	
	public Product editProduct(int id) {
		return productEditRepository.editProduct(id);
	}
	public void editUpdate(int id, Product product) {
		productEditRepository.editUpdate(id, product);
	}
}
