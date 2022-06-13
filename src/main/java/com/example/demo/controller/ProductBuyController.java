package com.example.demo.controller;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buy")
public class ProductBuyController {
	private final MailSender mailSender;

	public ProductBuyController(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@RequestMapping("/mail")
	public String mail() {
		SimpleMailMessage msg = new SimpleMailMessage();
		
		return "forward:/product-list";
	}
}
