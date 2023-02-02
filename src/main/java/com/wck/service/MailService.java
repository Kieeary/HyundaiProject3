package com.wck.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * Reference : https://kitty-geno.tistory.com/43
 * @author wangjh789
 */

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;
	
	public void sendMail(String rcvEmail, String encodedPw) {
		
		SimpleMailMessage smsg = new SimpleMailMessage();
		smsg.setTo(rcvEmail);
		
		smsg.setSubject("[WCK HyundaiProject3] 임시비밀번호 발송");
		smsg.setText("임시 비밀번호 : "+ encodedPw);
		
		javaMailSender.send(smsg);
	}
}
