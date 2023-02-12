package com.wck.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wck.domain.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminController {
	
	private final AdminService adminService;
	
	@GetMapping("/user")
	public String user(Model model) {
		List<MemberVO> members = adminService.getMemberList();
		log.info(members);
		model.addAttribute("members", members);
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
