package com.wck.domain;


import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.wck.util.UuidUtil;

import lombok.Data;

@Data
public class InsertMemberDTO {
	
	@Email(message = "이메일 타입이 아닙니다.")
	private String email;
	@NotBlank(message = "비밀번호를 확인해주세요")
	private String password;
	@Size(min = 2, message = "이름을 확인해주세요")
	private String name;
	
	@Size(min = 3, message = "휴대폰 번호를 확인해주세요")
	private String phoneFront;
	@Size(min = 3, message = "휴대폰 번호를 확인해주세요")
	private String phoneMiddle;
	@Size(min = 3, message = "휴대폰 번호를 확인해주세요")
	private String phoneBack;
	
	@NotNull(message = "생년월일을 확인해주세요")
	private Integer birthYear;
	@NotNull(message = "생년월일을 확인해주세요")
	private Integer birthMonth;
	@NotNull(message = "생년월일을 확인해주세요")
	private Integer birthDay;
	
	private Integer gender = 1;
	
	public MemberVO toMemberVO() {
		MemberVO member = new MemberVO();
		member.setEmail(email);
		member.setPassword(password);
		member.setName(name);
		member.setPhone(phoneFront+phoneMiddle+phoneBack);
		member.setBirth(Date.valueOf(birthYear+"/"+birthMonth+"/"+birthDay));
		member.setGender(gender);
		
		member.setId(UuidUtil.generateUUID(10));
		member.setLoginType("Email");
		member.setTosNo(1);
		member.setEnabled(true);
		member.setRole("ROLE_MEMBER");
		member.setGrade(1);
		member.setMileage(0);
		
		return member;
	}

}
