package com.musicapp.services;

import com.musicapp.dto.Movies;
import com.musicapp.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FavoriteRepository favoriteRepository;

    private final String API_KEY = "3aa8c3bf5b6497e59ec085c9cca3b9df";

    @Override
    public Movies getRecentMovies(Integer page) {
        String URL_RECENT_BASE = "https://api.themoviedb.org/3/trending/movie/day?page=" + page + "&api_key=" + API_KEY;

        ResponseEntity<Movies> response = restTemplate.getForEntity(URL_RECENT_BASE, Movies.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        return new Movies();
    }

    @Override
    public Movies getPopularMovies(Integer page) {
        String URL_POPULAR_BASE = "https://api.themoviedb.org/3/movie/popular?page=" + page + "&api_key=" + API_KEY;

        ResponseEntity<Movies> response = restTemplate.getForEntity(URL_POPULAR_BASE, Movies.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return new Movies();
    }

    @Override
    public Movies getTopRatedMovies(Integer page) {
        String URL_TOP_RATED_BASE = "https://api.themoviedb.org/3/movie/top_rated?page=1" + page + "&api_key=" + API_KEY;

        ResponseEntity<Movies> response = restTemplate.getForEntity(URL_TOP_RATED_BASE, Movies.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return new Movies();
    }
}
