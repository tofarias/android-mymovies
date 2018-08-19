package com.example.tiago.mymovies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiago.mymovies.R;
import com.example.tiago.mymovies.holder.MovieHolder;
import com.example.tiago.mymovies.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {

    public List<Movie> movieList;

    public MovieAdapter(List<Movie> m){
        this.movieList = m;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.movie_item,parent,false );

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        String movieNameEn = this.movieList.get(position).getTitleEn();
        String movieNamePtBr = this.movieList.get(position).getTitlePtBr();
        int movieId = this.movieList.get(position).getId();
        String movieCategory = this.movieList.get(position).getCategory().getName();
        String movieComment = this.movieList.get(position).getComment();

        holder.movieNameEn.setText(movieNameEn);
        holder.movieNamePtBr.setText(movieNamePtBr);
        holder.movieId.setText(String.valueOf(movieId));
        holder.movieCategory.setText(movieCategory);
        holder.movieComment.setText(movieComment);
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
