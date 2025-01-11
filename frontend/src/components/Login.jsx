import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

/**
 * 로그인 
 * @returns
 */
function Login() {
  const navigate          = useNavigate();
  const [email, setEmail] = useState("");
  const [pwd, setPwd]     = useState("");  

  const submitHandler = async e => {
    e.preventDefault();

    const formData = new FormData();
    formData.append('email', email);
    formData.append('password', pwd);

    const response = await axios({
        url: 'http://localhost:8080/loginCheck',
        method: 'POST',
        data: formData,
        // 중요
        // 쿠키나 HTTP 인증 정보를 함께 전송할 수 있도록 하는 설정
        // 요청을 보낼 때 쿠키나 인증 헤더(예: Authorization)와 같은 민감한 정보를 서버로 전달할 수 있다.
        withCredentials: true,
      });
      if (response.status === 200) {
        alert('success');
        console.log('결과 확인 : ' + response.data);
        // 로그인 결과를 담아서 홈 페이지 이동
        navigate('/home', { state: { userDetailData: response.data } });
      } else {
        alert('fail');
        console.log('결과 확인 : ' + response);
      }
  }
  const emailHandler  = e => {
    setEmail( e.target.value );
  }
  const pwdHandler    = e  => {
    setPwd( e.target.value );
  }
  return (
    <div>
      <fieldset>
        <h3>로그인</h3>
        <form onSubmit={submitHandler}>
          <input type="text" placeholder="email" value={email} onChange={emailHandler} />
          <br/><br/>
          <input type="password" placeholder="password" value={pwd} onChange={pwdHandler} />
          <br/><br/>
          <input type="submit" value="로그인"/>
        </form>
      </fieldset>
      <fieldset>
        <Link to="/signup">
          <button>회원가입</button>
        </Link>
      </fieldset>      
    </div>
  );
}

export default Login;
