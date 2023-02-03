package com.wck.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.FindIdDTO;
import com.wck.domain.FindPwDTO;
import com.wck.domain.InsertMemberDTO;
import com.wck.domain.MemberVO;
import com.wck.mapper.MemberMapper;
import com.wck.util.UuidUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	private final MailService mailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public MemberVO findMemberByEmail(String email) {

		MemberVO member = memberMapper.findOneByEmail(email, "Email");
		if (member == null)
			member = memberMapper.findOneByEmail(email, "Google");
		return member;
	}

	public void insertEmailMember(InsertMemberDTO insertMember) {
		MemberVO member = insertMember.toMemberVO();
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		int row = memberMapper.insertOne(member);
		if(row == 0) throw new RuntimeException("Db 에러 발생");
	}
	
	public List<MemberVO> findMemberListByNameAndBirth(FindIdDTO findId) {
		String name = findId.getName();
		Date birth = Date.valueOf(findId.getBirthYear()+"-"+ findId.getBirthMonth()+"-"+findId.getBirthDay());
		List<MemberVO> members = memberMapper.findAllByNameAndBirth(name,birth);
		log.info("members = "+ members);
		return members;
	}
	
	/**
	 * member 가 있을 경우 true, 없을 경우 false
	 */
	public boolean findMemberByNameAndEmail(FindPwDTO findPwDto) {
		MemberVO member = memberMapper.findOneByNameAndEmail(findPwDto);
		return member!= null;
	}
	
	/**
	 * 새로운 비밀번호로 암호화하고, 메일발송
	 */
	@Transactional
	public void resetPasswordByEmail(String email) {
		String pw = UuidUtil.generateUUID(12);
		String encodedPw = passwordEncoder.encode(pw);
		
		int row = memberMapper.updatePasswordOne(email, encodedPw);
		if(row == 0) throw new RuntimeException("DB에러 발생");
		
		mailService.sendMail(email,pw);
		
	}

}