package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}