package com.example.tiago.mymovies.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tiago.mymovies.EditActivity;
import com.example.tiago.mymovies.R;

public class MovieHolder extends RecyclerView.ViewHolder{

    public TextView movieNameEn;
    public TextView movieNamePtBr;
    public TextView movieId;
    public TextView movieCategory;
    public TextView movieComment;
    public TextView movieReleaseYear;

    public MovieHolder(@NonNull final View itemView) {
        super(itemView);

        this.movieNameEn   = itemView.findViewById(R.id.movieNameEn);
        this.movieNamePtBr = itemView.findViewById(R.id.movieNamePtBr);
        this.movieId       = itemView.findViewById(R.id.movieId);
        this.movieCategory = itemView.findViewById(R.id.movieCategory);
        this.movieComment  = itemView.findViewById(R.id.movieComment);
        this.movieReleaseYear = itemView.findViewById(R.id.movieReleaseYear);

        itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent editIntent = new Intent(view.getContext(), EditActivity.class);
                editIntent.putExtra("movie_id", movieId.getText());

                Activity currentActivity = (Activity) view.getContext();
                currentActivity.startActivity(editIntent);
            }
        });
    }
}
