package com.wck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wck.domain.InsertMemberDTO;
import com.wck.domain.MemberVO;
import com.wck.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public MemberVO findMemberByEmail(String email) {

		MemberVO member = memberMapper.findOneByEmail(email, "Email");
		if (member == null)
			memberMapper.findOneByEmail(email, "Google");
		return member;
	}

	public void insertEmailMember(InsertMemberDTO insertMember) {
		MemberVO member = insertMember.toMemberVO();
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		int row = memberMapper.insertOne(member);
		if(row == 0) throw new RuntimeException("Db 에러 발생");
	}

}
