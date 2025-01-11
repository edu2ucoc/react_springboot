import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
/**
 * 마이페이지
 * @returns 
 */
function MyInfo() {
  const navigate = useNavigate();
  const [user, setUser] = useState({
    userName: '',
    userEmail: '',
    role: '',
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/user', {
          withCredentials: true, // 자격 증명(쿠키, 인증 헤더 등)을 포함하여 HTTP 요청
        });
        if (response.status === 200) {
          setUser(response.data);
        }
      } catch (error) {
        console.error('Error checking user status:', error);
      }
    };
    fetchData();
  }, []);

  const preMoveHander = async () => {
    // // 홈으로 가기 위해서는 이메일과 권한 정보를 별도로 전달해 줘야 해서(컨셉) 세팅후 이동
    // navigate('/home', { 
    //   state: { 
    //     userDetailData: {
    //       email:user.email,
    //       authorities:user.role
    //     } 
    //   } 
    // });
    navigate(-1);
  };

  return (
    <div>
      <h3>마이페이지</h3>
      <table border="1">
        <thead>
          <tr>
            <th>id</th>
            <th>name</th>
            <th>email</th>
            <th>role</th>
            <th>regdate</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{user.id}</td>
            <td>{user.name}</td>
            <td>{user.email}</td>
            <td>{user.role}</td>
            <td>{user.regdate}</td>
          </tr>
        </tbody>
      </table>
      <br/><br/>
      <button onClick={preMoveHander}>이전</button>
    </div>
  );
}

export default MyInfo;
