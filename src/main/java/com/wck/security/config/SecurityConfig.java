package com.wck.security.config;


import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.AbstractSecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.wck.security.interceptor.UrlFilterInvocationSecurityMetadataSource;
import com.wck.security.provider.FormAuthenticationProvider;
import com.wck.security.service.SecurityResourceService;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private SecurityResourceService securityResourceService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
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
		log.info("??????");
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
		roleHierarchyImpl
		.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return roleHierarchyImpl;
	}
	
	
	@Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
    
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// ??????????????? ????????? security ?????? X
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// url ?????? ?????? ?????? ??????
			.authorizeHttpRequests()
//			.antMatchers("/wck/myapge/*").hasRole("USER")
//			.antMatchers("/wck/login").permitAll()
			.anyRequest().permitAll()
		.and()
			// form login ??????
			.formLogin()
			.loginPage("/wck/login")
			.failureHandler(authenticationFailureHandler)
			.defaultSuccessUrl("/wck/")
//			.permitAll()
			
		.and()
			.oauth2Login()
			.loginPage("/wck/login")
			.successHandler(authenticationSuccessHandler)
//			.permitAll()
			
		.and()
			.addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class)

			.rememberMe()
			.userDetailsService(userDetailsService)
			.tokenValiditySeconds(3600)
			.tokenRepository(tokenRepository())
		.and()
			.logout()
			.logoutUrl("/wck/logout")
			.logoutSuccessUrl("/wck/")
			.deleteCookies("JSESSIONID" , "remember-me")
		;
	}
//	@Bean
//	public FilterRegistrationBean fileterRegistrationBean() throws Exception{
//		FilterRegistrationBean fileterRegistrationBean = new FilterRegistrationBean<>();
//		fileterRegistrationBean.setFilter(customFilterSecurityInterceptor());
//		fileterRegistrationBean.setEnabled(false);
//		return fileterRegistrationBean;
//	}
//	
	@Bean
	public FilterSecurityInterceptor customFilterSecurityInterceptor() throws Exception {
		
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
		filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
		filterSecurityInterceptor.setAuthenticationManager(authenticationManagerBean());
		return filterSecurityInterceptor;
	}
	
	private AccessDecisionManager affirmativeBased() {
		AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecisionVoters());
		return affirmativeBased;
	}
	private List<AccessDecisionVoter<?>> getAccessDecisionVoters() {
		return Arrays.asList(new RoleVoter());
	}
	
	@Bean
	public FilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() throws Exception {
		return new UrlFilterInvocationSecurityMetadataSource(
				urlResourcesMapFacotryBean().getObject(),securityResourceService);
	}
	
	private UrlResourcesMapFactoryBean urlResourcesMapFacotryBean() {
		UrlResourcesMapFactoryBean urlResourcesMapFactoryBean = 
				new UrlResourcesMapFactoryBean(securityResourceService);
				
		return urlResourcesMapFactoryBean;
	}
	
	
	

	
	
	
	
	

}

