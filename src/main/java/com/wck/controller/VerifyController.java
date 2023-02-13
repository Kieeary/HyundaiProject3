package com.wck.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.wck.domain.InsertOrderDTO;
import com.wck.security.domain.Account;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Log4j2
@RequiredArgsConstructor
@RestController
public class VerifyController {
	private final IamportClient api;
//    private final ParticipantService participantService;
//    private final ChallengeService challengeService;
	
	@Value("${iamport.restapi.key}") 
    private String restapi;
	@Value("${iamport.secret.key}") 
    private String secret;
	@Value("${private.ip}") 
    private String privateIp;
	
	// 생성자를 통해 REST API 와 REST API secret 입력
	@Autowired
	public VerifyController() {
		this.api = new IamportClient(restapi,secret);
	}

	// iamport를 이용하여 결제하기를 완료하였을때
	@PostMapping("/verifyIamport/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(@PathVariable String imp_uid, HttpServletRequest request)
			throws IamportResponseException, IOException {
		log.warn("paymentByImpUid 진입");
		IamportResponse<Payment> paymentIamportResponse = api.paymentByImpUid(imp_uid);
		Payment payment = paymentIamportResponse.getResponse();
		HttpSession session = request.getSession(false); // 로그인이 된 사용자가 세션을 사용하고 있으므로 false 세팅을 해준것임
		session.setAttribute("payment", payment);
		session.setMaxInactiveInterval(60); // 60초동안 세션을 유지하고 있겠다는 뜻임
		log.warn("{}", paymentIamportResponse);
		return paymentIamportResponse;
	}
	
	@RequestMapping(value = "/orderCompleteMobile", method = RequestMethod.GET)
	public ResponseEntity<String> orderCompleteMobile(
			@RequestParam("imp_uid") String imp_uid,
			@RequestParam("merchant_uid") String merchant_uid, 
			Model model, 
			Locale locale, 
			HttpSession session)
			throws IamportResponseException, IOException, URISyntaxException {
		log.info("===================orderCompleteMobile");
		IamportResponse<Payment> result = api.paymentByImpUid(imp_uid);

		// 결제 가격과 검증결과를 비교한다.
		if (result.getResponse().getAmount().compareTo(BigDecimal.valueOf(100)) == 0) {
			log.info("KG 이니시스 결제 완료");
			log.info("imp_uid(=oId) : " + imp_uid);
			log.info("merchant_uid : " + merchant_uid);
			
//			return "redirect:wck/checkout/orderConfirmation2/"+imp_uid;
			
			URI redirectUri = new URI("http://"+privateIp+"/wck/checkout/orderConfirmation2/"+imp_uid);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(redirectUri);
			return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
		} else {
			//DB 날리기 Order_item, Order, Payment 
			return new ResponseEntity<> ("http://"+privateIp+"/wck/shoppingbag", HttpStatus.OK);
//			return "redirect:wck/shoppingbag";
		}
		
	}

}
