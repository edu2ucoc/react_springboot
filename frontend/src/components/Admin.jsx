import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

/**
 * 관리자페이지
 * @returns 
 */
function Admin() {
  const navigate = useNavigate();
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/admin', {
          withCredentials: true, // 자격 증명(쿠키, 인증 헤더 등)을 포함하여 HTTP 요청
        });
        if (response.status === 200) {
          setIsAdmin(true);
        }
      } catch (error) {
        console.error('Error checking admin status:', error);
      }
    };
    fetchData();
  }, []);

  const moveHomePage = async () => {
    navigate(-1);
  };

  return (
    <div>
      { isAdmin ? 
        <div>어드민 페이지입니다.</div> : 
        <div>권한이 없습니다.</div>
      }
      <button onClick={moveHomePage}>이전</button>
    </div>
  );
}

export default Admin;
