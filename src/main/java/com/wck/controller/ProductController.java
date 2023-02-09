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
import com.wck.domain.DetailProductVO;
import com.wck.domain.ProductColorChipVO;
import com.wck.domain.ProductInfoVO;
import com.wck.domain.ProductStockVO;
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
		
		
		return "wck/product/list";
	}
	
	@ResponseBody
	@GetMapping("/list/p")
	public Object list(@RequestParam(value="brand", required=false) String brand,
					   @RequestParam(value="gender", required=false) String gender,
					   @RequestParam(value="secCat", required=false) String secCat,
					   @RequestParam(value="thrCat", required=false) String thrCat) {
		
		log.info(gender);	
		String br = brand;
		String gd = gender;
		String sC = secCat;
		String tC = thrCat;
		
		log.info(gd);
		log.info(br);
		log.info(sC);
		log.info(tC);
		
		List<ProductVO> list = productService.productList(br, gd, sC, tC);
		
		for(ProductVO a : list) {
			log.info(a.getPid());
		}
		
		return list;
		
	}
	
	@GetMapping("/p")
	public String getProductInfo(Model model, @RequestParam String pid, @RequestParam String pcid) {
		String pi = pid;
		String pci = pcid;
		log.info(pid);
		log.info(pci);
		
		ProductInfoVO productInfo = productService.getProductInfo(pcid, pid);
		List<ProductColorChipVO> colorChip = productService.getColorChip(pid);
		
		log.info("size" + productInfo.getSizeNstock().size());
		model.addAttribute("productInfo", productInfo);
		model.addAttribute("productColor", colorChip);
	
		for(ProductColorChipVO a : colorChip) {
			log.info("=======" + a.getPcid());
			log.info("=======" + a.getPcchipimg());
		}
		return "wck/product/product_detail";
	}
	
	@GetMapping("/p/chgColor")
	@ResponseBody
	public Object chgProductColor(@RequestParam String pid, @RequestParam String pcid) {
		
		log.info(pid);
		log.info(pcid);
		
		ProductInfoVO productInfo = productService.getProductInfo(pcid, pid);
		
		log.info(productInfo.getPcid());
		
		return productInfo;
	}

	
}
