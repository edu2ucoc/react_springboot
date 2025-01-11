import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

/**
 * 회원가입
 * @returns 
 */
function Signup() {
  const navigate = useNavigate();
  const [user, setUser] = useState({
    name: '',
    email: '',
    password: '',
  });

  const inputChangeHandler = (e) => {
    const { name, value } = e.target;
    // 전체 데이터를 카피하고, 변경된 부분만 대체 처리
    setUser({ ...user, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      console.log(user);
      await axios.post('http://localhost:8080/join', user);
      alert('회원가입 성공');
      // 홈으로 이동(로그인)
      navigate('/'); 
    } catch (error) {
      console.log('회원가입 오류: ' + error);
    }
  };

  return (
    <div>
      <fieldset>
        <h3>회원가입</h3>
        <form onSubmit={handleSubmit}>
          <input type="text" name="name" value={user.name} placeholder="이름" onChange={inputChangeHandler} />
          <br/><br/>
          <input type="password" name="password" value={user.password} placeholder="비밀번호" onChange={inputChangeHandler} />
          <br/><br/>
          <input type="text" name="email" value={user.email} placeholder="이메일" onChange={inputChangeHandler} />
          <br/><br/>
          <button type="submit">가입</button>
        </form>
      </fieldset>
    </div>
  );
}

export default Signup;
