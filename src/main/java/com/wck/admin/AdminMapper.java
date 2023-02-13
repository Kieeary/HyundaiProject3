package com.wck.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.Criteria;
import com.wck.domain.MemberVO;
import com.wck.domain.ProductColorVO;
import com.wck.domain.ProductCommonVO;
import com.wck.domain.ProductStockVO;

@Mapper
public interface AdminMapper {

	public List<MemberVO> getMemberList();

	public int updateMember(String id, Integer grade, Boolean enabled, String role);
	
	public List<ProductCommonVO> getProductCommonList(Criteria cri);

	public int getProductCommonCount();

	public int updateProductCommon(String id, String name, String note, Boolean status, String bno);

	public List<ProductColorVO> getProductColorList(String pid);

	public List<ProductStockVO> getProductStockList(String pcid);
}
