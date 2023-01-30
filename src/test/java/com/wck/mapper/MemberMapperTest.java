package com.wck.mapper;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.MemberVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	void getMemberByEmail() throws SQLException {
		MemberVO member =  memberMapper.findOneByEmail("user1@gmail.com", "Email");
		Assertions.assertThat(member.getEmail()).isEqualTo("user1@gmail.com");
	}
	
	@Test
	@Transactional
	void insertOne() throws SQLException{
		MemberVO member = new MemberVO();
		String email = "test@gmail.com";
		String id = UUID.randomUUID().toString().substring(10);
		String name = "user"+id;
		String phone = "01000000000";
		Date birth = new Date();
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
		Assertions.assertThat(row).isEqualTo(1);
		
		MemberVO findMember = memberMapper.findOneByEmail(email, "Google");
		Assertions.assertThat(findMember.getEmail()).isEqualTo("test@gmail.com");
		
	}

}
