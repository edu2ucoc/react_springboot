package com.study.back.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.study.back.repository.UserRepository;
import com.study.back.entity.User;

/**
 * 스프링 시큐리티를 통해 로그인 시도시 처리되는 서비스
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("로그인 시도한 사용자 정보 : " + email);

        // 회원 아니면 오류
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.info("회원 아님 : " + email);
            throw new UsernameNotFoundException("회원 아님: " + email);
        }

        // 회원이면 UserDetails 객체 생성후 반환
        log.info("회원임 : " + email);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
    }

}
