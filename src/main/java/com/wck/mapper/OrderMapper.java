package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wck.domain.OrderCriteria;
import com.wck.domain.OrderVO;

@Mapper
public interface OrderMapper {
	
	List<OrderVO> getOrderList(@Param("cri") OrderCriteria cri, @Param("mId") String mId);
	

}
