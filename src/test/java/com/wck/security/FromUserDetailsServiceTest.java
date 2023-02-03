package com.wck.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.wck.security.domain.Account;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
public class FromUserDetailsServiceTest {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Test
	void loadUser() {
		Account account = (Account) userDetailsService.loadUserByUsername("user1@gmail.com");
		Assertions.assertThat(account.getEmail()).isEqualTo("user1@gmail.com");
	}
}
