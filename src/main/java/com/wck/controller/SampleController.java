package com.wck.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/index")
	public String[] hello() {
		return new String[] {"3차", "화이팅"};
	}
}
