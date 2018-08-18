package com.example.tiago.mymovies.model;

import java.util.List;

public class MovieCategory {

    private int id;
    private List<Movie> movieList;
    private List<Category> categoryLis;

    public MovieCategory(int id, List<Movie> movieList, List<Category> categoryLis) {
        this.id = id;
        this.movieList = movieList;
        this.categoryLis = categoryLis;
    }

    public MovieCategory(List<Movie> movieList, List<Category> categoryLis) {
        this.movieList = movieList;
        this.categoryLis = categoryLis;
    }

    public int getId() {
        return id;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Category> getCategoryLis() {
        return categoryLis;
    }

    public void setCategoryLis(List<Category> categoryLis) {
        this.categoryLis = categoryLis;
    }
}
