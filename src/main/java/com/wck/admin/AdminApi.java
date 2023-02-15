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

import com.nimbusds.oauth2.sdk.Response;
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
	
	/*
	 * author : 왕종휘
	 * purpose : 회원 업데이트 수행
	 */
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
	
	/*
	 * author : 왕종휘
	 * purpose : 상품 업데이터 수행
	 */
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
	
	/*
	 * author : 왕종휘
	 * purpose : 상품 대분류 조회
	 */
	@GetMapping("/productcommon/{pid}")
	public ResponseEntity<List<ProductColorVO>> getProductColorList(
			@PathVariable("pid") String pid){
		
		List<ProductColorVO> list = service.getProductColorList(pid);
		log.info(list);
		return new ResponseEntity<List<ProductColorVO>>(list,HttpStatus.OK);
	}
	
	/*
	 * author : 왕종휘
	 * purpose : 상품 중분류 조회
	 */
	@GetMapping("/productcolor/{pcid}")
	public ResponseEntity<List<ProductStockVO>> getProductStockList(
			@PathVariable("pcid") String pcid){
		
		List<ProductStockVO> list = service.getProductStockList(pcid);
		log.info(list);
		return new ResponseEntity<List<ProductStockVO>>(list,HttpStatus.OK);
	}
	
	/*
	 * author : 왕종휘
	 * purpose : 리소스 업데이트
	 */
	@PostMapping("/security/update")
	public ResponseEntity<String> updateResource(
			@RequestBody Map<String, Object> map){
		int id = Integer.parseInt((String) map.get("id"));
		String resourceName = (String) map.get("resourceName");
		String httpMethod = (String) map.get("httpMethod");
		int orderNum = Integer.parseInt((String) map.get("orderNum"));
		String role = (String) map.get("role");
		
		service.updateResource(id, resourceName, httpMethod, orderNum, role);
		
		return new ResponseEntity<String>("update success", HttpStatus.OK);
	}
	
	/*
	 * author : 왕종휘
	 * purpose :리소스 추가
	 */
	@PostMapping("/security/insert")
	public ResponseEntity<String> insertResource(
			@RequestBody Map<String, Object> map){
		
		String resourceName = (String) map.get("resourceName");
		String httpMethod = (String) map.get("httpMethod");
		int orderNum = Integer.parseInt((String) map.get("orderNum"));
		String role = (String) map.get("role");
		
		service.insertResource(resourceName, httpMethod, orderNum, role);
		return new ResponseEntity<String>("insert success", HttpStatus.OK);
	}
	
	/*
	 * author : 왕종휘
	 * purpose : 회원 업데이트 삭제
	 */
	@PostMapping("/security/delete")
	public ResponseEntity<String> deleteResource(
			@RequestBody Map<String, Object> map){
		
		int id = Integer.parseInt((String) map.get("id"));
		
		service.deleteResource(id);
		return new ResponseEntity<String>("delete success", HttpStatus.OK);
	}
	
	/*
	 * author : 왕종휘
	 * purpose : 이벤트 수정
	 */
	@PostMapping("/event/update")
	public ResponseEntity<String> updateEvent(
			@RequestBody Map<String, Object> map){
		int no = Integer.parseInt((String) map.get("no"));
		String title = (String) map.get("title");
		String content = (String) map.get("content");
		String issueDate = (String) map.get("issueDate");
		String expireDate = (String) map.get("expireDate");
		int limitCount = Integer.parseInt((String) map.get("limitCount"));
//		String img = (String) map.get("img");
		int discount = Integer.parseInt((String) map.get("discount"));
		int status = Integer.parseInt((String) map.get("status"));
//		String detailImg = (String) map.get("detailImg");
//		String couponTitle = (String) map.get("couponTitle");
		
		
		return new ResponseEntity<String>("event update success",HttpStatus.OK);
	}

}
