package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wck.domain.EventVO;
import com.wck.service.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/wck/event")
@Log4j2
@RequiredArgsConstructor
public class EventController {
	
	private final EventService eventService;

	
	@GetMapping
	public String eventList() {
		return "event/event_list";
	}
	
	@GetMapping("/{id}")
	public String eventDetail(@PathVariable("id") int eventId, Model model) {
		log.info("eventDetail");
		EventVO event = eventService.getEvent(eventId);
		model.addAttribute("event", event);
		return "event/event";
	}
	
	

}
