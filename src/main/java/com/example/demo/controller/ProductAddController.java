package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Product;
import com.example.demo.form.ProductAddForm;
import com.example.demo.service.ProductAddService;

@Controller
@RequestMapping("/product")
public class ProductAddController {
	@Autowired
	private ProductAddService productAddService;
	
	@ModelAttribute
	public ProductAddForm setupProductAddForm() {
		return new ProductAddForm();
	}	
	
	@RequestMapping("toAdd")
	public String toAdd(Model model) {
		List<Product> daiList = productAddService.daiName();
		List<Product> chuList = productAddService.chuName();
		List<Product> shoList = productAddService.shoName();
		model.addAttribute("daiList", daiList);
		model.addAttribute("chuList", chuList);
		model.addAttribute("shoList", shoList);
		
		Map<Integer, Integer> conditionList = new LinkedHashMap<>();
		conditionList.put(1, 1);
		conditionList.put(2, 2);
		conditionList.put(3, 3);
		model.addAttribute("conditionList", conditionList);
		return "add";
	}
	
	@RequestMapping("/add")
	public String add(ProductAddForm productAddForm) {
		Product product = new Product();
		product.setName(productAddForm.getName());
		product.setPrice(productAddForm.getPrice());
		
		return "list";
	}
}
