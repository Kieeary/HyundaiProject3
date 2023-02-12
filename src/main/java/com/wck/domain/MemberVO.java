package com.wck.domain;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class MemberVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String tel;
	private String zipcode;
	private String address1;
	private String address2;
	private Date birth;
	private int gender;
	private String refId;
	private String loginType;
	private int tosNo;
	private boolean enabled;
	private String role;
	private int grade;
	private int mileage;
}
