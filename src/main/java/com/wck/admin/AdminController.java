package com.wck.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wck.domain.Criteria;
import com.wck.domain.EventVO;
import com.wck.domain.MemberVO;
import com.wck.domain.PageDTO;
import com.wck.domain.ProductCommonVO;
import com.wck.domain.ProductVO;
import com.wck.mapper.ResourceMapper;
import com.wck.security.domain.Resources;
import com.wck.security.service.SecurityResourceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminController {
	
	private final AdminService adminService;
	private final ResourceMapper resourceMapper;
	
	/*
	 * author : 왕종휘
	 * purpose : 회원 조회 페이지
	 */
	@GetMapping("/user")
	public String user(Model model) {
		List<MemberVO> members = adminService.getMemberList();
		log.info(members);
		model.addAttribute("members", members);
		return "admin/user";
	}
	
	/*
	 * author : 왕종휘
	 * purpose : 상품 조회 페이지
	 */
	@GetMapping("/product")
	public String product(
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			Model model
			) {
		Criteria cri = new Criteria();
		cri.setCurrentPage(currentPage);
		cri.setPageSize(pageSize);
		
		int total = adminService.getProductCommonCount();
		List<ProductCommonVO> products = adminService.getProductCommonList(cri);
		
		PageDTO dto = new PageDTO(cri, total);
		
		model.addAttribute("products", products);
		model.addAttribute("paging", dto);
		
		
		return "admin/product";
	}
	
	/*
	 * author : 왕종휘
	 * purpose : 주문 조회 페이지
	 */
	@GetMapping("/order")
	public String order() {
		return "admin/order";
	}
	
	/*
	 * author : 왕종휘
	 * purpose : 이벤트 조회 페이지
	 */
	@GetMapping("/event")
	public String event(Model model) {
		
		List<EventVO> events =  adminService.getEventList();
		model.addAttribute("events", events);
		
		return "admin/event";
	}
	
	/*
	 * author : 왕종휘
	 * purpose : 리소스 조회 페이지
	 */
	@GetMapping("/security")
	public String access(Model model) {
		List<Resources> list = resourceMapper.findAllResources();
		model.addAttribute("resources", list);
		return "admin/security";
	}
	

}
