package com.musicapp.repositories;

import com.musicapp.models.FavoriteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteModel, Integer> {
    @Modifying
    @Query("DELETE FROM FavoriteModel f WHERE f.id_movie = :id AND f.user.id = :userId")
    void deleteByMovieIdAndUserId(@Param("id") Integer id, @Param("userId") Integer userId);

    @Query("SELECT f FROM FavoriteModel f WHERE LOWER(f.title) LIKE %:name%")
    List<FavoriteModel> searchByNameContainingIgnoreCase(String name);

    List<FavoriteModel> findByUserId(Integer user);
}
