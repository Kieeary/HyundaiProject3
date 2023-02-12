package com.wck.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.OrderVO;

@Mapper
public interface OrderMapper {
	public int insertPaymentMethod(OrderVO order);
	public int insertOrder(OrderVO order);
	public int insertOrderItem(String psid, String oid, int qty, int totalPrice);
	public OrderVO getOrderInfo(String mId, String oId);
	public int cancelOrder(String oId);
}
