package com.wck.admin;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductStockVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/admin")
@Log4j2
@RequiredArgsConstructor
public class AdminApi {
	
	private final AdminService service;
	
	@PostMapping("/member/update")
	public ResponseEntity<String> updateUser(
			@RequestBody Map<String, Object> map){
		String id = (String) map.get("id");
		Integer grade = Integer.parseInt((String) map.get("grade"));
		Boolean enabled = Boolean.parseBoolean((String) map.get("enabled"));
		String role = (String) map.get("role");
		
		service.updateMember(id, grade, enabled, role);

		
		return new ResponseEntity<String>("["+id+"] member update success", HttpStatus.OK);
		
	}
	
	@PostMapping("/productcommon/update")
	public ResponseEntity<String> updateProductCommon(
			@RequestBody Map<String, Object> map
			){
		String id = (String) map.get("id");
		String name =  (String) map.get("name");
		String note = (String) map.get("note");
		Boolean status = ((String) map.get("status")).equals("1");
		String bno = (String) map.get("bno");
		
		service.updateProductCommon(id,name,note,status,bno);
		log.info("id : "+id);
		log.info("name : "+name);
		log.info("note : "+note);
		log.info("status : "+status);
		log.info("bno : "+bno);
		
		return new ResponseEntity<>("["+id+"] product update success", HttpStatus.OK);
	}
	
	@GetMapping("/productcommon/{pid}")
	public ResponseEntity<List<ProductColorVO>> getProductColorList(
			@PathVariable("pid") String pid){
		
		List<ProductColorVO> list = service.getProductColorList(pid);
		log.info(list);
		return new ResponseEntity<List<ProductColorVO>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/productcolor/{pcid}")
	public ResponseEntity<List<ProductStockVO>> getProductStockList(
			@PathVariable("pcid") String pcid){
		
		List<ProductStockVO> list = service.getProductStockList(pcid);
		log.info(list);
		return new ResponseEntity<List<ProductStockVO>>(list,HttpStatus.OK);
	}

}
