package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.EventCouponVO;
import com.wck.domain.EventCriteria;
import com.wck.domain.EventVO;

@Mapper
public interface EventMapper {

	public List<EventVO> getEventList(EventCriteria cri);

	public EventVO getEvent(int eventId);
	
	public List<EventCouponVO> getCouponListByMid(String mid, int cpStatus);

	public boolean isJoined(int eventId, String mId);

	public int insertCouponDetail(int eno, String mId);
}
