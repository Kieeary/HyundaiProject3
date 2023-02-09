package com.wck.service;

import java.util.ArrayList;
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
		
		List<ProductVO> a = productService.getProductList(br, gd, sC, tC);
		
		for(ProductVO b : a) {
			log.info(b.getPid());
		}
	}

}
