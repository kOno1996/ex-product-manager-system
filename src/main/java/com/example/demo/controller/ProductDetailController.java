package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductDetailRepository;

@Controller
@RequestMapping("/product")
public class ProductDetailController {
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	@Autowired
	private HttpSession session;
	@RequestMapping("/detail")
	public String detail(int id, Model model) {
		Product productList = productDetailRepository.detail(id);
		//System.out.println(productList.getCategoryName());
		model.addAttribute("productList", productList);
		return "detail";
	}
}
