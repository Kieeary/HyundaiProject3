package com.wck.service;

import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wck.domain.BrandCategoryVO;
import com.wck.domain.ProductVO;
import com.wck.domain.SecondCategoryVO;

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
		String tC = "me03";
		int start = 0;
		int last = 1;
		
		List<ProductVO> a = productService.productList(br, gd, sC, tC, start, last);
		
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
	
	@Test
	void getProductsCount() {
		String gd = "me";
		String br = null;
		String sc = null;
		String tc = null;
		int cnt = productService.getProductsCount(br, gd, sc, tc);
		
		log.info(cnt);
	}
	
	@Test
	void getSecondCategory() {
		String depth1name = "me";
		List<SecondCategoryVO> a = productService.getSecondCategory(depth1name);
		
		for(SecondCategoryVO s : a) {
			log.info(s.getSecondname());
		}
	}
	
	@Test
	void getBrandCategory() {
		List<BrandCategoryVO> a = productService.getBrandCategory();
		
		for(BrandCategoryVO s : a) {
			log.info(s.getBrandname() + ", " + s.getBrandcode());
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
