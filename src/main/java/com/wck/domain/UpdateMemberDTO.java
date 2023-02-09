package com.wck.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateMemberDTO {
	
	@Size(min = 2,message = "이름을 입력해주세요")
	private String name;
	
	@Email
	private String email;
	
	@NotNull(message = "생년월일을 확인해주세요")
	private Integer year;
	@NotNull(message = "생년월일을 확인해주세요")
	private Integer month;
	@NotNull(message = "생년월일을 확인해주세요")
	private Integer day;
	
	@NotNull(message = "성별을 확인해주세요")
	private Integer gender;
	
	@Size(min = 2, max = 3, message = "휴대폰 번호를 확인해주세요")
	private String phone1;
	@Size(min = 3, max = 4, message = "휴대폰 번호를 확인해주세요")
	private String phone2;
	@Size(min = 3,max = 4, message = "휴대폰 번호를 확인해주세요")
	private String phone3;
	
	@NotNull(message = "주소를 입력해주세요")
	private String zipcode;
	@NotNull(message = "주소를 입력해주세요")
	private String address1;
	private String address2;
	
	@SuppressWarnings("deprecation")
	public UpdateMemberDTO(MemberVO member) {
		
		this.name = member.getName();
		this.email = member.getEmail();
		
		this.year = member.getBirth().getYear();
		this.month = member.getBirth().getMonth();
		this.day = member.getBirth().getDate();
		
		this.gender = member.getGender();
		
		// 휴대전화 token 더 해야됨
		this.phone1 = member.getPhone().substring(0,3);
		this.phone2 = member.getPhone().substring(3,7);
		this.phone3 = member.getPhone().substring(7);
		
		this.zipcode = member.getZipcode();
		this.address1 = member.getAddress1();
		this.address2 = member.getAddress2();				
	}
}
