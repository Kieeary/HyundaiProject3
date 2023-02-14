package com.wck.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.wck.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class VerifyController {
	private IamportClient api;
	
	@Autowired
	private final OrderService orderService;
	
	@Value("${iamport.restapi.key}") 
    private String restapi;
	@Value("${iamport.secret.key}") 
    private String secret;
	@Value("${private.ip}") 
    private String privateIp;
	
	@PostConstruct
	public void setApi() {
		this.api = new IamportClient(restapi,secret);
	}
	
	/*
	 * author : 김한울
	 * purpose : KG 이니시스 결제 과정
	 * 			 결제 성공으로 넘어가는 과정
	 */
	@RequestMapping(value = "/orderCompleteMobile", method = RequestMethod.GET)
	public ResponseEntity<String> orderCompleteMobile(
			@RequestParam("imp_uid") String imp_uid,
			@RequestParam("merchant_uid") String merchant_uid, 
			Model model, 
			HttpServletRequest request)
			throws IamportResponseException, IOException, URISyntaxException {
		
		IamportResponse<Payment> result = api.paymentByImpUid(imp_uid);

		if (result.getResponse().getAmount().compareTo(BigDecimal.valueOf(100)) == 0) {
			log.info("KG 이니시스 결제 완료");
			log.info("imp_uid(=oId) : " + imp_uid);
			log.info("merchant_uid : " + merchant_uid);
			
			// merchant_uid(pmcode)로 oid 찾기
			String oId  = orderService.getOrderId(merchant_uid);
			
			// 결제 성공 화면으로 이동
			URI redirectUri = new URI("http://"+privateIp+"/wck/checkout/orderConfirmation2/"+oId);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(redirectUri);

//			// 로그인이 된 사용자가 세션을 사용하고 있으므로 false 세팅을 해준것임
//			HttpSession session = request.getSession(false);
//			session.setMaxInactiveInterval(120); // 120초 동안 세션 유지
			
			return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
		} else {
			boolean deleteResult = orderService.deleteFailOrder(merchant_uid);
			log.info("Delete Fail Order Info in DB result : "+deleteResult);
			// 결제 실패시 장바구니 페이지로 이동
			return new ResponseEntity<> ("http://"+privateIp+"/wck/shoppingbag", HttpStatus.OK);
		}
		
	}

}
