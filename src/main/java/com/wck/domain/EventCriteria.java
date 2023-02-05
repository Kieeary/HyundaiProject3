package com.wck.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EventCriteria extends Criteria{

	private boolean past;
	
}
