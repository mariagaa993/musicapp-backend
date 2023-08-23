package com.musicapp.services;

import com.musicapp.dto.Movies;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    Movies getRecentMovies(Integer page);
    Movies getPopularMovies(Integer page);
    Movies getTopRatedMovies(Integer page);
}
