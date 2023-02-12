package com.wck.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderCriteria extends Criteria{

	private int month;
	
}
