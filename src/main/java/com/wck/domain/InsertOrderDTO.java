package com.wck.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsertOrderDTO {
	
	@NotNull
	private int beforeprice;
	@NotNull
	private int afterprice;
	@NotEmpty
	private String rcpt_name;
	@NotEmpty
	private String pmCode;
	@NotEmpty
	private String pmCompany;
	@NotEmpty(message = "핸드폰 번호를 입력해주세요")
	private String phone;
	@NotEmpty(message = "주소를 입력해주세요")
	private String zipcode;
	@NotEmpty(message = "주소를 입력해주세요")
	private String address1;
	
	@NotEmpty
	private String[] psids;
	@NotNull
	private int[] counts;

	private String address2;
	private String gl_radio_dlvr_msg;
	private String frontDoorSelectMessage;
	private String voucherCode;
	private int useMileage;
	
	private String oId;
	private String status = "결제 완료";
}
