package com.wck.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.wck.security.domain.Account;

@SpringBootTest
public class FromUserDetailsServiceTest {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Test
	void loadUser() {
		Account account = (Account) userDetailsService.loadUserByUsername("user1@gmail.com");
		Assertions.assertEquals(account.getEmail(), "user1@gmail.com");
	}
}
