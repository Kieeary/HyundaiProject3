package com.wck.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wck.domain.SampleVO;
import com.wck.security.domain.Account;
import com.wck.service.SampleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class SampleController {

	private final SampleService sampleService;
	
	@GetMapping("/index")
	public String[] hello() {
		return new String[] {"3차", "화이팅"};
	}
	
	@GetMapping("/mapper-test")
	public ResponseEntity<SampleVO> mapperTest(){
		SampleVO sample = sampleService.getSample();
		return new ResponseEntity<SampleVO>(sample, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<String> userTest(@AuthenticationPrincipal Account account){
		log.info(account);
		return new ResponseEntity<>(account.toString(),HttpStatus.OK);
		
	}
}
