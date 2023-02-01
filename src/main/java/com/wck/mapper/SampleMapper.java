package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wck.domain.SampleVO;

@Mapper
public interface SampleMapper {
	public SampleVO getSample();

}
