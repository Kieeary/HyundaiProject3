package com.wck.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
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
	
	@Value("${private.ip}") 
    private String privateIp;
	
	@PostMapping(value = "/ordersheet")
	public String orderForm(@AuthenticationPrincipal Account user, 
							HttpServletRequest request,
							RedirectAttributes redirect,
							Model model) {
		model.addAttribute("privateIp", privateIp);
		
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
			
			String msg = productService.getProductStock(psid, qty);
			if(!msg.equals("")) {
				String referer = request.getHeader("referer");
		        referer = referer.substring(16, referer.length());
				log.info("before page url > " + referer);
				redirect.addAttribute("error", msg);
				return "redirect:"+referer;
			}
			
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
	
	
	// KG 이니시스
	@GetMapping("/orderConfirmation2/{oid}")
	public String orderConfirmForm(
			@PathVariable("oid") String oid,
			Model model) {
		System.out.println(oid);
		System.out.println("Get mapping >>>>>>>>>>>>>>>>>>>>>");
		OrderVO order = orderService.getOrderInfo(oid);
		log.info("{}",order);
		model.addAttribute("order", order);

		// 적립 예정 마일리지 계산
		long totalOrderPrice = memberService.getTotalUsePrice(order.getMid());
		int grade = MemberGrade.of(totalOrderPrice - order.getObeforePrice());
		int expectAddMileage = (int) Math.floor(order.getObeforePrice() * MemberGrade.of(grade).getAccruRate());
		model.addAttribute("addM", expectAddMileage);

		return "/wck/order/order_comp";
	}
	
	
	// KakaoPay
	@PostMapping("/orderConfirmation1")
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
		model.addAttribute("oId", insertOrder.getOid());		
		return new ResponseEntity<> (insertOrder.getOid(), HttpStatus.OK);
	}
	
	@GetMapping("/orderConfirmation1")
	public String orderConfirmForm1(@AuthenticationPrincipal Account user,
									@RequestParam("oId") String oId,
									Model model) {
		OrderVO order = orderService.getOrderInfo(oId);
		log.info("{}",order);
		model.addAttribute("order", order);
		
		// 적립 예정 마일리지 계산
		long totalOrderPrice = memberService.getTotalUsePrice(user.getId());
		int grade = MemberGrade.of(totalOrderPrice - order.getObeforePrice());
		int expectAddMileage = (int) Math.floor(order.getObeforePrice() * MemberGrade.of(grade).getAccruRate());
		model.addAttribute("addM", expectAddMileage);
		
		return "/wck/order/order_comp";
	}
	
	
	
//	@Autowired(required=true)
//	private IamportClient api;
//	
//	@ResponseBody
//	@RequestMapping(value = "/orderConfirmation/{imp_uid}")
//	public IamportResponse<Payment> paymentByImpUid(
//			@PathVariable(value = "imp_uid") String imp_uid
//			, @RequestParam(required = false) String merchant_uid
//			, Model model
//			, HttpSession session)
//					throws IamportResponseException, IOException {
//		log.info("Get order Confirmation >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		// imp_uid=imp_389291808416
//		// merchant_uid=nobody_1676246291597
//		// imp_success=false
//		// error_msg=F0005%3A결제가+중단되었습니다%28imp_389291808416%29.01+%7C+사용자가+결제를+취소+하였습니다.
//		
//		// http//locaslhost/wck/checkout/orderConfirmation?imp_uid=imp_904255033222&merchant_uid=403e-60a0-4e0f-85e8-673c&imp_success=true
//			
//		log.info("imp uid ", imp_uid);
////		log.info("imp success ", imp_success);
//		log.info("imp MU ", merchant_uid);
////		log.info("imp error msg ", msg);
////		log.info("{}", insertOrder);
//		
//		return api.paymentByImpUid(imp_uid);
//	}
	
	
	
}
