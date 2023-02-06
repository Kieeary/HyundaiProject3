package com.wck.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProductColorVO {
	
	private String pcId;
	private String pcImg1;
	private String pcImg2;
	private String pcImg3;
	
	private String pcChipImg;
	private String pcColorCode;
	
	private int pcPrice;
	
	private Date pReleaseDate;
	
	private List<ProductStockVO> stocks;
	

}
