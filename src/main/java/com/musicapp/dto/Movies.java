package com.musicapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class Movies {
    private List<Movie> results;
    private Integer total_pages;
}
