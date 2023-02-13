package com.wck.security.domain;

import lombok.Data;

@Data
public class Resources {

	private Long id;
	private String resourceName;
	private String httpMethod;
	private int orderNum;
	private String role;
}
