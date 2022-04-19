/**
 * 
 */
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.form.UserForm;
import com.example.demo.service.UserRegisterService;

/**
 * @author rksuser
 *
 */

@Controller
@RequestMapping("/user")
public final class UserRegisterController {
	@Autowired
	private UserRegisterService userRegisterService;
	
	@ModelAttribute
	public UserForm setUpUserForm() {
		return new UserForm();
	}
	
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "register";
	}
	
	@RequestMapping("/register")
	public String register(UserForm userForm) {
		User user = new User();
		user.setName(userForm.getName());
		user.setPassword(userForm.getPassword());
		userRegisterService.userInsert(user);
		return "login";
	}
}
