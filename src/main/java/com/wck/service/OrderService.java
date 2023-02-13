package com.wck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.InsertOrderDTO;
import com.wck.domain.MemberGrade;
import com.wck.domain.OrderCriteria;
import com.wck.domain.OrderProductVO;
import com.wck.domain.OrderVO;
import com.wck.mapper.CartMapper;
import com.wck.mapper.EventMapper;
import com.wck.mapper.MemberMapper;
import com.wck.mapper.OrderMapper;
import com.wck.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderMapper orderMapper;
	private final ProductMapper productMapper;
	private final EventMapper eventMapper;
	private final MemberMapper memberMapper;
	private final CartMapper cartMapper;
	
	public List<OrderVO> getOrderList(OrderCriteria cri, String mid){
		return orderMapper.getOrderList(cri, mid);
	}
	
	public int getOrderCount(OrderCriteria cri, String mid) {
		return orderMapper.getOrderCount(cri, mid);
	}
	
	
	@Transactional
	public void insertOrder(InsertOrderDTO insertOrder) {
		OrderVO order = insertOrder.toOrderVO();

		orderMapper.insertPaymentMethod(order); 
		orderMapper.insertOrder(order);
		for(int i=0; i<order.getOrderProducts().size(); i++) {
			OrderProductVO prod = productMapper.getProductInfoWithColorName(order.getOrderProducts().get(i).getPsId()); 
			String psid = order.getOrderProducts().get(i).getPsId();
			int qty = order.getOrderProducts().get(i).getQuantity();
			orderMapper.insertOrderItem(psid, order.getOid(), qty, qty*prod.getPcPrice());
			// 장바구니 상품 제거
			cartMapper.deleteSpecificCart(order.getMid(), psid, qty);
			productMapper.updateProductStock(psid, qty);
		}
		
		String mId = order.getMid();
		
		// 쿠폰 사용으로 변경
		if(order.getCpid() != null || !order.getCpid().equals(""))
			eventMapper.useCoupon(order.getCpid(), mId);
		
		// 마일리지 변동(+추가 마일리지 - 사용 마일리지), 등급 업그레이드 확인 및 변경
		double rate = MemberGrade.of(memberMapper.getGradeById(mId)).getAccruRate();
		int addMileage = ((int) Math.floor(order.getObeforePrice()*rate)) - order.getOusedMileage();
		
		long totalOrderPrice = memberMapper.getTotalOrderPrice(mId);
		int grade = MemberGrade.of(totalOrderPrice);
		
		int nowMileage = memberMapper.getNowMileage(mId);
		memberMapper.updateMemberInfo(mId, nowMileage+addMileage, grade);
	}
	
	public OrderVO getOrderInfo(String mId, String oId) {
		return orderMapper.getOrderInfo(mId, oId);
	}
	
	public OrderVO getOrderInfo(String pmcode) {
		return orderMapper.getOrderInfoWithPMcode(pmcode);
	}
	
	@Transactional
	public int cancelOrder(String mId, OrderVO order) {
		// 적립된 마일리지 (이전 등급의 적립률이 적용됨)
		long totalOrderPrice = memberMapper.getTotalOrderPrice(mId);
		int beforeGrade = MemberGrade.of(totalOrderPrice - order.getObeforePrice());
		int expectSubMileage = (int) Math.floor(order.getObeforePrice() * MemberGrade.of(beforeGrade).getAccruRate());

		// 사용된 마일리지
		expectSubMileage += order.getOusedMileage();

		// 마일리지 변경 및 회원 등급 확인 및 변동
		int nowMileage = memberMapper.getNowMileage(mId);
		memberMapper.updateMemberInfo(mId, nowMileage - expectSubMileage, beforeGrade);
		
		if(order.getCpid() != null) {
			// 쿠폰 사용 -> 쿠폰 원복시키기
			eventMapper.cancelUseCoupon(order.getCpid());
		}

		// 상품 재고 업데이트
		for(int i=0; i<order.getOrderProducts().size(); i++) {
			String psid = order.getOrderProducts().get(i).getPsId();
			int qty =  -1 * order.getOrderProducts().get(i).getQuantity();
			productMapper.updateProductStock(psid, qty);
		}
		
		return orderMapper.cancelOrder(order.getOid());
	}

}
