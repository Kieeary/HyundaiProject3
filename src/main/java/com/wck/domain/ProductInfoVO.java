package com.wck.domain;

import java.util.List;

import lombok.Data;

@Data
public class ProductInfoVO {
	
	private String pcid;
	private String bname;
	private String pname;
	private String pcimg1;
	private String pcimg2;
	private String pcimg3;
	private String pnote;
	private int pcprice;
	private List<PerProductVO> sizeNstock;
}
