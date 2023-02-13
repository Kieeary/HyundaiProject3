package com.wck.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wck.mapper.ResourceMapper;
import com.wck.security.service.SecurityResourceService;

@Configuration
public class AppConfig {
	
	@Bean
	public SecurityResourceService securityResourceService (ResourceMapper resourceMapper) {
		SecurityResourceService securityResourceService = new SecurityResourceService(resourceMapper);
		return securityResourceService;
	}

}
