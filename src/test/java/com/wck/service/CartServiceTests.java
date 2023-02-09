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
		String mId = "7c-41f8-8c6a-739159990a8d";
		String psIds[] = {"SY2C9WSS957W_KK_61","TG2C7TTS040WFE_DG_S", "MU2C9TPC035N3K_BK_10", "IL2CANPC782W_OW_64"};
		for (String psId : psIds) {
			cartService.addCart(mId, psId, 1);
		}
	}

	@Test
	public void deleteCartTest() {
		String mId = "7c-41f8-8c6a-739159990a8d";
		String psIds[] = { "SY2C9WSS957W_KK_61", "TM2C7WSCS01WP3_DN_67", "TG2C7TTS040WFE_DG_S" };

		for (String psId : psIds) {
			int result = cartService.deleteCartProd(mId, psId);
			log.info(result == 1 ? (psId + " 상품 장바구니 삭제 완료") : "장바구니 삭제 실패");
		}
	}

}
