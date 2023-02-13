package com.wck.security.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.wck.mapper.ResourceMapper;
import com.wck.security.domain.Resources;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class SecurityResourceService {

	private final ResourceMapper  resourceMapper;

	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {
		
		 LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
		 List<Resources> resourcesList = resourceMapper.findAllResources();
		 resourcesList.forEach(re -> {
			 List<ConfigAttribute> configAttributeList = new ArrayList<>();
			 String role = re.getRole();
			 configAttributeList.add(new SecurityConfig(role));
			 result.put(new AntPathRequestMatcher(re.getResourceName()), configAttributeList);
		 });
		 log.info("result : "+ result);
		return result;
	}
	
	
}
