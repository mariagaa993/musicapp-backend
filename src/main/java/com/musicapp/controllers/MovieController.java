package com.musicapp.controllers;

import com.musicapp.dto.Movies;
import com.musicapp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/movies")
public class MovieController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MovieService movieService;

    @GetMapping("recent")
    public ResponseEntity<Movies> getAllRecentMovies(@RequestParam(name = "page") Integer page) {
        return new ResponseEntity<>(movieService.getRecentMovies(page), HttpStatus.OK);
    }

    @GetMapping("popular")
    public ResponseEntity<Movies> getAllPopularMovies(@RequestParam(name = "page") Integer page) {
        return new ResponseEntity<>(movieService.getPopularMovies(page), HttpStatus.OK);
    }

    @GetMapping("topRated")
    public ResponseEntity<Movies> getAllTopRatedMovies(@RequestParam(name = "page") Integer page) {
        return new ResponseEntity<>(movieService.getTopRatedMovies(page), HttpStatus.OK);
    }
}
