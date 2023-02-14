package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wck.domain.BrandCategoryVO;
import com.wck.domain.Criteria;
import com.wck.domain.DetailProductVO;
import com.wck.domain.FirstCategoryVO;
import com.wck.domain.OrderProductVO;
import com.wck.domain.ProductColorChipVO;
import com.wck.domain.ProductCommonVO;
import com.wck.domain.ProductInfoVO;
import com.wck.domain.ProductVO;
import com.wck.domain.SecondCategoryVO;
import com.wck.domain.ThirdCategoryVO;
import com.wck.domain.WithProductVO;

@Mapper
public interface ProductMapper {
	
	public List<ProductCommonVO> getLikeProductList(@Param("mId") String mId, @Param("cri") Criteria cri);

	public List<ProductVO> getProducts(@Param("br") String br, @Param("gd") String gd, 
					@Param("sC") String sC, @Param("tC") String tC, int start, int last); 
	
	public List<ProductVO> searchProducts(String keyword);
	
	public List<WithProductVO> getWithProducts(String pcid);
	
	public List<ProductColorChipVO> getColorChip(String pid);

	public ProductInfoVO getProductInfo(String pcid, String pid);
	
	public int getProductsCount(@Param("br") String br, @Param("gd") String gd, 
			@Param("sC") String sC, @Param("tC") String tC);
	
	public String getBrandImg(String br);

	public List<FirstCategoryVO> getCategoryName();
	
	public List<FirstCategoryVO> getFirstCategory(String br);
	
	public List<SecondCategoryVO> getSecondCategory(@Param("depth1name") String depth1name, @Param("br") String br);
	
	public List<ThirdCategoryVO> getThirdCategory(@Param("depth2name") String depth2name, @Param("br") String br);
	
	public List<BrandCategoryVO> getBrandCategory();
	
	public String brandName(int bno);
	
	public String genderName(String depth1name);
	
	public String secondCategoryName(String depth2name);
	
	public int deleteLikeProduct(String mId, String pId); 
	
	public boolean existLikeProduct(String mId, String pId);
	
	public int insertLikeProduct(String mId,String pId);

	public int getLikeProductCount(String mId);
	
	public ProductCommonVO getProductDetailByPid(String pId);

	public List<String> getSizeSet(String pcId);
	
	public List<DetailProductVO> getColorSet(String pId);

	public int getProductStock(String psId);
	
	public ProductCommonVO getProducSimpleInfo(String pId);
	
	public OrderProductVO getProductInfoWithColorName(String psId);
	
	public int updateProductStock(String psId, int subQty);
	
	public boolean isLikeProduct(String mid, String pid);
}
