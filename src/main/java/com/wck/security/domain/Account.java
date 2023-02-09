package com.wck.security.domain;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Account extends User implements OAuth2User{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String password;
	private String name;
	private String email;
	private int gender;
	
	private String loginType;
	private int grade;
	
	private int cartCount;
	
	// oauth2 일때 만
	private Map<String, Object> OA2_attr;
	
	
	
	@Override
	public Map<String, Object> getAttributes() {
		return this.OA2_attr;
	}
	
	// OAuth2UserDetailsService 용 생성자
	public Account(
			String id,
			String email,
            String password, 
            String name,
            int gender,
            String loginType,
            List<GrantedAuthority> authorities,
			Map<String, Object> OA2_attr) {
		this(id,email,password,name,gender,loginType, authorities);
		this.OA2_attr = OA2_attr;
	}
	
	
	public Account(
			String id,
			String email,
            String password, 
            String name,
            int gender,
            String loginType,
            List<GrantedAuthority> authorities
            ) {
		super(email, password, authorities);
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.loginType = loginType;
	}
	
}
