package com.wck.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class EventCouponVO {

	private String cpid;
	private String eno;
	private String mid;
	private Date cpIssueDate;
	private Date cpExpireDate;
	private Date cpUseDate;
	private int cpStatus;

	private String etitle;
	private String econtent;
	private Date eIssueDate;
	private Date eExpireDate;
	private int elimitCount;
	private int ecount;
	private String eImg;
	private int eDiscount;
	private int eStatus;
	private String eDetailImg;
	private String eCouponTitle;
}
