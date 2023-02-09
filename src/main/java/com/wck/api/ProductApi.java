package com.wck.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wck.domain.ProductCommonVO;
import com.wck.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/api/product")
@RestController
@RequiredArgsConstructor
@Log4j2
public class ProductApi {

	private final ProductService productService;
	
	
	@GetMapping("{pId}")
	public ResponseEntity<ProductCommonVO> getProductDetail(@PathVariable("pId") String pId){
		log.info("getProductDetail");
		ProductCommonVO prod = productService.getProductDetail(pId);
		return new ResponseEntity<ProductCommonVO>(prod, HttpStatus.OK);
	}
}
