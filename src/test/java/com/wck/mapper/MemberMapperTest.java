package com.wck.mapper;

import java.sql.SQLException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
