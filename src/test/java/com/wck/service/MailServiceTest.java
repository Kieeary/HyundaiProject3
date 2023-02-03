package com.wck.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MailServiceTest {

	@Autowired
	private MailService mailService;
	
	@Test
	void sendMail() {
		mailService.sendMail("wangjh789@gmail.com","test Email");
		log.info("메일을 확인해주세요");
	}
}
