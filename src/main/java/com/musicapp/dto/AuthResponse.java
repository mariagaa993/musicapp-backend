package com.musicapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private Integer id;
    private String fullName;
    private String token;
}
