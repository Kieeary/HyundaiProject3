package com.wck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wck.security.domain.Resources;


@Mapper
public interface ResourceMapper {

	public  List<Resources> findAllResources();

}
