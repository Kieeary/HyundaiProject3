package com.wck.domain;

import java.util.List;

import lombok.Data;

@Data
public class ProductCommonVO {
	private String pId;
	private String pName;
	private String pNote;
	private int pStatus;
	private int bNo;
	private String bName;
	
	private List<ProductColorVO> colors;
}
