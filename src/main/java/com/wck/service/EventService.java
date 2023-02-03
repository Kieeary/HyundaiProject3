package com.wck.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
}
