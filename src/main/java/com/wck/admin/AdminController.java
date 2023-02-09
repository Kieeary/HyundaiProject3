package com.wck.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/user")
	public String user() {
		return "admin/user";
	}
	@GetMapping("/product")
	public String product() {
		return "admin/product";
	}
	@GetMapping("/order")
	public String order() {
		return "admin/order";
	}
	@GetMapping("/event")
	public String event() {
		return "admin/event";
	}
	@GetMapping("/security")
	public String access() {
		return "admin/security";
	}

}
