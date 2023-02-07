package com.wck.service;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.wck.domain.Criteria;
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
	
	public List<ProductCommonVO> getLikeProductList(String mId, Criteria cri) {
		return productMapper.getLikeProductList(mId, cri);
	}
	
	
	/*
	 * 1 : insert / 2 : delete
	 */
	public int toggleLikeProduct(String mId, String pId) {
		boolean isExist = productMapper.existLikeProduct(mId, pId);
		int row = 0;
		if(isExist) 
			row = productMapper.deleteLikeProduct(mId, pId);
		else 
			row = productMapper.insertLikeProduct(mId, pId);
		if(row == 0) throw new RuntimeException("toggleLikeProduct Error 발생");
		return isExist ? 0 : 1;
	}
	
	public ProductCommonVO getProductDetail(String pId) {
		ProductCommonVO vo = productMapper.getProductDetailByPid(pId);
		if(vo == null) throw new RuntimeException("존재하지 않는 PID");
		return vo;
	}
	
	
	public List<ProductVO> productList(@Nullable String br, @Nullable String gd,
										@Nullable String sC, @Nullable String tC) {
		
		List<ProductVO> productList = productMapper.productList(br, gd, sC, tC);
		return productList;
	}

	public int getLikeProductCount(String id) {
		return productMapper.getLikeProductCount(id);
	}

}
