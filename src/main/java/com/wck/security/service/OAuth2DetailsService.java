package com.wck.security.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.wck.domain.MemberVO;
import com.wck.mapper.MemberMapper;
import com.wck.security.domain.Account;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OAuth2DetailsService extends DefaultOAuth2UserService {

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("-------oauth2 loaduser-------------");

		String clientName = userRequest.getClientRegistration().getClientName();

		log.info("clientName :", clientName);

		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("======oAuth2User===============");
		oAuth2User.getAttributes().forEach((k, v) -> {
			log.info(k + " : " + v);
		});// end foreach

		String email = null;
		if (clientName.equals("Google")) {// 구글 인증 확인
			email = oAuth2User.getAttribute("email");
		} // end if
		log.info("구글 인증 확인");
		log.info("email : " + email);

		try {
			MemberVO member = saveOAuth2Member(oAuth2User.getAttributes());
			log.info("---saveSocialMember--");
			log.info(member);

			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(member.getRole()));

			Account account = new Account(member.getId(), member.getEmail(), member.getPassword(), member.getName(),
					member.getGender(), member.getLoginType(), authorities, oAuth2User.getAttributes());
			return account;
		} catch (SQLException e) {
			log.info(e.getMessage());
			return null;
		}
	}

	private MemberVO saveOAuth2Member(Map<String, Object> attr) throws SQLException {
		String email = attr.get("email").toString();
		String id = UUID.randomUUID().toString().substring(10);
		String name = "user" + id;
		String phone = "01000000000";
		Date birth = new Date(new java.util.Date().getTime());
		int gender = 1;
		String loginType = "Google";
		int tosNo = 1;
		boolean enabled = true;
		String role = "ROLE_USER";
		int grade = 1;
		int mileage = 0;
		log.info("saveOAuth2Member 시작");
		MemberVO member = memberMapper.findOneByEmail(email, "Google");

		// 기존 member가 있다면 리턴
		if (!(member == null)) {
			log.info("기존 회원");
			return member;
		}

		// 기존 member가 없다면 저장 후 리턴
		member = new MemberVO();
		member.setEmail(email);
		member.setId(id);
		member.setPhone(phone);
		member.setPassword(passwordEncoder.encode("1111"));
		member.setName(name);
		member.setBirth(birth);
		member.setGender(gender);
		member.setLoginType(loginType);
		member.setTosNo(tosNo);
		member.setEnabled(enabled);
		member.setRole(role);
		member.setGrade(grade);
		member.setMileage(mileage);

		memberMapper.insertOne(member);
		return member;
	}

}
