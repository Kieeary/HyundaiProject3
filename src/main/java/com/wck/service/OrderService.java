package com.wck.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.InsertOrderDTO;
import com.wck.domain.MemberGrade;
import com.wck.domain.OrderProductVO;
import com.wck.domain.OrderVO;
import com.wck.mapper.CartMapper;
import com.wck.mapper.EventMapper;
import com.wck.mapper.MemberMapper;
import com.wck.mapper.OrderMapper;
import com.wck.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderService {
	private final OrderMapper orderMapper;
	private final ProductMapper productMapper;
	private final EventMapper eventMapper;
	private final MemberMapper memberMapper;
	private final CartMapper cartMapper;
	
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
		if(!order.getCpid().equals(""))
			eventMapper.useCoupon(order.getCpid(), mId);
		
		// 마일리지 변동(+추가 마일리지 - 사용 마일리지), 등급 업그레이
		double rate = MemberGrade.of(memberMapper.getGradeById(mId)).getAccruRate();
		int addMileage = ((int) Math.floor(order.getObeforePrice()*rate)) - order.getOusedMileage();
		
		long totalOrderPrice = memberMapper.getTotalOrderPrice(mId);
		int grade = MemberGrade.of(totalOrderPrice);
		
		int nowMileage = memberMapper.getNowMileage(mId);
		memberMapper.updateMemberInfo(mId, nowMileage+addMileage, grade);
	}

}
