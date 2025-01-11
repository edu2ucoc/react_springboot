import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Home from './Home';
import Admin from './Admin';
import Login from './Login';
import Signup from './Signup';
import MyInfo from './MyInfo';

/**
 * 모든 페이지 종합
 * @returns 
 */
function Basic() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Home />} />
        <Route path="/admin" element={<Admin />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/myInfo" element={<MyInfo />} />        
        
      </Routes>
    </div>
  );
}

export default Basic;
