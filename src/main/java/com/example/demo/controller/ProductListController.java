package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductListService;

@Controller
@RequestMapping("/product-list")
public class ProductListController {
	
	@Autowired
	private ProductListService productListService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<Product> productList = productListService.showAll();
		model.addAttribute("productList", productList);
		return "list";
	}
}
