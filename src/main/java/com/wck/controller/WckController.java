package com.wck.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wck.domain.CartVO;
import com.wck.domain.InsertMemberDTO;
import com.wck.domain.UpdateMemberDTO;
import com.wck.security.domain.Account;
import com.wck.service.CartService;
import com.wck.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/wck")
@Log4j2
@RequiredArgsConstructor
public class WckController {
	
	@Autowired
	private final CartService cartService;
	
	@Autowired
	private final MemberService memberService;
	
	@GetMapping
	public String home() {
		return "wck/home";
	}
	
	/*
	 * 로그인 페이지
	 */
	@GetMapping("/login")
	public String loginForm(@RequestParam(required = false, name = "error") String error,
			Model model) {
		log.info("===login page====");
		model.addAttribute("error",error);
		return "wck/login";
	}
	
	/*
	 * 회원가입 페이지
	 */
	@GetMapping("/join")
	public String joinForm(Model model) {
		model.addAttribute("insertMember", new InsertMemberDTO());
		return "wck/join";
	}
	
	/*
	 * 회원가입 수행
	 */
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
	
	/*
	 * 회원완료 페이지
	 */
	@GetMapping("/joincomplete")
	public String joinComp() {
		return "wck/join_complete";
	}
	
	/*
	 * Oauth2 의 경우 필수 값을 받기 위한 페이지
	 */
	@GetMapping("/login/change")
	public String changeForm(@AuthenticationPrincipal Account account ,Model model) {
		log.info("changeForm");
		UpdateMemberDTO update = new UpdateMemberDTO();
		
		update.setEmail(account.getEmail());
		
		model.addAttribute("member", update);
		
		SecurityContextHolder.clearContext();
		return "wck/change_info_oauth2";
	}
	
	/*
	 * Oauth2 회원 정보 변경 수행
	 */
	@PostMapping("/login/change")
	public String changeFormPost(@ModelAttribute("member") UpdateMemberDTO member) {
		log.info(member);
		memberService.updateInfo(member);
		return "redirect:/wck/login";
	}
	
	/*
	 * 키워드 검색 폼
	 */
	@GetMapping("/search")
	public String searchForm() {
		return "wck/search";
	}
	/*
	 * 키워드 검색 처리 로직 : 쿠키에 검색어 저장
	 */
	@GetMapping("/search/result")
	public String searchResult(
			@RequestParam("query") String query) {
		log.info("query : " +query);
		return "wck/home";
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
