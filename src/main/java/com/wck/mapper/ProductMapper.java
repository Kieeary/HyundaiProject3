package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductCommonVO;

@Mapper
public interface ProductMapper {
	
	public List<ProductCommonVO> getLikeProductList(String mId);

	public ProductColorVO getProductColor(String pId, String pcId);

}
