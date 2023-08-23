package com.musicapp.services;

import com.musicapp.dto.AuthResponse;
import com.musicapp.dto.UserLogin;
import com.musicapp.dto.UserRegister;

public interface UserService {
    AuthResponse registerUser(UserRegister userRegister);
    AuthResponse loginUser(UserLogin userLogin);
}
