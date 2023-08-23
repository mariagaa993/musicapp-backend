package com.musicapp.services;

import com.musicapp.dto.*;
import com.musicapp.models.UserModel;
import com.musicapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse registerUser(UserRegister userRegister) {
        UserModel userModel = new UserModel();
        userModel.setFullName(userRegister.getFullName());
        userModel.setUsername(userRegister.getEmail());
        userModel.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        userModel.setRole(Role.USER);

        userRepository.save(userModel);

        return AuthResponse.builder()
                .id(userModel.getId())
                .fullName(userModel.getFullName())
                .token(jwtService.getToken(userModel))
                .build();
    }

    @Override
    public AuthResponse loginUser(UserLogin userLogin) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
        UserModel name = userRepository.findByUsername(userLogin.getEmail()).orElseThrow();
        UserDetails user = userRepository.findByUsername(userLogin.getEmail()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .id(name.getId())
                .fullName(name.getFullName())
                .token(token)
                .build();
    }
}
