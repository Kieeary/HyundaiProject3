package com.wck.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wck.security.domain.Account;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/wck")
public class WckController {

	@GetMapping("/login")
	public String loginForm() {
		return "wck/login";
	}
	
	@GetMapping("/join")
	public String joinForm() {
		return "wck/join";
	}
	
	@GetMapping("/sampleProductDetail")
	public String samplePD() {
		return "wck/sample/prod_detail";
	}
	
	@GetMapping("/shoppingbag")
	public String cartForm(@AuthenticationPrincipal Account user) {
		log.info(user);
		return "wck/shoppingbag/cart";
	}
	
}
