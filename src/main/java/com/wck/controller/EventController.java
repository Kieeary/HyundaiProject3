package com.wck.controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wck/event")
public class EventController {
	
	@GetMapping
	public String eventList() {
		return "event/event_list";
	}
	
	@GetMapping("/{id}")
	public String eventDetail(@PathParam("id") String eventId) {
		return "event/event";
	}

}
