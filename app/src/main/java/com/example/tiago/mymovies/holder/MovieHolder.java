package com.example.tiago.mymovies.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tiago.mymovies.R;

public class MovieHolder extends RecyclerView.ViewHolder{

    public TextView movieNameEn;
    public TextView movieNamePtBr;
    public TextView movieId;
    public TextView movieCategory;
    public TextView movieComment;

    public MovieHolder(@NonNull View itemView) {
        super(itemView);
        this.movieNameEn   = itemView.findViewById(R.id.movieNameEn);
        this.movieNamePtBr = itemView.findViewById(R.id.movieNamePtBr);
        this.movieId       = itemView.findViewById(R.id.movieId);
        this.movieCategory = itemView.findViewById(R.id.movieCategory);
        this.movieComment  = itemView.findViewById(R.id.movieComment);
    }
}
