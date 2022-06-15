package com.example.demo.controller;

import org.springframework.mail.MailException;
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
		msg.setFrom("keitaro.ono.1119@gmail.com");
		msg.setTo("keitaro.ono.1119@gmail.com");
		msg.setSubject("購入の完了");
		msg.setText("商品の購入が完了したのでお知らせします。");
		
		try {
			mailSender.send(msg);
		}catch(MailException e) {
			e.printStackTrace();
		}
		return "forward:/product-list";
	}
}
