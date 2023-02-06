package com.wck.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductCommonVO;
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
	

	

}
