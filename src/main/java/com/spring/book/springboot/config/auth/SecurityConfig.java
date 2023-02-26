package com.spring.book.springboot.config.auth;

import com.spring.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 설정 활성화
@Configuration
// https://devlog-wjdrbs96.tistory.com/434 (WebSecurityConfigurerAdapter - deprecated)
// https://stackoverflow.com/questions/74609057/how-to-fix-spring-authorizerequests-is-deprecated (authorizeRequests - deprecated)
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;

  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .headers().frameOptions().disable()
        .and()
          .authorizeHttpRequests() // authorizeRequests -> authorizeHttpRequests 수정 (권한 관리 설정 시작점)
          .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // antMatchers -> requestMatchers 수정 (권한 관리 대상을 지정하는 옵션 - permitAll 옵션을 사용하여 전체 열람 권한 설정)
          .requestMatchers("/api/v1/**").hasRole(Role.USER.name()) // antMatchers -> requestMatchers 수정 (권한 관리 대상을 지정하는 옵션 - USER 권한을 가진 사람만 사용 가능)
          .anyRequest().authenticated() // 이외의 나머지 URL(anyRequest())들은 인증된 사용자들만 허용(authenticated())
        .and()
          .logout()
          .logoutSuccessUrl("/") // 로그아웃 성공 시 / 주소로 이동
        .and()
          .oauth2Login() // 로그인 성공 후 여러 설정들의 진입점
          .userInfoEndpoint() // 로그인 성공 이후 사용자 정보 설정 정보
          .userService(customOAuth2UserService); // 구현체에 등록

    return http.build();
  }
}
