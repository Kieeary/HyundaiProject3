package com.wck.service;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.wck.domain.ProductVO;
import com.wck.mapper.CartMapper;
import com.wck.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductMapper productMapper;
	
	public List<ProductVO> productList(@Nullable String br, @Nullable String gd,
										@Nullable String sC, @Nullable String tC) {
		
		List<ProductVO> productList = productMapper.productList(br, gd, sC, tC);
		return productList;
	}

}
