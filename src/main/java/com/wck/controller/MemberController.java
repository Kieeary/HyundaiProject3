package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	// TODO : 3개 컨트롤러 구현
	
	@GetMapping("/findIdPw")
	public String findIdPw() {
		return "member/find_id_pw";
	}
	
	@GetMapping("/findIdComplete")
	public String findIdComplete() {
		return "member/find_id_comp";
	}
	
	
	// TODO : 메일링 할 껀지 />>
	@GetMapping("/findPwComplete")
	public String findPwComplete() {
		return "member/find_pw_comp";
	}
}
