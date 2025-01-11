package com.study.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN") // /admin/** 접근은 ADMIN 권한을 가진 사용자만 가능
                    .antMatchers("/user/**").authenticated() // /user/** 접근은 인증된 사용자만 가능
                    .anyRequest().permitAll()
                .and()
                .formLogin() // 로그인 설정
                    .loginPage("/login")
                    // 로그인 처리 엔드 포인트(URL만 설정)로 지정, 실처리는  CustomUserDetailsService 에서 처리
                    .loginProcessingUrl("/loginCheck")
                        .usernameParameter("email")         // form에서 email을 전달하여 로그인 처리, 비번도 표기해도 되나 통상 password 사용
                    // 로그인 성공하면 응답처리용
                    .defaultSuccessUrl("/loginSuccess")
                .and()
                .logout() // 로그아웃 설정
                    // 로그아웃 처리를 위한 엔드포인트만 지정, 시큐리티 내부에서 처리됨
                    .logoutUrl("/logout")
                    // 로그아웃 성공하면 이동
                    .logoutSuccessUrl("/logoutSuccess")
                    // 세션 식별자(Session Identifier)
                    // 서버는 JSESSIONID라는 고유 식별자를 발급하여 클라이언트(보통 웹 브라우저)에 쿠키로 전달
                    // 로그아웃 처리나 세션 무효화 시에 사용
                    .deleteCookies("JSESSIONID");
        // 필터 체인을 적용하여 요청이 들어오기 전에 CORS(크로스 도메인 처리)
        http.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    // CORS는 웹 페이지가 자신이 호스트되지 않은 도메인에서 리소스를 요청할 수 있도록 허용하는 메커니즘
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 자격 증명(예: 쿠키, 인증 헤더)을 포함한 요청을 허용 -> 세션 쿠키를 받겠다
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");// 프런트단 리액트 서버, 필요시 포트 변경
        // 모든 헤더값 통과
        config.addAllowedHeader("*");
        // 모든 메소드 방식 통과
        config.addAllowedMethod("*");
        // CORS 설정을 URL 패턴에 매핑하는 역할을 하는 객체
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 모든 URL에 대해 CORS를 적용
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
