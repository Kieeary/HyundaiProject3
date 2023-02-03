package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/wck/checkout")
public class OrderController {
	
	@GetMapping("/ordersheet")
	public String orderForm() {
		return "wck/order/order_sheet";
	}
	
	@GetMapping("/orderConfirmation")
	public String orderConfirmForm() {
		return "wck/order/order_comp";
	}

}
