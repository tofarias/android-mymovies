package com.example.tiago.mymovies.dao;

import com.example.tiago.mymovies.model.Movie;

import java.util.List;

public interface MovieDao {
    public List<Movie> listAll();
    public void insert(Movie movie);
    public void update(Movie movie);
    public Movie finById(String id);
}
