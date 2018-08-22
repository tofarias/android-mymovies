package com.example.tiago.mymovies.dao;

import com.example.tiago.mymovies.model.MovieScore;

public interface MovieScoreDao {
    //public List<MovieScore> listAll();
    public MovieScore findByMovieId(String movieId);
    public long insert(MovieScore movieScore);
    public void update(MovieScore movieScore);
    public void delete(String id);
    //public Movie finById(String id);
}
