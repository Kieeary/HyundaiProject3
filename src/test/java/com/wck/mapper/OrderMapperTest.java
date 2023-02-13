package com.wck.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.OrderCriteria;
import com.wck.domain.OrderProductVO;
import com.wck.domain.OrderVO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class OrderMapperTest {
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ProductMapper productMapper;

	@Test
	public void paymentInsertTest() {
		OrderVO order = new OrderVO();
		order.setPmCode("4418-7b0c-42e3-bbf5-67de");
		order.setPmCompany("kakakopay");
		if(order.getPmCompany().equals("credit")) {
        	order.setPmMethod("0");
        } else {
        	order.setPmMethod("1");
        }
		
		int result = orderMapper.insertPaymentMethod(order);
		log.info(result==1 ? "Payment Insert OK" : "Payment Insert ERROR!");

	}

	@Test
	public void orderInsertTest() {
		OrderVO order = new OrderVO();
		order.setOid("232211P1676119017");
		order.setObeforePrice(574000);
		order.setOafterPrice(383977);
		order.setOreceiver("김한울");
		order.setPmCode("4418-7b0c-42e3-bbf5-67de");
		order.setOphone("01053559460");
		order.setOzipcode("11305");
		order.setOaddress1("성남시 수정구 수정로 119번길 6-1");
		order.setOaddress2("8888");
		order.setOmemo("배송 전 연락 바랍니다");
		order.setCpid("testCoupon");
		order.setOusedMileage(17823);
		order.setMid("7c-41f8-8c6a-739159990a8d");
		order.setOstatus("결제 완료");

		int result = orderMapper.insertOrder(order);
		log.info(result==1 ? "Order Insert OK" : "Order Insert ERROR!");
	}
	
	@Test
	@Transactional
	public void orderItemsInsertTest() {
		String oid = "232211P1676119017";
		String[] psids = {"SY2C9WSS957W_KK_61", "FL2B7HCY005LMM_SB_FR"};
		int[] qtys = {2, 1};
		int result = 0;
		for(int i=0; i<psids.length; i++) {
			OrderProductVO prod = productMapper.getProductInfoWithColorName(psids[i]);
			result += orderMapper.insertOrderItem(psids[i], oid, qtys[i], qtys[i]*prod.getPcPrice());
		}
		log.info(result==psids.length ? "Order Item Insert All OK" : "Order Item Insert ERROR!");
	}
	
	@Test
	public void getOrderInfoTest() {
		String mId = "7c-41f8-8c6a-739159990a8d";
		String oId = "230212P1676129267";
		
		OrderVO order = orderMapper.getOrderInfo(oId);
		log.info("{}", order);
	}
	
	@Test
	public void getOrderList() {
		OrderCriteria cri = new OrderCriteria();
		cri.setCurrentPage(1);
		cri.setPageSize(5);
		cri.setMonth(1);
		List<OrderVO> list = orderMapper.getOrderList(cri, "123123");
		log.info(list);
	}
	
	@Test
	public void getOrderCount() {
		OrderCriteria cri = new OrderCriteria();
		cri.setCurrentPage(1);
		cri.setPageSize(5);
		cri.setMonth(1);
		int count = orderMapper.getOrderCount(cri, "7c-41f8-8c6a-739159990a8d");
		log.info(count);
	}

}
