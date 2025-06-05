package com.whs.dev2.dto;

import lombok.Getter;
import lombok.Setter;
import com.whs.dev2.entity.User;

@Getter
@Setter
public class UserRequestDto {
    private String username;
    private String password;
    private String email;
}