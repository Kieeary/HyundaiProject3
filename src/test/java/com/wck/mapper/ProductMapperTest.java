package com.wck.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.Criteria;
import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductCommonVO;

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
	
	@Test
	void getProductColor() {
		String pId = "101110005";
		String pcId = "101110005_MU";
		ProductColorVO vo = productMapper.getProductColor(pId, pcId);
		log.info(vo);
	}
	
	@Test
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

}
