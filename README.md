# react_springboot
## frontend
    - 리액트 구성
    - 라우터 처리
    - 회원가입, 로그인, 홈페이지, 개인페이지, 관리자페이지
    - 로그인 성공후 개발자모드 application 쿠키쪽에 세션값이 생성됨
## backend
    - 스프링시큐리티
    - CORS는 필터 체인으로 구성
    - login, logout은 엔드포인트로 처리
    - 실제 로그인은 CustomUserDetailsService 에서 처리
    - 롤 부여 (아이디로 구분)
    - 데이터베이스 mysql, 디비명 webdb, 접속계정 root/1234