package com.wck.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wck.domain.DetailProductVO;
import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductCommonVO;
import com.wck.domain.ProductVO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductMapperTest {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	void getLikeProduct() {
		String mid = "123123";
		List<ProductCommonVO> list = productMapper.getLikeProductList(mid);
		log.info(list);
	}
	
	@Test
	void getProductColor() {
		String pId = "101110005";
		String pcId = "101110005_MU";
		ProductColorVO vo = productMapper.getProductColor(pId, pcId);
		log.info(vo);
	}
	
	@Test
	void getProductList() {
		String gd = "we";
		List<ProductVO> vo = productMapper.getProducts(null, gd, null, null);
		
		for(ProductVO a : vo) {
			if(a.getPid().equals("MM2C7KTO041H9E")) {
			log.info("PID :" + a.getPid());
			for(DetailProductVO b : a.getDetailProduct()) {
				log.info("PCID : " + b.getPcid());
			}
			}
		}
	}

}
