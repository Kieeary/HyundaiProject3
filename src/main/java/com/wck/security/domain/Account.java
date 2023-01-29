package com.wck.security.domain;

import java.util.Set;


import lombok.Data;

@Data
public class Account{
	
	private String mId;
	private String mPassword;
	private String mName;
	private Set<String> userRoles;

}
