package com.wck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wck.domain.EventCouponVO;
import com.wck.domain.EventCriteria;
import com.wck.domain.EventVO;
import com.wck.mapper.EventMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

	private final EventMapper eventMapper;
	
	public List<EventVO> getEventList(EventCriteria cri){
		return eventMapper.getEventList(cri);
	}
	
	public EventVO getEvent(int eventId){
		return eventMapper.getEvent(eventId);
	}
	
	public List<EventCouponVO> getCouponListByMid(String mid, int cpStatus){
		return eventMapper.getCouponListByMid(mid, cpStatus);
	}

	public boolean isJoined(int eventId, String mId) {
		return eventMapper.isJoined(eventId, mId);
	}

	@Transactional
	public boolean issueCoupon(int eventId, String mId) {
		boolean isJoined = isJoined(eventId, mId);
		if(isJoined) return false;
		int row = eventMapper.insertCouponDetail(eventId,mId);

		return row == 1;
	}
	
}
