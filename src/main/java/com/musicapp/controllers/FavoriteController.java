package com.musicapp.controllers;

import com.musicapp.dto.Movie;
import com.musicapp.dto.MoviesResponse;
import com.musicapp.dto.Response;
import com.musicapp.models.UserModel;
import com.musicapp.services.FavoriteService;
import com.musicapp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/favoriteMovies")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private MovieService movieService;

    @PostMapping("save")
    public ResponseEntity<Response> saveFavorite(@RequestBody Movie movie) {
        UserModel currentUser = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<Response>(favoriteService.saveFavoriteMovie(currentUser, movie), HttpStatus.CREATED);
    }

    @GetMapping("list")
    public ResponseEntity<MoviesResponse> getAllFavoriteMovies(@RequestParam(name = "page") Integer page, @RequestParam(defaultValue = "20") Integer size) {
        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Movie> favoriteMovies = favoriteService.getFavoriteMovies(user, page, size);
        Integer totalMovies = favoriteService.getTotalFavoriteMovies();
        Integer totalPages = (int) Math.ceil((double) totalMovies / size);

        MoviesResponse response = new MoviesResponse(favoriteMovies, totalPages);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public List<Movie> searchMoviesByName(@RequestParam(name = "name") String name) {
        return favoriteService.searchFavoriteMoviesByName(name);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteFavorite(@PathVariable(name = "id") Integer id) {
        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        favoriteService.deleteFavoriteMovie(id, user);
        return new ResponseEntity<>("Removed successfully!", HttpStatus.OK);
    }
}
