package com.wck.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wck.security.domain.Account;

@Mapper
public interface MemberMapper {
	
	public Account getAccountByUserId(String userId);

}
