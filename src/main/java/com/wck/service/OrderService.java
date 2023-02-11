package com.wck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.InsertOrderDTO;
import com.wck.domain.OrderProductVO;
import com.wck.domain.OrderVO;
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
	
	@Transactional
	public void insertOrder(InsertOrderDTO insertOrder) {
		OrderVO order = insertOrder.toOrderVO();
		// payment data insert
		orderMapper.insertPaymentMethod(order); 
		
		// order data insert
		orderMapper.insertOrder(order);
		
		// order item data insert
		for(int i=0; i<order.getOrderProducts().size(); i++) {
			OrderProductVO prod = productMapper.getProductInfoWithColorName(order.getOrderProducts().get(i).getPsId()); 
			String psid = order.getOrderProducts().get(i).getPsId();
			int qty = order.getOrderProducts().get(i).getQuantity();
			orderMapper.insertOrderItem(psid, order.getOid(), qty, qty*prod.getPcPrice());			
		}
		
		// insert 성공 후 쿼리
		
	}

}
