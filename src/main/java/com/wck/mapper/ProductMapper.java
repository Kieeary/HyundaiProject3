package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductCommonVO;
import org.springframework.lang.Nullable;

import com.wck.domain.ProductVO;

@Mapper
public interface ProductMapper {
	
	public List<ProductCommonVO> getLikeProductList(String mId);

	public ProductColorVO getProductColor(String pId, String pcId);
	
	public List<ProductVO> getProducts(@Nullable String br, @Nullable String gd, 
					@Nullable String sC, @Nullable String tC); 

}
