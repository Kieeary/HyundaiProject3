package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wck.service.MemberService;
import com.wck.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/wck/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	// 상품 조회
	@GetMapping("/{brand}/{gender}/{firstCategory}/{secondCategory}/{thirdCategory}")
	public void getBrandProducts(@RequestParam(required = false) String brand,
								 @RequestParam(required = false) String gender,
								 @RequestParam(required = false) String firstCategory,
								 @RequestParam(required = false) String secondCategory,
								 @RequestParam(required = false) String thirdCategory) {
		
		String br = brand;
		String gd = gender;
		String fC = firstCategory;
		String sC = secondCategory;
		String tC = thirdCategory;
		
		
		
	}

	
}
