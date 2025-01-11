package com.study.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.back.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
