package com.wck.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertCartDTO {

	@NotNull
	private String psId;
	
	@Range(min = 1)
	private int qty;
}
