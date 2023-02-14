package com.wck.service;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

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
	
	public boolean isLikeProduct(String mid, String pid) {
		return productMapper.isLikeProduct(mid, pid);
	}
	
	public ProductCommonVO getProductDetail(String pId) {
		ProductCommonVO vo = productMapper.getProductDetailByPid(pId);
		if(vo == null) throw new RuntimeException("존재하지 않는 PID");
		return vo;
	}

	public List<ProductVO> getProductList(@Nullable String br, @Nullable String gd, @Nullable String sC,
			@Nullable String tC, int start, int last) {

		List<ProductVO> productList = productMapper.getProducts(br, gd, sC, tC, start, last);
		
		return productList;

	}
	
	public String getBrandImg(String br) {
		return productMapper.getBrandImg(br);
	}
	
	
	public List<ProductVO> productList(@Nullable String br, @Nullable String gd,
										@Nullable String sC, @Nullable String tC, int start, int last) {
		
		List<ProductVO> productList = productMapper.getProducts(br, gd, sC, tC, start, last);
		
		for(ProductVO a : productList) {
			log.info(a.getPid());
		}
		
		return productList;
	}
	
	/*
	 * 정기범
	 */
	public List<ProductVO> searchProductsList(String keyword) {
		
		return productMapper.searchProducts(keyword);
	}
	
	/*
	 * 정기범
	 */
	public List<WithProductVO> getWithProducts(String pcid) {
		return productMapper.getWithProducts(pcid);
	}
	
	/*
	 * 정기범
	 */
	public List<FirstCategoryVO> getCategoryName() {
		return productMapper.getCategoryName();
	}
	
	/*
	 * 정기범
	 */
	public List<ThirdCategoryVO> ThirdCategoryName(@Nullable String depth2name, @Nullable String brand) {
		
		List<ThirdCategoryVO> categoryName = productMapper.getThirdCategory(depth2name, brand);
		return categoryName;
		
	}
	
	/*
	 * 정기범
	 */
	public List<FirstCategoryVO> getFirstCategory(String brand) {
		
		List<FirstCategoryVO> categoryName = productMapper.getFirstCategory(brand);
		return categoryName;
	}

	
	/*
	 * 정기범
	 */
	public List<SecondCategoryVO> getSecondCategory(@Nullable String depth1name, @Nullable String brand) {
		List<SecondCategoryVO> categoryName = productMapper.getSecondCategory(depth1name, brand);
		return categoryName;
	}
	
	/*
	 * 정기범
	 */
	public List<ThirdCategoryVO> getThirdCategory(@Nullable String depth2name, @Nullable String brand) {
		List<ThirdCategoryVO> categoryName = productMapper.getThirdCategory(depth2name, brand);
		return categoryName;
	}
	
	/*
	 * 정기범
	 */
	public List<BrandCategoryVO> getBrandCategory(){
		List<BrandCategoryVO> brandCategory = productMapper.getBrandCategory();
		return brandCategory;
	}
	
	/*
	 * 정기범
	 */
	public String gender(String depth1name) {
		return productMapper.genderName(depth1name);
	}
	
	/*
	 * 정기범
	 */
	public String secondCategory(String depth2name) {
		return productMapper.secondCategoryName(depth2name);
	}
	
	/*
	 * 정기범
	 */
	public String brand(int bno) {
		return productMapper.brandName(bno);
	}
	
	public ProductInfoVO getProductInfo(String pcid, String pid) {
		
		return productMapper.getProductInfo(pcid, pid);
	}
	
	public int getLikeProductCount(String id) {
		return productMapper.getLikeProductCount(id);
	}


	
	public List<ProductColorChipVO> getColorChip(String pid){
		return productMapper.getColorChip(pid);
	}

	public List<String> getSizeSet(String pcId) {
		return productMapper.getSizeSet(pcId);
	}
	
	public List<DetailProductVO> getColorSet(String pId) {
		return productMapper.getColorSet(pId);

	}
	
	public int getProductsCount(@Nullable String br, @Nullable String gd,
										@Nullable String sC, @Nullable String tC) {
		
		return productMapper.getProductsCount(br, gd, sC, tC);
	}
	
	public String getProductStock(String psId, int qty) {
		String pId = psId.split("_")[0];
		int stock = productMapper.getProductStock(psId);
		String msg = "";
		log.info("pid : "+pId);
		
		if(qty > stock) {
			ProductCommonVO prodInfo = productMapper.getProducSimpleInfo(pId);
			msg = "["+prodInfo.getBName()+"] "+prodInfo.getPName()+"의 재고수량은 "+stock+"개입니다. 다시 입력해 주시기 바랍니다.";
		}
		log.info("재고 수량 : "+msg);
		return msg;
	}
	
	public OrderProductVO getPsIdInfo(String psid){
		return productMapper.getProductInfoWithColorName(psid);
	}
	
}
