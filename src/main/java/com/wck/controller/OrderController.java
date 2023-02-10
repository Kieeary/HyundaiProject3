package com.wck.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wck.domain.OrderProductVO;
import com.wck.security.domain.Account;
import com.wck.service.CartService;
import com.wck.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/wck/checkout")
@RequiredArgsConstructor
public class OrderController {
	
	@Autowired
	private final CartService cartService;
	
	@Autowired
	private final ProductService productService;
	
	@PostMapping(value = "/ordersheet")
	public String orderForm(@AuthenticationPrincipal Account user, HttpServletRequest request, Model model) {
		model.addAttribute("mId", user.getId());
		List<String> psids = new LinkedList<String>();
		String[] prodIdx = request.getParameterValues("cartlist");
		
		log.info("MID > " + user.getId());
		for (String id : prodIdx) {
			psids.add(request.getParameter("ps_"+id));
		}
		
		log.info("PSID # > "+psids.size());
		List<OrderProductVO> prods = productService.getPsIdInfo(psids);
		
		for(int i=0; i<psids.size(); i++) {
			int qty = Integer.parseInt((String) request.getParameter("quantity_"+i));
			prods.get(i).setQuantity(qty);
		}
		
		model.addAttribute("prods", prods);
		log.info("{}",prods);
		
		return "wck/order/order_sheet";
	}
	
	@GetMapping("/orderConfirmation")
	public String orderConfirmForm() {
		return "wck/order/order_comp";
	}

}
