package com.wck.domain;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductVO {
	private String pId;
	private String pName;
	private String pNote;
	private int pStatus;
	private int bNo;
	private String bName;
	
	private String pCColorCode; 
	private String pCPrice;
	private int pSStock;
	private String pSize;
	private int like;
	
	private List<DetailProductVO> detailProduct;

}
