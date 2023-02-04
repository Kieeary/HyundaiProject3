package com.wck.controller;

import java.lang.reflect.Member;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wck.domain.MemberVO;
import com.wck.security.domain.Account;
import com.wck.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/wck/mypage")
@RequiredArgsConstructor
public class MypageController {
	
	private final MemberService memberService;
	
	@GetMapping
	public String myPage() {
		return "wck/mypage/mypage";
	}
	
	@GetMapping("/change/checkpw")
	public String checkPw(
			@RequestParam(required = false, name = "error") String error,
			@AuthenticationPrincipal Account account, 
			Model model ) {
		
		model.addAttribute("error", error);
		model.addAttribute("account",account);
		return "wck/mypage/change_info_check_pw";
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/change/checkpw")
	public String checkPwPost(
			@AuthenticationPrincipal Account account,
			@RequestParam("password") String password,
			RedirectAttributes redirect) {
		boolean result = memberService.checkPassword(account.getEmail(), password);
		// 비밀번호가 일치하지 않으면
		log.info("result : " + result);
		if(!result) {
			return "redirect:/wck/mypage/change/checkpw?error="+URLEncoder.encode("비밀번호를 확인해주세요");
		}
		
		return "redirect:/wck/mypage/change/form";
	}
	
	@GetMapping("/change/form")
	public String changeForm(
			@AuthenticationPrincipal Account account,
			Model model) {
		MemberVO member = memberService.findMemberByEmail(account.getEmail());
		model.addAttribute("member", member);
		
		return "wck/mypage/change_info";
	}
	
	@GetMapping("/order/myorders")
	public String orderListForm() {
		return "wck/order/my_order";
	}
	
	@GetMapping("/order/myorderdetail")
	public String orderDetailForm(@RequestParam String code, Model model) {
		log.info("주문 번호 : "+code);
		return "wck/order/my_order_detail";
	}

}
