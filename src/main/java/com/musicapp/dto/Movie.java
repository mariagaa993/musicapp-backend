package com.musicapp.dto;

import lombok.Data;

@Data
public class Movie {
    private Integer id;
    private String title;
    private String backdrop_path;
    private String original_language;
    private Double popularity;
}
