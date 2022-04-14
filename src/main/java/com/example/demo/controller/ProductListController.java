package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product-list")
public class ProductListController {
	
	@RequestMapping("")
	public String index() {
		return "list";
	}
}
