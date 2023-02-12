package com.wck.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wck.domain.OrderCriteria;
import com.wck.domain.OrderVO;
import com.wck.security.domain.Account;
import com.wck.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Log4j2
public class OrderApi {

	private final OrderService orderService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getOrderList(
			@AuthenticationPrincipal Account account,
			@RequestParam(name = "month", defaultValue = "1") int month,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage) {
		log.info("month : "+ month);
		log.info("pageSize : "+ pageSize);
		log.info("currentPage : "+ currentPage);
		OrderCriteria cri = new OrderCriteria();
		cri.setMonth(month);
		cri.setPageSize(pageSize);
		cri.setCurrentPage(currentPage);
		
		
		List<OrderVO> list = orderService.getOrderList(cri, account.getId());
		int count = orderService.getOrderCount(cri, account.getId());
		
		Map<String, Object> map = new HashMap<>();
		map.put("orders", list);
		map.put("count", count);
		
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	

}
