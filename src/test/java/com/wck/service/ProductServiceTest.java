package com.wck.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wck.domain.ProductVO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductServiceTest {
	@Autowired
	private ProductService productService;
	
	@Test
	void getProductList() {
		String gd = "me";
		String br = null;
		String sC = null;
		String tC = "me031";
		
		List<ProductVO> a = productService.productList(br, gd, sC, tC);
		
		for(ProductVO b : a) {
			log.info(b.getPid());
		}
	}
	
	@Test
	void searchProductList() {
		String keyword = "캐시미어";
		List<ProductVO> a = productService.searchProductsList(keyword);
		
		for(ProductVO b : a) {
			log.info(b.getPname());
		}
		
	}
	
	// author : 김한울
	// purpose : 장바구니 상품 변경시 가능 여부 확인
	@Test
	void getProdStockService() {
		String psid = "IL2CANPC782W_BK_70";
		int qty = 1000;
		
		String result = productService.getProductStock(psid, qty);
		log.info(result.equals("") ? "상품 변경 가능" : result);
	}

}
