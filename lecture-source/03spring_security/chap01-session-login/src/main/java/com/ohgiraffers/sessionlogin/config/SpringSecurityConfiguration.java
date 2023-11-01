package com.ohgiraffers.sessionlogin.config;

import com.ohgiraffers.sessionlogin.member.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/* 시큐리티 설정 활성화 및 bean 등록 기능 */
@EnableWebSecurity //이거 꼭 쓰기
public class SpringSecurityConfiguration {

    private final AuthenticationService authenticationService;

    public SpringSecurityConfiguration(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /* 비밀번호 암호화에 사용할 객체 BCryptPasswordEncoder bean 등록 */
    @Bean //쓸거 빈등록 꼭 하고 쓰기
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* Http 요청에 대한 설정을 SecurityFilterChain에 설정 */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                /* CSRF 공격 방지는 기본적으로 활성화 되어 있어 비활성화 처리 */
                .csrf()
                .disable()
                /* 요청에 대한 권한 체크 */
                .authorizeHttpRequests()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll() // 이러한 요청이 오면 모두 허가하겠다.
                /* hasRole 에 전달하는 값은 "ROLE_"가 자동으로 앞에 붙는다. */
                .antMatchers("/order/**","/member/mypage").hasRole("MEMBER")
                .antMatchers(HttpMethod.POST, "/menu/**").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                /* 위에 서술 된 패턴 외의 요청은 인증 되지 않은 사용자도 요청 허가 */
                .anyRequest().permitAll()
                .and()
                /* 로그인 설정 */
                .formLogin()
                .loginPage("/member/login")
                .successForwardUrl("/")
                .failureForwardUrl("/error/login")
                .and()
                /* 로그 아웃 설정 */
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .and()
                /* 인증/인가 예외 처리 : 인증이 필요하면 로그인 페이지로 이동하므로 인가 처리만 설정 */
                .exceptionHandling()
                .accessDeniedPage("/error/denied")
                .and()
                .build();
    }

    /* 사용자 인증을 위해서 사용할 Service 등록 & 비밀번호 인코딩 방식 지정 */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {

        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authenticationService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }


}