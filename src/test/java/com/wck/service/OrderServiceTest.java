package com.wck.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wck.domain.InsertOrderDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void orderDTOInsertTest() {
		InsertOrderDTO orderDTO = new InsertOrderDTO();
		orderDTO.setBeforeprice(574000);
		orderDTO.setAfterprice(383977);
		orderDTO.setRcpt_name("김한울");
		orderDTO.setPmCode("4418-7b0c-42e3-bbf5-67de");
		orderDTO.setPhone("01053559460");
		orderDTO.setZipcode("11305");
		orderDTO.setAddress1("성남시 수정구 수정로 119번길 6-1");
		orderDTO.setAddress2("8888");
		orderDTO.setGl_radio_dlvr_msg("배송 전 연락 바랍니다");
		orderDTO.setFrontDoorSelectMessage("자유 출입 가능");
		orderDTO.setVoucherCode("testCoupon");
		orderDTO.setUseMileage(17823);
		orderDTO.setMId("7c-41f8-8c6a-739159990a8d");
		orderDTO.setStatus("결제 완료");
		orderDTO.setPmCompany("credit");
		orderDTO.setPsids(new String[]{"SY2C9WSS957W_KK_61", "FL2B7HCY005LMM_SB_FR"});
		orderDTO.setCounts(new int[] {2, 7});
		
		orderService.insertOrder(orderDTO);
	}

}
