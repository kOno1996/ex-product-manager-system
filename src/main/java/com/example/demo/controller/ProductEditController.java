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
	public String edit(ProductEditForm productEditForm, Model model, int id) {
		
		//System.out.println(id);
		
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n" + session.getAttribute("productList"));
		List<Product> daiCategoryList = productEditService.editDaiCategory(); 
		List<Product> chuCategoryList = productEditService.editChuCategory();
		List<Product> shoCategoryList = productEditService.editShoCategory();
		Product editProduct = productEditService.editProduct(id);
		//System.out.println(editProduct.getDaiName());
		productEditForm.setName(editProduct.getName());
		productEditForm.setPrice(editProduct.getPrice());
		productEditForm.setBrand(editProduct.getBrand());
		productEditForm.setDescription(editProduct.getDescription());
		
		model.addAttribute("daiCategoryList", daiCategoryList);
		model.addAttribute("chuCategoryList", chuCategoryList);
		model.addAttribute("shoCategoryList", shoCategoryList);
		model.addAttribute("editProduct", editProduct);
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n" + session.getAttribute("productList"));
		return "edit";
	}
	
	@RequestMapping("/update")
	public String update(ProductEditForm productEditForm, String daiName, String chuName, String shoName, int id) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n" + id +"\n\n\n\n\n\n\n\n\n\n\n\n\n");
		Product product = new Product();
		product.setName(productEditForm.getName());
		product.setPrice(productEditForm.getPrice());
		product.setCategory(Integer.parseInt(shoName));
		product.setCondition(productEditForm.getCondition());
		product.setBrand(productEditForm.getBrand());
		product.setShipping(0);
		product.setDescription(productEditForm.getDescription());
		
		productEditService.editUpdate(id, product);
		return "forward:/product-list";
	}
	
}
