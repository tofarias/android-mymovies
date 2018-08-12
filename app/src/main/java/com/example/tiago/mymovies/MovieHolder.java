package com.example.tiago.mymovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MovieHolder extends RecyclerView.ViewHolder{

    public TextView movieNameEn;
    public TextView movieNamePtBr;
    public TextView movieId;
    public String movie;

    public MovieHolder(@NonNull View itemView) {
        super(itemView);
        this.movieNameEn = itemView.findViewById(R.id.movieName);
        this.movieNamePtBr = itemView.findViewById(R.id.movieNamePtBr);
        this.movieId   = itemView.findViewById(R.id.movieId);
    }
}
