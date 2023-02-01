package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	
	@GetMapping("/findIdPw")
	public String findIdPw() {
		return "member/find_id_pw";
	}
	
	@GetMapping("/findIdResult")
	public String findIdResult() {
		return "member/find_id_result";
	}
}
