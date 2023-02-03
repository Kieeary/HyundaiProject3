package com.wck.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wck.domain.SampleVO;

@SpringBootTest
public class SampleMapperTest {
	
	@Autowired
	private SampleMapper sampleMapper;
	
	@Test
	public void getSample() {
		SampleVO sample = sampleMapper.getSample();
		Assertions.assertEquals(sample.getId(),"id");
		Assertions.assertEquals(sample.getName(),"name");
	}
}
