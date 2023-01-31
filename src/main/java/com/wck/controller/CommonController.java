package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

	@GetMapping("/login")
	public String loginForm() {
		return "common/login";
	}
	
	@GetMapping("/join")
	public String joinForm() {
		return "common/join";
	}
}