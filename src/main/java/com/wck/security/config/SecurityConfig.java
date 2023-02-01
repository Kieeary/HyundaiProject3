package com.wck.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 정적파일에 대해선 security 적용 X
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	@Bean
	public RoleHierarchyImpl roleHierarchyImpl() {
		log.info("실행");
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
		roleHierarchyImpl
		.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return roleHierarchyImpl;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// url 경로 접근 권한 체크
			.authorizeHttpRequests()
			.antMatchers("/user").hasRole("USER")
			.anyRequest().permitAll()
		.and()
			// form login 방식
			.formLogin()
			.loginPage("/wck/login")
			.loginProcessingUrl("/wck/login")
			.permitAll()
		.and()
			.oauth2Login()
			.loginPage("/wck/login")
			.loginProcessingUrl("/wck/login")
			
		.and()
			.csrf()
			.disable()
			
		.logout()
		.logoutSuccessUrl("/")
		
		;
		
	}
	

	
	
	
	
	

}
