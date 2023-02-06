package com.wck.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wck.domain.ProductColorVO;
import com.wck.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/api/product")
@RestController
@RequiredArgsConstructor
@Log4j2
public class ProductApi {

	private final ProductService productService;
	
	
	@GetMapping("/{pId}/color/{pcId}")
	public ResponseEntity<ProductColorVO> getProductColor(
			@PathVariable("pId") String pId,
			@PathVariable("pcId") String pcId){
		
		return new ResponseEntity<ProductColorVO>(productService.getProductColor(pId, pcId),HttpStatus.OK);
		
	}
	
}
