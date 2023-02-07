package com.wck.domain;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductVO {
	private String pid;
	private String pname;
	private int pstatus;
	private int bno;
	private String bname;
	private int pcprice;
	
	private List<DetailProductVO> detailProduct;

}
