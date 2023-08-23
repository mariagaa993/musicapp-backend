package com.musicapp.services;

import com.musicapp.dto.Movie;
import com.musicapp.dto.Response;
import com.musicapp.models.FavoriteModel;
import com.musicapp.models.UserModel;
import com.musicapp.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    private FavoriteModel convertToModel(Movie movie) {
        FavoriteModel favoriteModel = new FavoriteModel();
        favoriteModel.setId_movie(movie.getId());
        favoriteModel.setTitle(movie.getTitle());
        favoriteModel.setImage(movie.getBackdrop_path());
        favoriteModel.setLanguage(movie.getOriginal_language());
        favoriteModel.setPopularity(movie.getPopularity());

        return favoriteModel;
    }

    private Movie convertToDTO(FavoriteModel favoriteModel) {
        Movie movie = new Movie();
        movie.setId(favoriteModel.getId_movie());
        movie.setTitle(favoriteModel.getTitle());
        movie.setBackdrop_path(favoriteModel.getImage());
        movie.setOriginal_language(favoriteModel.getLanguage());
        movie.setPopularity(favoriteModel.getPopularity());

        return movie;
    }

    private boolean isMovieAlreadyInFavorites(UserModel user, Integer movieId) {
        List<FavoriteModel> favoriteMovies = user.getFavoriteMovies();
        return favoriteMovies.stream()
                .anyMatch(favorite -> favorite.getId_movie().equals(movieId));
    }

    @Override
    public Response saveFavoriteMovie(UserModel user, Movie movie) {
        Response response = new Response();

        if (isMovieAlreadyInFavorites(user, movie.getId())) {
            response.setDescription("The movie is already in the favorites list");
            return response;
        }

        FavoriteModel favoriteModel = convertToModel(movie);
        favoriteModel.setUser(user);
        favoriteRepository.save(favoriteModel);
        response.setDescription("OK");

        return response;
    }

    @Override
    public List<Movie> getFavoriteMovies(UserModel user, Integer page, Integer size) {
        List<FavoriteModel> favoriteMovies = favoriteRepository.findByUserId(user.getId());
        int totalMovies = favoriteMovies.size();
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, totalMovies);

        List<FavoriteModel> favoriteMoviesOnPage = favoriteMovies.subList(startIndex, endIndex);

        return favoriteMoviesOnPage.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteFavoriteMovie(Integer id, UserModel user) {
        favoriteRepository.deleteByMovieIdAndUserId(id, user.getId());
    }

    @Override
    public Integer getTotalFavoriteMovies() {
        List<FavoriteModel> allFavoriteMovies = favoriteRepository.findAll();
        return allFavoriteMovies.size();
    }

    @Override
    public List<Movie> searchFavoriteMoviesByName(String name) {
        List<FavoriteModel> favoriteMovies = favoriteRepository.searchByNameContainingIgnoreCase(name);
        return favoriteMovies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
