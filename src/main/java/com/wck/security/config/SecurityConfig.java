package com.wck.security.config;


import javax.validation.constraints.AssertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.wck.security.provider.FormAuthenticationProvider;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
    	return new FormAuthenticationProvider();
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
	public void configure(WebSecurity web) throws Exception {
		// 정적파일에 대해선 security 적용 X
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
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
			.failureHandler(authenticationFailureHandler)
			.defaultSuccessUrl("/wck/")
			
		.and()
			.oauth2Login()
			.loginPage("/wck/login")
			.defaultSuccessUrl("/wck/")
			
		.and()
			.csrf()
			.disable()
			
		.logout()
		.logoutUrl("/wck/logout")
		.logoutSuccessUrl("/wck/")
		.deleteCookies("JSESSIONID" , "remember-me")
		;
		
		

	
		
	}
	

	
	
	
	
	

}

