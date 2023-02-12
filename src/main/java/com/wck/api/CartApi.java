package com.wck.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wck.domain.InsertCartDTO;
import com.wck.security.domain.Account;
import com.wck.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartApi {
	
	private final CartService cartService;
	
	/* author : 왕종휘
	 * 장바구니 추가
	 */
	@PostMapping("/add")
	public ResponseEntity<String> addCartApi(
			@AuthenticationPrincipal Account account,
			@Valid @RequestBody InsertCartDTO cart, BindingResult bindingResult){
		log.info(cart);
		if(bindingResult.hasErrors()) {
			log.info(bindingResult.getAllErrors());
			return new ResponseEntity<>("error occur",HttpStatus.OK);
		}
		
		log.info(cart.getPsId());
		log.info(cart.getQty());
		
		cartService.addCart(account.getId(), cart.getPsId(), cart.getQty());
		return new ResponseEntity<>("",HttpStatus.OK);
	}

}
