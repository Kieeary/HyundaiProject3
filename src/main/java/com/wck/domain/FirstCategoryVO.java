package com.wck.domain;

import java.util.List;

import lombok.Data;

@Data
public class FirstCategoryVO {

	private String firstname;
	private String depth1name;
	List<SecondCategoryVO> secondCategory;
}
