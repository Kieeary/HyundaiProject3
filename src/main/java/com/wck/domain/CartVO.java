package com.wck.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CartVO {
	private String mId;
	private String PSId;
	private int PQuantity;
}
