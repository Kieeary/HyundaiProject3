package com.wck.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CartVO {
	private String mId;
	private String pImg;
	private String pSId;
	private int pQuantity;
	
	private String pName;
	private String pStatus;
	private String bName;
	
	private String pCColorCode; 
	private String pCPrice;
	private String pSStock;
	private String pSize;
	private int like;
	private double accumRate;
	
}
