package com.musicapp.dto;

import lombok.Data;

@Data
public class UserRegister {
    private String fullName;
    private String email;
    private String password;
}
