package com.musicapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MoviesResponse {
    private List<Movie> results;
    private Integer totalPages;
}
