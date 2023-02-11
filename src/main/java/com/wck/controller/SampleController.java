package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class SampleController {

	
	@GetMapping("/wck/file-test")
	public String hello() {
		return "wck/sample/file_test";
	}
	
	
}
