package com.wck.mapper;

import static org.mockito.ArgumentMatchers.intThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wck.domain.CartVO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class CartMapperTests {
	@Autowired
	private CartMapper cartMapper;
	
//	author : 김한울
//	purpose : 장바구니에 몇 개의 상품이 존재하는지  확인
//			  존재하지 않는 상품이면 0, 존재하면 장바구니 수량 출력
	@Test
	void countCartTest() {
		int result = cartMapper.countCart("7c-41f8-8c6a-739159990a8d", "SH2CAKCD036M_BK_95");
		log.info(result!=0 ? "이미 "+result+"개의 품이 존재" : "장바구니에 없는 상품");
	}

//	author : 김한울
//	purpose : 장바구니에 새로운 상품 등록
	@Test
	void insertCartTest() {
		int result = cartMapper.insertCart("7c-41f8-8c6a-739159990a8d", "SH2CAKCD036M_BK_95", 1);
		log.info(result==1 ? "장바구니 담기 완료" : "장바구니 담기 실패");
	}

//	author : 김한울
//	purpose : 특정 회원의 장바구니 정보들 조회
//	조회 데이터 : 브랜드명, 상품 이름, 상품 가격, 상품 색상, 상품 사이즈, 장바구니 수량, 좋아요 여부
	@Test
	void readCartTest() {
		String mId = "7c-41f8-8c6a-739159990a8d";
		List<CartVO> prods = cartMapper.readCart(mId);
		for (CartVO cartVO : prods) {
			log.info(cartVO.getBName());
			log.info(cartVO.getPName());
			log.info(cartVO.getPColorName());
		}
	}
	
//	author : 김한울
//	purpose : 장바구니 특정 상품 삭제
	@Test
	void deleteCartTest() {
		String mId = "7c-41f8-8c6a-739159990a8d";
		String pSId = "SH2CAKCD036M_BK_95";
		int result = cartMapper.deleteCart(mId, pSId);
		log.info(result==1 ? "장바구니 삭제 완료" : "장바구니 삭제 실패");
	}
	

}
