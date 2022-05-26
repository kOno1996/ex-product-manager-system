package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Product;
import com.example.demo.form.ProductEditForm;
import com.example.demo.service.ProductEditService;

@Controller
@RequestMapping("/product")
public class ProductEditController {
	@Autowired
	private ProductEditService productEditService;
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public ProductEditForm setupEditForm() {
		return new ProductEditForm();
	}
	
	@RequestMapping("/edit")
	public String edit(Model model) {
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n" + session.getAttribute("productList"));
		List<Product> daiCategoryList = productEditService.editDaiCategory(); 
		List<Product> chuCategoryList = productEditService.editChuCategory();
		List<Product> shoCategoryList = productEditService.editShoCategory();
		
		model.addAttribute("daiCategoryList", daiCategoryList);
		model.addAttribute("chuCategoryList", chuCategoryList);
		model.addAttribute("shoCategoryList", shoCategoryList);
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n" + session.getAttribute("productList"));
		return "edit";
	}
	
	@RequestMapping("/update")
	public String update(ProductEditForm productEditForm) {
		
		return "forward:/product-list";
	}
	
}
