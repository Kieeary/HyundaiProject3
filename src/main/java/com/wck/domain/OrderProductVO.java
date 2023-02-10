package com.wck.domain;

import lombok.Data;

@Data
public class OrderProductVO {
	
	private String psId;
	private String bName;
	private String pName;
	private String colorName;
	private String pImg;
	private String pSize;
	private int quantity;
}
