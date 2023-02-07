package com.wck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wck.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/wck/shoppingbag")
@RequiredArgsConstructor
public class CartController {
	
	@Autowired
	private final CartService cartService;
	
//	author : 김한울
//	purpose : 장바구니 상품 삭제
	@GetMapping("/deleteProd")
	public String deleteProd(@RequestParam("mid") String mId, @RequestParam(value = "psid", required = false) String psid) {
		log.info("user id > "+mId);
		
		if (psid == null) {
//			cartService.deleteCart(mId);
		} else {
			log.info("psid > "+ psid);
			cartService.deleteCartProd(mId, psid);
		}
		
		return "redirect:/wck/shoppingbag";
	}
	
	@GetMapping("/deleteProds")
	public String deleteProds(@RequestParam("mid") String mId, @RequestParam("psids") List<String> psids) {
		log.info("user id > " + mId);
		log.info("{}",psids);
		cartService.deleteSelectedProds(mId, psids);
		return "redirect:/wck/shoppingbag";
	}
	
	
	// 장바구니 수량 변경
	// 장바구니 조회
	// 상바구니 상품 추가

}
	