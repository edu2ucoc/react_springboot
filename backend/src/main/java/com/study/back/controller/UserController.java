package com.study.back.controller;

import com.study.back.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.study.back.entity.User;
import com.study.back.service.UserService;

import java.util.Map;
import java.util.HashMap;

import lombok.RequiredArgsConstructor;

// 롬복 로그
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입, 회원 정보는 JSON으로 전달된다
     * @param user
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody UserDto user) {
        log.info("회원가입을 위해 전달된 데이터" + user.toString());
        userService.joinUser(user);
        return ResponseEntity.ok().build();
    }

    /**
     * 로그인 성공하면 스프링시큐리티에 의해서 이 주소로 포워딩됨
     * @return
     */
    @GetMapping("/loginSuccess")
    public ResponseEntity<Map<String, String>> loginOk() {
        // 현재 세션에서 인증정보 획득
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 이름과 권한 획득
        String email = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        // 확인
        log.info("로그인한 유저 이메일:" + email);
        log.info("유저 권한:" + authentication.getAuthorities());
        // 해당 정보 세팅 (json 형식 구성 => 키, 값)
        // 필요시 데이터베이스 연동해서 정보를 더 추가할수 있다
        // createUserInfo 커스텀 처리 필요
        Map<String, String> userInfo = createUserInfo(email, authorities);
        // 응답 -> 리액트에서 받음
        return ResponseEntity.ok(userInfo);
    }

    /**
     * 로그아웃 성공하면 단순 응답으로 처리
     * @return
     */
    @GetMapping("/logoutSuccess")
    public ResponseEntity<Void> logoutOk() {
        log.info("로그아웃 성공");
        return ResponseEntity.ok().build();
    }

    /**
     * 어드민 진입시 체크
     * @return
     */
    @GetMapping("/admin")
    public ResponseEntity<Void> getAdminPage() {
        log.info("어드민 인증 성공");
        return ResponseEntity.ok().build();
    }

    /**
     * 개인정보 확인
     * @return
     */
    @GetMapping("/user")
    public ResponseEntity<UserDto> getUserPage() {
        log.info("인증이 있어서 요청이 넘어옴 ");
        // 시큐리티에서 읽어서, 해당 정보 유저 반환
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto user = userService.getUserInfo(email);
        log.info(user.toString());
        return ResponseEntity.ok(user);
    }

    /**
     * 사용자 세션 정보에서 추출하여 JSON 구성을 위해 자료구조 처리
     * @param email
     * @param authorities
     * @return
     */
    private Map<String, String> createUserInfo(String email, String authorities) {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("email", email);
        userInfo.put("authorities", authorities);
        return userInfo;
    }
}
