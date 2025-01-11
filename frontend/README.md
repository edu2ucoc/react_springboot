# 설치 및 구동
- cd frontend
- npm install
    - package.json에서
    ```
        # (참고)아래 2개는 쿠키 및 세션 처리를 위한 모듈 
        "js-cookie": "^3.0.5",
        "react-cookie": "^7.1.4",
    ```

- npm start

# 통신
- axios

# 중요 구조
```
├── package.json        : 패키지 관리
├── public
│   ├── favicon.ico
│   ├── index.html
│   ├── logo192.png
│   ├── logo512.png
│   ├── manifest.json
│   └── robots.txt
└── src
    ├── App.css
    ├── App.js          : 컴포넌트 메인
    ├── App.test.js
    ├── components
    │   ├── Admin.jsx   : 관리자만 접근 가능, 로그인 이후 진입
    │   ├── Basic.jsx   : 전체 라우팅 구도및 등록
    │   ├── Home.jsx    : 로그인 이후 홈페이지
    │   ├── Login.jsx   : 로그인, 로그인 없이 접근가능
    │   ├── MyInfo.jsx  : 로그인 이후 개인 정보 확인
    │   └── Signup.jsx  : 회원가입, 로그인 없이 접근가능
    ├── index.css
    ├── index.js        : 라우팅 큰 틀 적용
    ├── logo.svg
    ├── reportWebVitals.js
    └── setupTests.js
```
