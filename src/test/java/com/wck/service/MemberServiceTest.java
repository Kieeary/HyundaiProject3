package com.wck.service;

import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.ArgumentMatchers.intThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.FindIdDTO;
import com.wck.domain.FindPwDTO;
import com.wck.domain.InsertMemberDTO;
import com.wck.domain.MemberVO;
import com.wck.service.MemberServiceTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	
	@Test
	@Transactional
	void insertMember() {
		String password = "123123";
		InsertMemberDTO dto = new InsertMemberDTO();
		dto.setEmail("test@test.com");
		dto.setPassword(password);
		dto.setName("tester");
		dto.setPhoneFront("010");
		dto.setPhoneMiddle("1234");
		dto.setPhoneBack("1234");
		dto.setBirthYear(1997);
		dto.setBirthMonth(7);
		dto.setBirthDay(27);
		dto.setGender(1);
		
		memberService.insertEmailMember(dto);
		
		MemberVO member = memberService.findMemberByEmail("test@test.com");
		Assertions.assertEquals(member.getEmail(), "test@test.com");
		// passwordEncoder test
		Assertions.assertNotEquals(member.getPassword(), password);
	}
	
	@Test
	void findMemberListByNameAndBirth() {
		FindIdDTO findId = new FindIdDTO();
		findId.setName("왕종휘");
		findId.setBirthYear(1997);
		findId.setBirthMonth(7);
		findId.setBirthDay(27);
		List<MemberVO> members = memberService.findMemberListByNameAndBirth(findId);
		log.info(members);
	}
	
	@Test
	void findMemberByNameAndEmail() {
		FindPwDTO findPw = new FindPwDTO();
		String email = "user1@gmail.com";
		findPw.setEmail(email);
		findPw.setName("왕종휘");
		boolean result = memberService.findMemberByNameAndEmail(findPw);
		log.info(result);
		Assertions.assertEquals(result, true);
	}
	
//	author : 김한울
//	purpose : 회원별 마일리지 적립률 조
	@Test
	void getMileageAddRateTest() {
		String mId = "7c-41f8-8c6a-739159990a8d";
		double result = memberService.getMileageAddRate(mId);
		log.info("User Mileage accrual rate > " + result);
	}
	
}
