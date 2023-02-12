package com.wck.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wck.domain.EventCouponVO;
import com.wck.domain.InsertOrderDTO;
import com.wck.domain.MemberGrade;
import com.wck.domain.MemberVO;
import com.wck.domain.OrderProductVO;
import com.wck.domain.OrderVO;
import com.wck.security.domain.Account;
import com.wck.service.MemberService;
import com.wck.service.OrderService;
import com.wck.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/wck/checkout")
@RequiredArgsConstructor
public class OrderController {
	
	@Autowired
	private final MemberService memberService;
	
	@Autowired
	private final ProductService productService;
	
	@Autowired
	private final OrderService orderService;
	
	@PostMapping(value = "/ordersheet")
	public String orderForm(@AuthenticationPrincipal Account user, HttpServletRequest request, Model model) {
		List<String> psids = new LinkedList<String>();
		List<Integer> qtys = new LinkedList<Integer>();
		
		String[] prodIdx = request.getParameterValues("cartlist");
		
		for (String id : prodIdx) {
			psids.add(request.getParameter("ps_"+id));
			int qty = Integer.parseInt((String) request.getParameter("quantity_"+id));
			qtys.add(qty);
		}
				
		List<OrderProductVO> prods = new LinkedList<OrderProductVO>();
		for(int i=0; i<prodIdx.length; i++) {
			String psid = psids.get(i);
			int qty = qtys.get(i);
			OrderProductVO prod = productService.getPsIdInfo(psid);
			prod.setQuantity(qty);
			prods.add(prod);
		}
		
		model.addAttribute("prods", prods);
		log.info("prods > {}", prods);
		
		MemberVO member = memberService.findMemberByEmail(user.getEmail());
		model.addAttribute("mem", member);
		log.info("member > {}", member);
		
		double accrualRate = memberService.getMileageAddRate(user.getId());
		model.addAttribute("rate", accrualRate);
		
		
		List<EventCouponVO> coupon = memberService.getCoupon(user.getEmail());
		model.addAttribute("coupons", coupon);	
		log.info("coupons > {}", coupon);
		
		model.addAttribute("insertOrder", new InsertOrderDTO());
		
		return "/wck/order/order_sheet";
	}
	
	@PostMapping("/orderConfirmation")
	@ResponseBody
	public ResponseEntity<String> orderConfirmForm(@AuthenticationPrincipal Account user,
									@Valid @ModelAttribute("insertOrder") InsertOrderDTO insertOrder,
									BindingResult bindingResult,
									Model model) {
		if(bindingResult.hasErrors()) {
			log.info(bindingResult.getAllErrors());
			return new ResponseEntity<> ("fail", HttpStatus.BAD_REQUEST);
		}
		
		insertOrder.setMId(user.getId());
		log.info("{}", insertOrder);
		orderService.insertOrder(insertOrder);
		model.addAttribute("oId", insertOrder.getOId());		
		return new ResponseEntity<> (insertOrder.getOId(), HttpStatus.OK);
	}
	
	@GetMapping("/orderConfirmation")
	public String orderConfirmForm(@AuthenticationPrincipal Account user,
									@RequestParam("oId") String oId,
									Model model) {
		OrderVO order = orderService.getOrderInfo(user.getId(), oId);
		log.info("{}",order);
		model.addAttribute("order", order);
		
		// 적립 예정 마일리지 계산
		long totalOrderPrice = memberService.getTotalUsePrice(user.getId());
		int grade = MemberGrade.of(totalOrderPrice - order.getObeforePrice());
		int expectAddMileage = (int) Math.floor(order.getObeforePrice() * MemberGrade.of(grade).getAccruRate());
		model.addAttribute("addM", expectAddMileage);
		
		return "/wck/order/order_comp";
	}
}
