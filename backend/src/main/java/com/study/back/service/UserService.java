package com.study.back.service;

import com.study.back.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.back.entity.User;
import com.study.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinUser(UserDto user) {
        String encPassword = bCryptPasswordEncoder.encode(user.getPassword());
        log.info("비밀번호 인코딩:" + encPassword);
        user.setPassword(encPassword);
        if(user.getName().equals("admin") )
            user.setRole("ROLE_ADMIN");
        else
            user.setRole("ROLE_USER");
        userRepository.save(user.toEntity());
    }

    public UserDto getUserInfo(String email) {
        User user = userRepository.findByEmail(email);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .role(user.getRole())
                .regdate(user.getRegdate())
                .build();
    }

}
