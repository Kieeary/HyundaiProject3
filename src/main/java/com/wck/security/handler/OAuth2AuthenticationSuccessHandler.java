package com.wck.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.wck.security.domain.Account;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class OAuth2AuthenticationSuccessHandler 
	extends SimpleUrlAuthenticationSuccessHandler{

	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("=========Oauth2 Login Success Handler==========");
		Account account =  (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info(account.getEmail()+" login");
		if(account.getGender() == -1) {
			redirectStrategy.sendRedirect(request, response, "/wck/login/change");
			return;
		}
		
		setDefaultTargetUrl("/wck");
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest != null)
			redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
		else 
			redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
		
	}
	
	

}
