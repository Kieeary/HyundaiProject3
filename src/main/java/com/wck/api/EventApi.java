package com.wck.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wck.domain.EventCriteria;
import com.wck.domain.EventVO;
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
	
}
