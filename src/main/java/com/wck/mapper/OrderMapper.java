package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wck.domain.OrderCriteria;
import com.wck.domain.OrderVO;

@Mapper
public interface OrderMapper {

	public List<OrderVO> getOrderList(@Param("cri") OrderCriteria cri, @Param("mId") String mId);
	public int getOrderCount(@Param("cri") OrderCriteria cri, @Param("mId") String mId);

	public int insertPaymentMethod(OrderVO order);
	public int insertOrder(OrderVO order);
	public int insertOrderItem(String psid, String oid, int qty, int totalPrice);
	public OrderVO getOrderInfo(String oId);
	public OrderVO getOrderInfoWithPMcode(String pmcode);
	public int cancelOrder(String oId);
	public String getOId(String merchant_uid);
	public int deleteFailOrder(String oid);
	public int deleteFailPaymentMethod(String merchant_uid);
}
