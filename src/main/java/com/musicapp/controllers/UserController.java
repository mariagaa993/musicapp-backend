package com.musicapp.controllers;

import com.musicapp.dto.AuthResponse;
import com.musicapp.dto.UserLogin;
import com.musicapp.dto.UserRegister;
import com.musicapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRegister userRegister) {
        return ResponseEntity.ok(userService.registerUser(userRegister));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLogin userLogin) {
        return ResponseEntity.ok(userService.loginUser(userLogin));
    }
}
