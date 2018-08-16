package com.example.tiago.mymovies.dao;

import com.example.tiago.mymovies.model.Movie;

import java.util.List;

public interface MovieDao {
    public List<Movie> listAll();
    public void save(Movie movie);
}
