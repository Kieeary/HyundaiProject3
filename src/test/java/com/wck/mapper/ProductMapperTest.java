package com.wck.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.BrandCategoryVO;
import com.wck.domain.Criteria;
import com.wck.domain.DetailProductVO;
import com.wck.domain.FirstCategoryVO;
import com.wck.domain.ProductColorChipVO;
import com.wck.domain.ProductCommonVO;
import com.wck.domain.ProductInfoVO;
import com.wck.domain.ProductStockVO;
import com.wck.domain.ProductVO;
import com.wck.domain.SecondCategoryVO;
import com.wck.domain.ThirdCategoryVO;
import com.wck.domain.WithColorChipInfoVO;
import com.wck.domain.WithProductVO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductMapperTest {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	void getLikeProduct() {
		Criteria cri = new Criteria();
		cri.setPageSize(10);
		cri.setCurrentPage(1);
		String mid = "123123";
		List<ProductCommonVO> list = productMapper.getLikeProductList(mid,cri);
		log.info(list);
		log.info(list.size());
	}
	
	
	/*
	 * 정기범
	 */
	@Test
	void getProductList() {
		String gd = "me";
		String tC = "me031";
		int start = 0;
		int last = 1;
		List<ProductVO> vo = productMapper.getProducts(null, gd, null, tC, start, last);
		
		for(ProductVO a : vo) {

			log.info("PID :" + a.getPid());
			for(DetailProductVO b : a.getDetailProduct()) {
				log.info("PCID : " + b.getPCId());
			}
		}
		
	}
	
	/*
	 * 정기범
	 */
	@Test
	void getBrandProductList() {
		String br = "3";
		
		int start = 0;
		int last = 1;
		List<ProductVO> vo = productMapper.getProducts(br, null, null, null, start, last);
		
		for(ProductVO a : vo) {

			log.info("PID :" + a.getPid());
			for(DetailProductVO b : a.getDetailProduct()) {
				log.info("PCID : " + b.getPCId());
			}
		}
		
	}
	
	/*
	 * 정기범
	 */
	@Test
	void getBrandImg() {
		String br = "1";
		String gd = "me";
		
	}
	
	/*
	 * 정기범
	 */
	@Test
	void getProductSize() {
		String pcid = "MM2C7KTO041H9E_BK";
		String pid = "MM2C7KTO041H9E";
		
		ProductInfoVO productInfo = productMapper.getProductInfo(pcid, pid);
		
		for(ProductStockVO a : productInfo.getSizeNstock()) {
			log.info(a);
		}
	}
	
	/*
	 * 정기범
	 */
	@Test
	void searchProducts() {
		String keyword = "바지";
		List<ProductVO> vo = productMapper.searchProducts(keyword);
	
		for(ProductVO a : vo) {

			log.info("PID :" + a.getPid());
			for(DetailProductVO b : a.getDetailProduct()) {
				log.info("PCID : " + b.getPCId());
			}
		}
	}
	
	/*
	 * 정기범
	 */
	@Test
	void getColorChip() {
		String pid = "MM2C7KTO041H9E";
		
		List<ProductColorChipVO> colorchip = productMapper.getColorChip(pid);
		
		for(ProductColorChipVO a : colorchip) {
			log.info("=======" + a.getPcid());
			log.info("=======" + a.getPcchipimg());
		}
	}
	
	/* 
	 * 정기범
	 */
	@Test
	void getWithProduct() {
		String pcid = "SH2C7WPC510M_BG";
		
		List<WithProductVO> withproduct = productMapper.getWithProducts(pcid);
		
		for(WithProductVO a : withproduct) {
			log.info(a.getWithpcid());
			for(WithColorChipInfoVO b : a.getWithcolorchip()) {
				log.info(b.getPcchipimg());
				log.info(b.getOtherpcid());
			}
			log.info("=============");
		}
	}
	
	/*
	 * 정기범
	 */
	@Test
	void getProductsCount() {
		
		String br = null;
		String gd = "we";
		String sc = null;
		String tc = null;
		
		int a = productMapper.getProductsCount(br, gd, sc, tc);
		
		log.info(a);
	}
	
	/*
	 * 정기범
	 */
	@Test
	void getCategoryName() {
		
		List<FirstCategoryVO> a = productMapper.getCategoryName();
		
		for(FirstCategoryVO b : a) {
			log.info(b);
		}
		return ;
	}
	
	/*
	 * 정기범
	 */
	@Test
	void getFirstCategory() {
		String brand = "3";
		
		List<FirstCategoryVO> a = productMapper.getFirstCategory(brand);
		for(FirstCategoryVO s : a) {
			log.info(s.getFirstname());
		}
	}
	
	/*
	 * 정기범
	 */
	@Test
	void getSecondCategory() {
		
		String depth1name = "me";
		String br = null;
		
			List<SecondCategoryVO> a = productMapper.getSecondCategory(depth1name, null);
			for(SecondCategoryVO s : a) {
				log.info(s.getSecondname());
			}
	}
	
	/*
	 * 정기범
	 */
	@Test
	void getThirdCategory() {
		
		String depth2name = "me03";
		String br = null;
		
			List<ThirdCategoryVO> a = productMapper.getThirdCategory(depth2name, br);
			for(ThirdCategoryVO s : a) {
				log.info(s.getDetailname());
			}
	}
	
	/*
	 * 정기범
	 */
	@Test
	void getBrandCategory() {
		
		List<BrandCategoryVO> a = productMapper.getBrandCategory();
		for(BrandCategoryVO s : a) {
			log.info(s.getBrandname());
		}
	}
	
	/*
	 * 정기범
	 */
	@Test
	void gender() {
		String gd = "we";
		String a = productMapper.genderName(gd);
		
		log.info(a);
	}
	
	/*
	 * 정기범
	 */
	@Test
	void secondCategory() {
		String sc = "we03";
		String a = productMapper.secondCategoryName(sc);
		
		log.info(a);
	}
	
	/*
	 * 정기범
	 */
	void brand() {
		int br = 3;
		String a = productMapper.brandName(br);
		
		log.info(a);
	}
	
	@Transactional
	void deleteLikeProduct() {
		String pId = "SH2CAKCD036M";
		String mId = "123123";
		int row = productMapper.deleteLikeProduct(mId, pId);
		Assertions.assertEquals(row, 1);
	}
	
	@Test
	void existLikeProduct() {
		String pId = "SH2CAKCD036M";
		String mId = "123123";
		boolean exist = productMapper.existLikeProduct(mId, pId);
		log.info(exist);
	}
	
	@Test
	void getProductDetailByPid() {
		String pId = "O22C8WOP141W";
		ProductCommonVO vo = productMapper.getProductDetailByPid(pId);
		log.info(vo);
	}

//	author : 김한울
//	purpose : 상품 color 옵션에 따른 사이즈 추출 (품절 제외)  
	@Test
	void getSizeSetTest() {
		String pcId = "SJ2C9ASZ097W_OW";
		List<String> sizeSet = productMapper.getSizeSet(pcId);
		log.info("{}", sizeSet);
	}
	
//	author : 김한울
//	purpose : 상품 color 옵션 추출(PCID, color chip img, color code, color name)  
	@Test
	void getColorSetTest() {
		String pId = "SJ2C9ASZ097W";
		List<DetailProductVO> colorSet = productMapper.getColorSet(pId);
		for (DetailProductVO color : colorSet) {
			log.info("PCID > " + color.getPCId());
			log.info("COLORCODE > " + color.getPCColorCode());
			log.info("COLORNAME > " + color.getColorName());
		}
	}
	
	@Test
	void isLikeProduct() {
		String pid = "SJ2C9ASZ097W";
		String mid = "123123";
		boolean result = productMapper.isLikeProduct(mid, pid);
		log.info(result);
	}
	

}
