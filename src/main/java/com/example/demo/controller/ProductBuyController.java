package com.example.demo.controller;

import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buy")
public class ProductBuyController {
	private final MailSender mailSender;
	
	public MainController(MailSender mailSender) {
		this.mailSender = mailSender;
	}
}
