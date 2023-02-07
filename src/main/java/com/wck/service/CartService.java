package com.wck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.CartVO;
import com.wck.mapper.CartMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartService {
	private final CartMapper cartMapper;

//	author : 김한울
//	purpose : 장바구니에 상품 추가
//			  이미 존재하는 상품의 경우 수량 증가
//			  존재하지 않는 상품의 경우 추가
	@Transactional
	public void addCart(String mId, String pSId, int addQuantity) {
		int count = cartMapper.countCart(mId, pSId); 
		if(count != 0) {
			log.info("이미 존재하는 상품");
			cartMapper.updateCart(mId, pSId, (count + addQuantity));
			log.info(addQuantity+"개의 상품 추가 완료");
		} else {
			log.info("존재하지 않는 상품");
			int result = cartMapper.insertCart(mId, pSId, addQuantity);
			log.info(result==1 ? "장바구니 담기 완료":"장바구니 담기 실패");
		}
	}

//	author : 김한울
//	purpose : 장바구니 상품 정보 불러오기
	public List<CartVO> readCartList(String mId){
		return cartMapper.readCart(mId);
	}
	
	public int deleteCartProd(String mId, String pSId) {
		return cartMapper.deleteCart(mId, pSId);
	}
}
