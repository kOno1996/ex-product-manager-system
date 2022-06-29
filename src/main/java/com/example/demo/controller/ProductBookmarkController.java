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
	public String Register(int id, Model model) {
		boolean isEmptyBookmark = productBookmarkService.isBookmarkList(id);
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + isEmptyBookmark);
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + id + "\n\n\n\n\n\n\n\n\n\n\n");
		if(isEmptyBookmark == true) {
			Product product = productBookmarkService.getProduct(id);
			productBookmarkService.productRegisterService(product);
		}else {
			model.addAttribute("bookmarkText", "既にお気に入り登録されています。");
		}
		
		return "forward:/bookmark/list";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Product> bookmarkList = productBookmarkService.getBookmarkList();
		model.addAttribute("bookmarkList", bookmarkList);
		return "bookmark";
	}
	
	@RequestMapping("/delete")
	public String delete(int id) {
		productBookmarkService.delete(id);
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + id);
		return "forward:/bookmark/list";
	}
}
