package com.wck.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class CartServiceTests {
	@Autowired
	private CartService cartService;
	
//	author : 김한울
//	purpose : 장바구니, 쇼핑백 추가 서비
	@Test
	public void addCartTest() {
		log.info("기존에 존재하는 상품 테스트");
		cartService.addCart("7c-41f8-8c6a-739159990a8d", "SH2CAKCD036M_BK_95", 3);
		log.info("기존에 존재하지 않는 상품 테스트");
		cartService.addCart("7c-41f8-8c6a-739159990a8d", "SH2CAKCD036M_MB_100", 1);
	}

}
