package com.wck.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wck.domain.InsertMemberDTO;
import com.wck.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import com.wck.security.domain.Account;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/wck")
@Log4j2
@RequiredArgsConstructor
public class WckController {
	
	
	private final MemberService memberService;
	
	@GetMapping("/login")
	public String loginForm(@RequestParam(required = false, name = "error") String error,
			Model model) {
		model.addAttribute("error",error);
		return "wck/login";
	}
	
	@GetMapping("/join")
	public String joinForm(Model model) {
		model.addAttribute("insertMember", new InsertMemberDTO());
		return "wck/join";
	}
	
	@PostMapping("/join")
	public String doJoin(
			@Valid @ModelAttribute("insertMember") InsertMemberDTO insertMember,
			BindingResult bindingResult
			) {
		if(bindingResult.hasErrors()) {
			log.info(bindingResult.getAllErrors());
			return "wck/join";
		}
		log.info(insertMember);
		
		memberService.insertEmailMember(insertMember);
		
		return "redirect:/wck/joincomplete";
	}
	
	@GetMapping("/joincomplete")
	public String joinComp() {
		return "wck/join_complete";
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
