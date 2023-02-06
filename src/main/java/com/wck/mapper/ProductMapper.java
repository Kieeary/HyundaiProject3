package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import com.wck.domain.ProductVO;

@Mapper
public interface ProductMapper {
	
	public List<ProductVO> productList(@Nullable String br, @Nullable String gd, 
					@Nullable String sC, @Nullable String tC); 

}
