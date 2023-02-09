package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wck.domain.Criteria;
import com.wck.domain.DetailProductVO;
import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductCommonVO;
import com.wck.domain.ProductInfoVO;

import org.springframework.lang.Nullable;

import com.wck.domain.ProductVO;

@Mapper
public interface ProductMapper {
	
	public List<ProductCommonVO> getLikeProductList(@Param("mId") String mId, @Param("cri") Criteria cri);

	public List<ProductVO> getProducts(@Nullable String br, @Nullable String gd, 
					@Nullable String sC, @Nullable String tC); 

	public ProductInfoVO getProductInfo(String pcid, String pid);

	public int deleteLikeProduct(String mId, String pId); 
	
	public boolean existLikeProduct(String mId, String pId);
	
	public int insertLikeProduct(String mId,String pId);

	public int getLikeProductCount(String mId);
	
	public ProductCommonVO getProductDetailByPid(String pId);

	public List<String> getSizeSet(String pcId);
	
	public List<DetailProductVO> getColorSet(String pId);


}
