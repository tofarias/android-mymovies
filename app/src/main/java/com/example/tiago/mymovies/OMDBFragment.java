package com.example.tiago.mymovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiago.mymovies.OMDBApi.Movie;

import java.io.Serializable;

public class OMDBFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.omdb_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Serializable movie = bundle.getSerializable("movie");

            //TextView txtReleased = (TextView) findViewById(R.id.txtReleased);

        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {

            Serializable movie = bundle.getSerializable("movie");

            TextView txtReleased = (TextView) view.findViewById(R.id.txtReleased);
            TextView txtLanguage = (TextView) view.findViewById(R.id.txtLanguage);
            TextView txtVotes = (TextView) view.findViewById(R.id.txtVotes);
            TextView txtRating = (TextView) view.findViewById(R.id.txtRating);
            TextView txtRunTime = (TextView) view.findViewById(R.id.txtRunTime);
            TextView txtAwards = (TextView) view.findViewById(R.id.txtAwards);

            txtReleased.setText( ((Movie) movie).Released );
            txtLanguage.setText( ((Movie) movie).Language );
            txtAwards.setText( ((Movie) movie).Awards );
            txtRunTime.setText( ((Movie) movie).RunTime );
            txtRating.setText( ((Movie) movie).imdbRating );
            txtVotes.setText( ((Movie) movie).imdbVotes );
        }


        TextView txtReleased = (TextView) view.findViewById(R.id.txtReleased);
    }

}
