import React from 'react';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

/**
 * 홈 페이지
 * @returns 
 */
function Home() {
  const navigate = useNavigate();
  const location = useLocation();
  const { email, authorities } = location.state.userDetailData;

  const handleLogout = async () => {
    try {
      // 세션 종료를 위한 처리 -> 응답이 오면 쿠키가 삭제됨(세션 삭제)
      await axios.post('/logout');
      alert('success');
      // 로그인을 위한 페이지로 이동
      navigate('/');
    } catch (error) {
      console.error('로그아웃 에러:', error);
    }
  };

  const moveUserPage = () => {
    navigate('/myInfo');
  };

  const moveAdminPage = () => {
    navigate('/admin');
  };

  return (
    <div>
      <fieldset>
        <h3>사용자 정보</h3>
        <p>이메일: {email}</p>
        <p>권한: {authorities}</p>
      </fieldset>
      <fieldset>
        <button onClick={handleLogout}>로그아웃</button>
        <br/><br/>        
        <button onClick={moveUserPage}>마이 페이지</button>
        <br/><br/>
        <button onClick={moveAdminPage}>어드민 페이지</button>
        <br/>
      </fieldset>
    </div>
  );
}

export default Home;
