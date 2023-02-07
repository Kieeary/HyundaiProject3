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
	
	/*
	 * 해당 product_common 의 product_color 정보 가져오기
	 */
	@GetMapping("/{pId}/color/{pcId}")
	public ResponseEntity<ProductColorVO> getProductColor(
			@PathVariable("pId") String pId,
			@PathVariable("pcId") String pcId){
		log.info("pId : "+pId);
		log.info("pcId : "+pcId);
		ProductColorVO vo = productService.getProductColor(pId, pcId);
		log.info(vo);
		return new ResponseEntity<ProductColorVO>(vo,HttpStatus.OK);
		
	}
	
}
