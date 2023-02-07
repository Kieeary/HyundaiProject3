package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wck.domain.Criteria;
import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductCommonVO;
import org.springframework.lang.Nullable;

import com.wck.domain.ProductVO;

@Mapper
public interface ProductMapper {
	
	public List<ProductCommonVO> getLikeProductList(@Param("mId") String mId, @Param("cri") Criteria cri);

	
	public List<ProductVO> productList(@Nullable String br, @Nullable String gd, 
					@Nullable String sC, @Nullable String tC);

	public int deleteLikeProduct(String mId, String pId); 
	
	public boolean existLikeProduct(String mId, String pId);
	
	public int insertLikeProduct(String mid,String pId);

	public int getLikeProductCount(String mId);
	
	public ProductCommonVO getProductDetailByPid(String pId);

}
