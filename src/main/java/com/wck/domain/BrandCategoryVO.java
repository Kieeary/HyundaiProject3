package com.wck.domain;

import java.util.List;

import lombok.Data;

@Data
public class BrandCategoryVO {

	private String brandname;
	private String brandcode;
	private List<FirstCategoryVO> firstCategory;
}
