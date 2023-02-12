package com.wck.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wck.domain.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminMapper mapper;
	
	List<MemberVO> getMemberList(){
		return mapper.getMemberList();
	}
}
