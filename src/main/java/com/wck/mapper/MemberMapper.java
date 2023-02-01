package com.wck.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.MemberVO;

@Mapper
public interface MemberMapper {

	public MemberVO findOneByEmail(String email, String loginType) throws SQLException;
	public int insertOne(MemberVO member) throws SQLException;
}
