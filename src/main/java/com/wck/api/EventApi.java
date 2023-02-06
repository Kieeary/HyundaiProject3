package com.wck.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wck.domain.EventCouponVO;
import com.wck.domain.EventCriteria;
import com.wck.domain.EventVO;
import com.wck.security.domain.Account;
import com.wck.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventApi {

	private final EventService eventService;
	
	@GetMapping("/list")
	public ResponseEntity<List<EventVO>> getList(
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "past", defaultValue = "past") boolean past
			){
		EventCriteria cri = new EventCriteria();
		cri.setPageSize(pageSize);
		cri.setCurrentPage(currentPage);
		cri.setPast(past);
		
		List<EventVO> list = eventService.getEventList(cri);
		return new ResponseEntity<List<EventVO>>(list,HttpStatus.OK);
	}

	@GetMapping("/mycoupon")
	public ResponseEntity<List<EventCouponVO>> getMyCoupon(
			@AuthenticationPrincipal Account account){
		List<EventCouponVO> list =  eventService.getCouponListByMid(account.getId(), 1);
		return new ResponseEntity<List<EventCouponVO>>(list, HttpStatus.OK);
	}
	
	/**
	 * 참여 헀는지 확인
	 */
	@GetMapping("{id}/check")
	public ResponseEntity<Boolean> checkAlreayJoin(
			@PathVariable("id") int eventId,
			@AuthenticationPrincipal Account account){
		// 로그인 하지 않은 유저의 경우 참여하지 않음
		if(account == null) return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		
		boolean result = eventService.isJoined(eventId, account.getId());
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@PostMapping("{id}/coupon")
	public ResponseEntity<String> getCoupon(
			@AuthenticationPrincipal Account account,
			@PathVariable("id") int eventId
			){
		boolean result = eventService.issueCoupon(eventId,account.getId());
		if(!result)
			return new ResponseEntity<String>("SUCCESS", HttpStatus.NOT_ACCEPTABLE);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	
}
