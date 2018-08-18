package com.example.tiago.mymovies.dao;

import com.example.tiago.mymovies.model.Category;
import com.example.tiago.mymovies.model.Movie;

import java.util.List;

public interface CategoryDao {
    public List<Category> listAll();
}
