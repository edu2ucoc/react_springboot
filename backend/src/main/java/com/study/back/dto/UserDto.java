package com.study.back.dto;

import com.study.back.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import java.sql.Timestamp;

@Data
@ToString
public class UserDto {
    private int id;
    private String name;
    private String password;
    private String email;
    private String role;
    private Timestamp regdate;
    @Builder
    public UserDto(int id, String name, String password, String email, String role, Timestamp regdate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.regdate = regdate;
    }
    public User toEntity()
    {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .password(this.password)
                .email(this.email)
                .role(this.role)
                .regdate(this.regdate)
                .build();
    }
}
