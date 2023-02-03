package com.wck.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wck.domain.MemberVO;
import com.wck.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApi {
	
	private final MemberService memberSerivce;
	
	/**
	 * 이미 가입된 회원이 있는지 확인 = 가입된 회원이 있다면 : true / 없다면 : false 
	 */
	@GetMapping("/isExist")
	public ResponseEntity<Boolean> isExist(@RequestParam("email") String email){
		MemberVO member = memberSerivce.findMemberByEmail(email);
		
		return new ResponseEntity<Boolean>(member!=null, HttpStatus.OK);
	}
	
	

}
