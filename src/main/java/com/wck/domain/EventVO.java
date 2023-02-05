package com.wck.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class EventVO {

	private int no;
	private String title; 
	private String content;
	private Date issueDate;
	private Date expireDate;
	private int limitCount;
	private int count;
	private String img;
	private int discount;
	private int status;
	private String detailImg;
	private String couponTitle;
	
}
