package com.wck.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderVO {

	private String oid;
	private String ozipcode;
	private String oaddress1;
	private String oaddress2;
	private String oreceiver;
	private String ophone;
	private String otel;
	private String omeno;
	private String oemail;
	private int ousedMileage;
	private int obeforePrice;
	private int oafterPrice;
	private String ostatus;
	private Date odate;
	
	private String cpid;
	private String eno;
	private String ecoupontitle;
	private int discount;
	
	private String pmcode;
	private String pmmethod;
	private String pmcompany;
	
	List<OrderProductVO> orderProducts;
	
	

}
