package com.wck.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wck.domain.Criteria;
import com.wck.domain.EventVO;
import com.wck.domain.MemberVO;
import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductCommonVO;
import com.wck.domain.ProductStockVO;
import com.wck.security.interceptor.UrlFilterInvocationSecurityMetadataSource;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminMapper mapper;
	
	@Autowired
	private UrlFilterInvocationSecurityMetadataSource securityMetadataSource;
	
	/*
	 * author : 왕종휘
	 * purpose : 회원 조회 수행
	 */
	List<MemberVO> getMemberList(){
		return mapper.getMemberList();
	}

	/*
	 * author : 왕종휘
	 * purpose : 회원 수정 수행
	 */
	public void updateMember(String id, Integer grade, Boolean enabled, String role) {
		int row = mapper.updateMember(id, grade, enabled, role);
		if(row == 0) throw new RuntimeException("updateMember Error");
	}
	
	/*
	 * author : 왕종휘
	 * purpose : 대분류 상품 조회 페이지
	 */
	public List<ProductCommonVO> getProductCommonList(Criteria cri){
		return mapper.getProductCommonList(cri);
	}
	
	/*
	 * author : 왕종휘
	 * purpose : 대분류 상품 건수 조회
	 */
	public int getProductCommonCount() {
		return mapper.getProductCommonCount();
	}

	/*
	 * author : 왕종휘
	 * purpose : 대분류 상품 수정 수행
	 */
	public void updateProductCommon(String id, String name, String note, Boolean status, String bno) {
		int row = mapper.updateProductCommon(id,name,note,status,bno);
		if(row == 0) throw new RuntimeException("updateProductCommon Error");
		
	}

	public List<ProductColorVO> getProductColorList(String pid) {
		List<ProductColorVO> list = mapper.getProductColorList(pid);
		return list;
	}

	public List<ProductStockVO> getProductStockList(String pcid) {
		List<ProductStockVO> list = mapper.getProductStockList(pcid);
		return list;
	}
	
	public void updateResource(int id, String resourceName, String httpMethod, int orderNum, String role) {
		mapper.updateResource(id, resourceName,httpMethod, orderNum, role);
		securityMetadataSource.reload();
	}

	public void insertResource(String resourceName, String httpMethod, int orderNum, String role) {
		mapper.insertResource(resourceName,httpMethod, orderNum, role);
		securityMetadataSource.reload();
	}
	
	public void deleteResource(int id) {
		mapper.deleteResource(id);
		securityMetadataSource.reload();
	}

	public List<EventVO> getEventList() {
		List<EventVO> eventList = mapper.getEvnetList();
		return eventList;
	}

}
