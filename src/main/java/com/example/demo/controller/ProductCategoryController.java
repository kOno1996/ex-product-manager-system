package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductCategoryController {
	
	@RequestMapping("/category")
	public String category(String category) {
		System.out.println(category);
		return "list";
	}
}
