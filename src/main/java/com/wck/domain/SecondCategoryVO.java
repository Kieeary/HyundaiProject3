package com.wck.domain;

import java.util.List;
import lombok.Data;

@Data
public class SecondCategoryVO {

	private String secondname;
	private String depth2name;
	private List<ThirdCategoryVO> thirdCategory; 
}
