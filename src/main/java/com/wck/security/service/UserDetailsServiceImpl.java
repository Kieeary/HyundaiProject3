package com.wck.security.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wck.mapper.MemberMapper;
import com.wck.security.domain.Account;
import com.wck.security.domain.UserDetail;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername ttt");
		Account account =  memberMapper.getAccountByUserId(username);
		if(account == null) {
			throw new UsernameNotFoundException("No user found");
		}
		account.getUserRoles().add("ROLE_USER");
		return new UserDetail(
				account, 
				account.getUserRoles().stream().collect(Collectors.toList()));
	}
	
	

}
