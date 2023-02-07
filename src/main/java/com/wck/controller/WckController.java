package com.wck.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wck.domain.CartVO;
import com.wck.domain.InsertMemberDTO;
import com.wck.service.CartService;
import com.wck.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import com.wck.security.domain.Account;

@Controller
@RequestMapping("/wck")
@Log4j2
@RequiredArgsConstructor
public class WckController {
	
	@Autowired
	private final CartService cartService;
	
	@Autowired
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
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/shoppingbag")
	public String cartForm(@AuthenticationPrincipal Account user, Model model) {
		model.addAttribute("mId", user.getId());
		List<CartVO> prods = cartService.readCartList(user.getId()); 
		model.addAttribute("prods", prods);
		double gradeRate = memberService.getMileageAddRate(user.getId());
		model.addAttribute("rate", gradeRate);
		
		log.info("User info > "+user.getId());
		log.info("Carts Prods # > " + prods.size());
		log.info("Mileage accrual rate > " + gradeRate);
		
		return "wck/shoppingbag/cart";
	}
}
