package com.wck.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wck.domain.Criteria;
import com.wck.domain.MemberVO;
import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductCommonVO;
import com.wck.domain.ProductStockVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminMapper mapper;
	
	List<MemberVO> getMemberList(){
		return mapper.getMemberList();
	}

	public void updateMember(String id, Integer grade, Boolean enabled, String role) {
		int row = mapper.updateMember(id, grade, enabled, role);
		if(row == 0) throw new RuntimeException("updateMember Error");
	}
	
	public List<ProductCommonVO> getProductCommonList(Criteria cri){
		return mapper.getProductCommonList(cri);
	}
	
	public int getProductCommonCount() {
		return mapper.getProductCommonCount();
	}

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
	}

	public void insertResource(String resourceName, String httpMethod, int orderNum, String role) {
		mapper.insertResource(resourceName,httpMethod, orderNum, role);
	}
	
	public void deleteResource(int id) {
		mapper.deleteResource(id);
	}

}
