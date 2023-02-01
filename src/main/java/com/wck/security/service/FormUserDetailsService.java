package com.wck.security.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wck.domain.MemberVO;
import com.wck.mapper.MemberMapper;
import com.wck.security.domain.Account;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class FormUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("--loadUserByUsername----");
		log.info(username);
		MemberVO member = null;
		try {
			member = memberMapper.findOneByEmail(username, "Email");
		}catch(SQLException e) {
			log.info(e.getMessage());
		}
		
        if(member == null) {
        	try {
        		member = memberMapper.findOneByEmail(username, "Google");
    		} catch (SQLException e1) {
    			throw new UsernameNotFoundException("Check Email or Social!!");
    		}
        }
        log.info("-------");
        log.info(member);
        log.info(member.getRole());
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getRole()));
        
        Account account = new Account(member.getId(), member.getEmail(), member.getPassword(), member.getName(), member.getGender(), member.getLoginType(), authorities);
        
        log.info(account);
		
		return account;
	}
	

}
