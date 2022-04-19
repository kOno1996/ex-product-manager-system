package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	public String index(@PageableDefault(size = 30)Pageable pageable, Model model) {
		Page<Product> productList = productListService.showAll(pageable);
		model.addAttribute("page", productList);
		model.addAttribute("productList", productList.getContent());
		return "list";
	}
	
	@RequestMapping("/page")
	public String page(@PageableDefault(size = 30)Pageable pageable, Model model, Integer page) {
		Page<Product> productList = productListService.showPage(pageable, page);
		model.addAttribute("page", productList);
		model.addAttribute("productList", productList.getContent());
		model.addAttribute("number", page);
		if(page == null) {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n" + "nullです" + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}else {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n" + "nullじゃない" + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}
		return "list";
	}
}
