package com.wck.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.FindPwDTO;
import com.wck.domain.MemberVO;

@Mapper
public interface MemberMapper {

	public MemberVO findOneByEmail(String email, String loginType);
	public int insertOne(MemberVO member);
	public List<MemberVO> findAllByNameAndBirth(String name, Date birth);
	public MemberVO findOneByNameAndEmail(FindPwDTO findPw);
}
