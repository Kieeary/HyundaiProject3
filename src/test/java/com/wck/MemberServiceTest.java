package com.wck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.InsertMemberDTO;
import com.wck.domain.MemberVO;
import com.wck.service.MemberService;

@SpringBootTest
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	
	@Test
	@Transactional
	void isnertMember() {
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
	
}
