package com.wck.service;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductCommonVO;
import com.wck.domain.ProductVO;
import com.wck.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {
	
	private final ProductMapper productMapper;
	
	public List<ProductCommonVO> getLikedProductList(String mId) {
		return productMapper.getLikeProductList(mId);
	}
	
	public ProductColorVO getProductColor(String pId, String pcId){
		return productMapper.getProductColor(pId, pcId);
	}
	

	
	public List<ProductVO> productList(@Nullable String br, @Nullable String gd,
										@Nullable String sC, @Nullable String tC) {
		
		List<ProductVO> productList = productMapper.getProducts(br, gd, sC, tC);
		return productList;
	}

}
