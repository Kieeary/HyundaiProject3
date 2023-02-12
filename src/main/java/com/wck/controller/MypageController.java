package com.wck.controller;

import java.net.URLEncoder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wck.domain.MemberGrade;
import com.wck.domain.OrderVO;
import com.wck.domain.UpdateMemberDTO;
import com.wck.security.domain.Account;
import com.wck.service.MemberService;
import com.wck.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/wck/mypage")
@RequiredArgsConstructor
public class MypageController {
	
	private final MemberService memberService;
	
	private final OrderService orderService;
	
	/*
	 * author : 왕종휘
	 * 마이 페이지
	 */
	@GetMapping
	public String myPage() {
		return "wck/mypage/mypage";
	}
	
	/*
	 * author : 왕종휘
	 * 유저 정보 변경을 위한 비밀번호 확인 페이지
	 */
	@GetMapping("/change/checkpw")
	public String checkPw(
			@RequestParam(required = false, name = "error") String error,
			@AuthenticationPrincipal Account account, 
			Model model ) {
		
		model.addAttribute("error", error);
		model.addAttribute("account",account);
		return "wck/mypage/change_info_check_pw";
	}
	
	/*
	 * author : 왕종휘
	 * 유저 정보 변경을 위한 비밀번호 확인 수행
	 */
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
	
	/*
	 * author : 왕종휘
	 * 유저 정보 변경 페이지
	 */
	@GetMapping("/change/form")
	public String changeForm(
			@AuthenticationPrincipal Account account,
			Model model) {
		UpdateMemberDTO member = new UpdateMemberDTO(
				memberService.findMemberByEmail(account.getEmail()));
		
		model.addAttribute("member", member);
		
		return "wck/mypage/change_info";
	}
	
	/*
	 * author : 왕종휘
	 * 회원 탈퇴 페이지
	 */
	@GetMapping("/change/secession")
	public String secession() {
		return "wck/mypage/secession";
	}
	
	/*
	 * author : 왕종휘
	 * 회원탈퇴 진행
	 */
	@PostMapping("/change/secession")
	public String secessionPost(@AuthenticationPrincipal Account account) {
		log.info("회원탈퇴 ...");
		memberService.disabledMember(account.getEmail());
		
		return "redirect:/wck/logout";
	}
	
	/*
	 * author : 왕종휘
	 * 찜한 상품 목록 페이지
	 */
	@GetMapping("/wish")
	public String myWishList(
			@AuthenticationPrincipal Account account) {
		return "wck/mypage/wishlist";
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
	
	@PostMapping("/cancelRequest/{oId}")
	@ResponseBody
	public ResponseEntity<String> cancelOrder(@AuthenticationPrincipal Account user,
									@PathVariable("oId") String oId) {
		OrderVO order = orderService.getOrderInfo(user.getId(), oId);
		log.info("{}",order);
		int result = orderService.cancelOrder(user.getId(), order);
		
		if(result==0) {
			return new ResponseEntity<String> ("ERROR", HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
	}
}
