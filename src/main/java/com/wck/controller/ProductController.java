package com.wck.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wck.domain.ProductInfoVO;
import com.wck.domain.ProductVO;
import com.wck.service.MemberService;
import com.wck.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller	
@RequestMapping("/wck/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	// 상품 조회
	@GetMapping("/list")
	public String getBrandProducts() {
		
		String br = null;
		String gd = "we";
		String fC = null;
		String sC = null;
		String tC = null;
		
		//List<ProductVO> list = productService.productList(br, gd, sC, tC);
		
		//log.info(list);
		
		return "wck/product/list";
	}
	
	@ResponseBody
	@GetMapping("/list/p")
	public Object list(@RequestParam("gender") String gender) {
		
		log.info(gender);	
		String br = null;
		String gd = gender;
		String fC = null;
		String sC = null;
		String tC = null;
		log.info(gd);
		List<ProductVO> list = productService.productList(br, gd, sC, tC);
		
		return list;
		
	}
	
	@GetMapping("/p")
	public String getProductInfo(Model model, @RequestParam String pid, @RequestParam String pcid) {
		String pi = pid;
		String pci = pcid;
		log.info(pid);
		log.info(pci);
		
		ProductInfoVO productInfo = productService.getProductInfo(pcid, pid);
		
		log.info("size" + productInfo.getSizeNstock().size());
		model.addAttribute("productInfo", productInfo);
	
		return "wck/product/product_detail";
	}

	
}
