package com.wck.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wck.domain.Criteria;
import com.wck.domain.MemberVO;
import com.wck.domain.ProductCommonVO;
import com.wck.domain.UpdateMemberDTO;
import com.wck.security.domain.Account;
import com.wck.service.MemberService;
import com.wck.service.ProductService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApi {
	
	private final MemberService memberSerivce;
	private final ProductService productService;
	
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
	
	@GetMapping("/like")
	public ResponseEntity<Map<String, Object>> getLikeProductList(
			@AuthenticationPrincipal Account account,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "currentPage", defaultValue = "0") int currentPage){
		Criteria cri = new Criteria();
		log.info("pageSize : "+pageSize);
		cri.setPageSize(pageSize);
		cri.setCurrentPage(currentPage);
		Map<String, Object> map = new HashMap<>();
		List<ProductCommonVO> list = productService.getLikeProductList(account.getId(),cri);
		log.info(list.size());
		int count = productService.getLikeProductCount(account.getId());
		map.put("list", list);
		map.put("count",count);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/like/{pId}")
	public ResponseEntity<String> toggleLikeProduct(
			@AuthenticationPrincipal Account account,
			@PathVariable("pId") String pId){
		int result = productService.toggleLikeProduct(account.getId(), pId);
		String msg = result == 1? "insert success" : "delete success";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
	@Data
	static class TmpDTO{
		@Size(min = 8)
		private String password;
	}
	
	

}
