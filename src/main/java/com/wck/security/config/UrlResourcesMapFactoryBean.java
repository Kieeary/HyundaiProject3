package com.wck.security.config;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.wck.security.service.SecurityResourceService;

public class UrlResourcesMapFactoryBean  
	implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>>{

	private SecurityResourceService securitytResourceService;
	private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap;
	
	public UrlResourcesMapFactoryBean(SecurityResourceService securitytResourceService) {
		this.securitytResourceService = securitytResourceService;
	}

	@Override
	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
		if(resourceMap == null) {
			init();
		}
		return resourceMap;
	}

	private void init() {
		resourceMap = securitytResourceService.getResourceList();
	}

	@Override
	public Class<?> getObjectType() {
		return LinkedHashMap.class;
	}
	
	@Override
	public boolean isSingleton() {
		return true;
	}

	
}
