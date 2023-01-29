package com.wck.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
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


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		
			// url 경로 접근 권한 체크
			.authorizeHttpRequests()
			.anyRequest().permitAll()
			
		.and()
		
			// form login 방식
			.formLogin()
//			.loginPage("/login")
			;
		
	}
	

	
	
	
	
	

}
