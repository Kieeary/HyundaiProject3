package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/wck/shoppingbag")
public class CartController {
	
	@GetMapping("/")
	public String cartForm() {
		return "wck/shoppingbag/cart";
	}

}
