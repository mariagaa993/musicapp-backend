package com.musicapp.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "favorite")
public class FavoriteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer id_movie;
    private String title;
    private String image;
    private String language;
    private Double popularity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Override
    public String toString() {
        return "FavoriteModel{" +
                "id=" + id +
                ", id_movie=" + id_movie +
                '}';
    }
}
