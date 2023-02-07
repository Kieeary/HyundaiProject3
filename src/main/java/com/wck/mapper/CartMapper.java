package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.CartVO;

@Mapper
public interface CartMapper {
	public int insertCart(String mId, String pSId, int pQuantity);
	public int countCart(String mId, String pSId);
	public List<CartVO> readCart(String mId);
	public void updateCart(String mId, String pSId, int pQuantity);
	public int deleteCart(String mId, String pSId);
	
}
