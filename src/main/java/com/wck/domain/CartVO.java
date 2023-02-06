package com.wck.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CartVO {
	private String mId;
	private String pSId;
	private int pQuantity;
	
	private String pName;
	private int pStatus;
	private String bName;
	
	private String pCColorCode; 
	private String pCPrice;
	private int pSStock;
	private String pSize;
	private int like;
	
}
