package com.wck.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.EventCouponVO;
import com.wck.domain.EventCriteria;
import com.wck.domain.EventVO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class EventMapperTest {
	
	@Autowired
	private EventMapper eventMapper;
	
	@Test
	void getEventList() {
		EventCriteria cri = new EventCriteria();
		cri.setCurrentPage(1);
		cri.setPast(false);
		cri.setPageSize(1);
		List<EventVO> list = eventMapper.getEventList(cri);
		log.info(list.size());
	}
	
	@Test
	void getEvent() {
		EventVO event = eventMapper.getEvent(123121);
		log.info(event);
		Assertions.assertNotNull(event);
	}
	
	@Test
	void getUsableCoupon() {
		String mid = "123123";
		List<EventCouponVO> list = eventMapper.getCouponListByMid(mid, 1);
		log.info(list);
		Assertions.assertEquals(list.size(), 1);
	}
	
	@Test
	void isJoined() {
		String mid = "123123";
		int eno = 123121;
		boolean result = eventMapper.isJoined(eno, mid);
		Assertions.assertEquals(result, true);
	}
	
	@Test
	@Transactional
	void insertCouponDetail() {
		int eno = 123122;
		String mid = "123123";
		int row = eventMapper.insertCouponDetail(eno, mid);
		Assertions.assertEquals(row, 1);
	}

}
