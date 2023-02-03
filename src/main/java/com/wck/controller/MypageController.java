package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/wck/mypage/order")
public class MypageController {
	
	@GetMapping("/myorders")
	public String orderListForm() {
		return "wck/order/my_order";
	}
	
	@GetMapping("/myorderdetail")
	public String orderDetailForm(@RequestParam String code, Model model) {
		//?code=230202P16631539
		log.info("주문 번호 : "+code);
		return "wck/order/my_order_detail";
	}

}
