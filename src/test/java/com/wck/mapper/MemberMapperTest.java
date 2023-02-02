package com.wck.mapper;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.FindPwDTO;
import com.wck.domain.MemberVO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	void getMemberByEmail()  {
		MemberVO member =  memberMapper.findOneByEmail("user1@gmail.com", "Email");
		Assertions.assertEquals(member.getEmail(),"user1@gmail.com" );
	}
	
	@Test
	@Transactional
	void insertOne(){
		MemberVO member = new MemberVO();
		String email = "test@gmail.com";
		String id = UUID.randomUUID().toString().substring(10);
		String name = "user"+id;
		String phone = "01000000000";
		Date birth = new Date(new java.util.Date().getTime());
		int gender = 1;
		String loginType = "Google";
		int tosNo = 1;
		boolean enabled = true;
		String role = "ROLE_USER";
		int grade = 1;
		int mileage = 0;
		member.setEmail(email);
		member.setId(id);
		member.setPhone(phone);
		member.setPassword("{noop}1111");
		member.setName(name);
		member.setBirth(birth);
		member.setGender(gender);
		member.setLoginType(loginType);
		member.setTosNo(tosNo);
		member.setEnabled(enabled);
		member.setRole(role);
		member.setGrade(grade);
		member.setMileage(mileage);
		
		int row = memberMapper.insertOne(member);
		Assertions.assertEquals(row,1);
		
		MemberVO findMember = memberMapper.findOneByEmail(email, "Google");
		log.info(findMember.getBirth());
		Assertions.assertEquals(findMember.getEmail(),"test@gmail.com");
	}
	
	@Test
	@SuppressWarnings("deprecation")
	void findMemberByNameAndBirth() {
		String name = "왕종휘";
		Date birth = Date.valueOf("1997-07-27");
		List<MemberVO> members = memberMapper.findAllByNameAndBirth(name, birth);
		log.info(members);
		Assertions.assertEquals(members.size(),	1);
	}
	
	@Test
	void findMemberByNameAndEmail() {
		String email = "user1@gmail.com";
		FindPwDTO findPw = new FindPwDTO();
		findPw.setName("왕종휘");
		findPw.setEmail(email);
		MemberVO member = memberMapper.findOneByNameAndEmail(findPw);
		log.info(member);
		Assertions.assertEquals(member.getEmail(), email);
	}

}
