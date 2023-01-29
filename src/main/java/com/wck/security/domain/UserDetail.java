package com.wck.security.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class UserDetail extends User{
	private Account account;
	private List<String> roles;
	public UserDetail(Account account, List<String> roles) {
		super(
				account.getMId(),
				account.getMPassword(),
				roles.stream().map(SimpleGrantedAuthority::new)
	    			.collect(Collectors.toList())
	    		);
		this.account = account;
		this.roles = roles;
	}
	
	

}
