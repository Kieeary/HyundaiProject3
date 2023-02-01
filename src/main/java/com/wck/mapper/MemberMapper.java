package com.wck.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.MemberVO;

@Mapper
public interface MemberMapper {

	public MemberVO findOneByEmail(String email, String loginType);
	public int insertOne(MemberVO member);
}
