package com.wck.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CartVO {
	private String PImg;
	private String PSId;
	private int PQuantity;
	
	private String PName;
	private String PStatus;
	private String BName;
	
	private String PColorName; 
	private int PCPrice;
	private String PSStock;
	private String PSize;
	private String like;
}
