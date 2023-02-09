package com.wck.service;

import java.util.List;
import java.util.Map;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.wck.domain.Criteria;
import com.wck.domain.DetailProductVO;
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
		
		List<ProductVO> productList = productMapper.getProducts(br, gd, sC, tC);
		return productList;
	}

	public int getLikeProductCount(String id) {
		return productMapper.getLikeProductCount(id);
	}
	
	public List<String> getSizeSet(String pcId) {
		return productMapper.getSizeSet(pcId);
	}
	
	public List<DetailProductVO> getColorSet(String pId) {
		return productMapper.getColorSet(pId);
	}
	
	public String getProductStock(String psId, int qty) {
		String pId = psId.split("_")[0];
		int stock = productMapper.getProductStock(psId);

		String msg = "";
		
		log.info("pid : "+pId);
		
		if(qty > stock) {
			ProductCommonVO prodInfo = productMapper.getProductSympleInfo(pId);
			msg = "["+prodInfo.getBName()+"] "+prodInfo.getPName()+"의 재고수량은 "+stock+"개입니다. 다시 입력해 주시기 바랍니다.";
		}
		
		log.info("재고 수량 : "+msg);
		
		return msg;
	}

}
