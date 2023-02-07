package com.wck.service;

import static org.mockito.ArgumentMatchers.intThat;

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
		String mId = "7c-41f8-8c6a-739159990a8d";
		String psIds[] = {"SJ2C9ASZ097W_OW_235", "CM2C9ASZ756WS_WT_36", "TG2D3ASZ066MS3_WT_9"};
		
		for (String psId : psIds) {
			cartService.addCart(mId, psId, 1);
		}
	}
	
	@Test
	public void deleteCartTest() {
		String mId = "7c-41f8-8c6a-739159990a8d";
		String psIds[] = {"SJ2C9ASZ097W_OW_235", "CM2C9ASZ756WS_WT_36", "TG2D3ASZ066MS3_WT_9"};
		
		for (String psId : psIds) {
			int result = cartService.deleteCartProd(mId, psId);
			log.info(result==1 ? (psId+" 상품 장바구니 삭제 완료") : "장바구니 삭제 실패");
		}
	}

}
