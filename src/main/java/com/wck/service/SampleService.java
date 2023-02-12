package com.wck.service;

import org.springframework.stereotype.Service;

import com.wck.domain.SampleVO;
import com.wck.mapper.SampleMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleService {

	private final SampleMapper sampleMapper;
	
	public SampleVO getSample() {
		return sampleMapper.getSample();
	}
	
}
