package com.wck.domain;

import org.hibernate.validator.constraints.Range;

import com.sun.istack.internal.NotNull;

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
