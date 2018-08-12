package com.example.tiago.mymovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tiago.mymovies.adapter.MovieAdapter;
import com.example.tiago.mymovies.dao.MovieDao;
import com.example.tiago.mymovies.dao.db.MovieDaoDb;
import com.example.tiago.mymovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Movie> moviesList = new ArrayList<>();
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        MovieDao movieDao = new MovieDaoDb(this);
        this.moviesList = movieDao.listar();

        MovieAdapter adapter = new MovieAdapter(this.moviesList);
        recyclerView.setAdapter(adapter);
    }
}
