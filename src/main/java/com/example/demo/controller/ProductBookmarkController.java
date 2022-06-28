package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductBookmarkService;

@Controller
@RequestMapping("/bookmark")
public class ProductBookmarkController {
	@Autowired
	private ProductBookmarkService productBookmarkService;
	
	@RequestMapping("/register")
	public String Register(int id) {
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + id + "\n\n\n\n\n\n\n\n\n\n\n");
		Product product = productBookmarkService.getProduct(id);
		productBookmarkService.productRegisterService(product);
		return "forward:/product-list";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Product> bookmarkList = productBookmarkService.getBookmarkList();
		model.addAttribute("bookmarkList", bookmarkList);
		return "bookmark";
	}
}
