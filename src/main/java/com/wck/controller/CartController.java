package com.wck.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wck.domain.DetailProductVO;
import com.wck.service.CartService;
import com.wck.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/wck/shoppingbag")
@RequiredArgsConstructor
public class CartController {
	
	@Autowired
	private final CartService cartService;
	
	@Autowired
	private final ProductService productService;
	
//	author : 김한울
//	purpose : 장바구니 상품 삭제
	@GetMapping("/deleteProd")
	public String deleteProd(@RequestParam("mid") String mId, @RequestParam(value = "psid", required = false) String psid) {
		log.info("user id > "+mId);
		
		if (psid == null) {
//			cartService.deleteCart(mId);
		} else {
			log.info("psid > "+ psid);
			cartService.deleteCartProd(mId, psid);
		}
		
		return "redirect:/wck/shoppingbag";
	}
	
	@GetMapping("/deleteProds")
	public String deleteProds(@RequestParam("mid") String mId, @RequestParam("psids") List<String> psids) {
		log.info("user id > " + mId);
		log.info("{}",psids);
		cartService.deleteSelectedProds(mId, psids);
		return "redirect:/wck/shoppingbag";
	}
	
	@GetMapping(value = "/getCurrentOpt", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getCurrentOpt(@RequestParam("pcid") String pcId) {
		String pId = pcId.split("_")[0];
		log.info("PID > " + pId);
		List<DetailProductVO> colorSet = productService.getColorSet(pId);
		List<String> sizeSet = productService.getSizeSet(pcId);
		Map<String, Object>	datas = new HashMap<String, Object>();
		datas.put("colorSet", colorSet);
		log.info("color # : " + colorSet.size());
		datas.put("sizeSet", sizeSet);			
		return new ResponseEntity<>(datas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSizeOpt", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<String>> getSizeOpt(@RequestParam("pcid") String pcId) {
		log.info("PCID > " + pcId);
		List<String> sizeSet = productService.getSizeSet(pcId);
		log.info("{} ",sizeSet);
		return new ResponseEntity<>(sizeSet, HttpStatus.OK);
	}
	
	@PostMapping(value = "/changeProd", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, String>> changeCartProd(@RequestBody Map<String, Object> data){
		String mid = (String) data.get("mid");
		String oldPsid = (String) data.get("old_psid"); 
		String newPsid = (String) data.get("new_psid");
		int qty = Integer.parseInt((String) data.get("qty"));
		
		String msg = productService.getProductStock(newPsid, qty);
		
		log.info(msg);
		
		if(msg.equals("")) {
			cartService.deleteCartProd(mid, oldPsid);
			cartService.addCart(mid, newPsid, qty);
			return new ResponseEntity<> (HttpStatus.OK);
		} else {
			Map<String, String> result = new HashMap<>();
			result.put("msg", msg);
			log.info(msg);
			return new ResponseEntity<> (result, HttpStatus.OK);
		}
	}
}
	