package com.wck;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

@Configuration
public class AppConfig extends DefaultOAuth2UserService {
   @Bean
   PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}