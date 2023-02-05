package com.wck.api;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wck.domain.MemberVO;
import com.wck.domain.UpdateMemberDTO;
import com.wck.security.domain.Account;
import com.wck.service.MemberService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
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
	
	@PostMapping("/check/pw")
	public ResponseEntity<Boolean> isValidPw(
			@RequestBody TmpDTO dto,
			@AuthenticationPrincipal Account account){
		boolean result = memberSerivce.checkPassword(account.getEmail(), dto.getPassword());
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> changeApi(
			@AuthenticationPrincipal Account account,
			@Valid @RequestBody UpdateMemberDTO member,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) { 
			log.info(member);
			log.info(bindingResult.getAllErrors());
			return new ResponseEntity<String>("ERROR", HttpStatus.NOT_ACCEPTABLE);
		}
		memberSerivce.updateInfo(member);
		log.info(member);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.CREATED);
	}
	
	@PostMapping("/update/pw")
	public ResponseEntity<String> changePw(
			@AuthenticationPrincipal Account account,
			@Valid @RequestBody TmpDTO dto, 
			BindingResult bindingResult
			){
		if(bindingResult.hasErrors())
			return new ResponseEntity<String>("ERROR", HttpStatus.NOT_ACCEPTABLE);
		
		memberSerivce.updatePassword(account.getEmail(), dto.getPassword());
		return new ResponseEntity<String>("SUCCESS", HttpStatus.CREATED);
	}
	
	
	
	@Data
	static class TmpDTO{
		@Size(min = 8)
		private String password;
	}
	
	

}
