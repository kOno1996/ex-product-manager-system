package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
		
		//System.out.println("\n\n\n\n\n\n\n" + daiList.get(0) + "\n\n\n\n\n\n\n");
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
	public String add(ProductAddForm productAddForm, String daiName, String chuName, String shoName) {
		Product product = new Product();
		product.setName(productAddForm.getName());
		product.setPrice(productAddForm.getPrice());
		product.setDaiName(daiName);
		product.setChuName(chuName);
		product.setShoName(shoName);
		product.setCategory(Integer.parseInt(shoName));
		product.setBrand(productAddForm.getBrand());
		product.setCondition(productAddForm.getCondition());
		product.setDescription(productAddForm.getDescription());
		
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n" + product.getDaiName() + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n" + product.getChuName() + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n" + product.getShoName() + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		productAddService.addProduct(product);
		
		return "forward:/product-list";
	}
}
