package com.wck.service;

import static org.mockito.ArgumentMatchers.intThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wck.domain.InsertOrderDTO;
import com.wck.domain.OrderVO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void orderDTOInsertTest() {
		InsertOrderDTO orderDTO = new InsertOrderDTO();
		orderDTO.setBeforeprice(74000);
		orderDTO.setAfterprice(74000);
		orderDTO.setRcpt_name("김한울");
		orderDTO.setPmCode("5723-xxac-42e3-bbf5-67de");
		orderDTO.setPhone("01053559460");
		orderDTO.setZipcode("11305");
		orderDTO.setAddress1("성남시 수정구 수정로 119번길 6-1");
		orderDTO.setAddress2("8888");
		orderDTO.setMId("7c-41f8-8c6a-739159990a8d");
		orderDTO.setStatus("결제 완료");
		orderDTO.setPmCompany("credit");
		orderDTO.setPsids(new String[]{"FL2B7HCY005LMM_SB_FR"});
		orderDTO.setCounts(new int[] {7});
		
		orderService.insertOrder(orderDTO);
	}
	
	@Test
	public void getOrderInfoTesr() {
		String mId = "7c-41f8-8c6a-739159990a8d";
		String oId = "230212P1676129267";
		
		OrderVO order = orderService.getOrderInfo(mId, oId);
		log.info("{}", order);
	}
	
	@Test
	public void orderCancelTest() {
		String mId = "7c-41f8-8c6a-739159990a8d";
		String oId = "230212P1676129267";
		
		OrderVO order = orderService.getOrderInfo(mId, oId);
		
		int result = orderService.cancelOrder(mId, order);
		log.info("cancel result > "+result);
		Assertions.assertEquals(result, 1);
	}

}
