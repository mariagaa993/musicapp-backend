package com.musicapp.services;

import com.musicapp.dto.Movie;
import com.musicapp.dto.Response;
import com.musicapp.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoriteService {
    Response saveFavoriteMovie(UserModel user, Movie movie);
    List<Movie> getFavoriteMovies(UserModel user, Integer page, Integer size);
    void deleteFavoriteMovie(Integer id, UserModel user);
    Integer getTotalFavoriteMovies();
    List<Movie> searchFavoriteMoviesByName(String name);
}
