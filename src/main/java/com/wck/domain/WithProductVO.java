package com.wck.domain;

import java.util.List;

import lombok.Data;

@Data
public class WithProductVO {
	
	private String pcid;
	private String withpcid;
	private String pcimg1;
	private int pcprice;
	private String pname;
	private String bname;
	private String pid;
	
	List<WithColorChipInfoVO> withcolorchip;
}
