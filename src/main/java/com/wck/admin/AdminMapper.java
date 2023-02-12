package com.wck.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.MemberVO;

@Mapper
public interface AdminMapper {

	public List<MemberVO> getMemberList();
	
}
