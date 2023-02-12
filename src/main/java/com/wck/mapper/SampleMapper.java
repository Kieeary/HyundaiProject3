package com.wck.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.SampleVO;

@Mapper
public interface SampleMapper {
	public SampleVO getSample();

}
