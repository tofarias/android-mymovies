package com.example.tiago.mymovies;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tiago.mymovies.Service.AudioBackgroundService;
import com.example.tiago.mymovies.adapter.MovieAdapter;
import com.example.tiago.mymovies.dao.MovieDao;
import com.example.tiago.mymovies.dao.db.MovieDaoDb;
import com.example.tiago.mymovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Movie> moviesList = new ArrayList<>();
    MovieAdapter movieAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieDao movieDao = new MovieDaoDb(this);

        this.recyclerView = (RecyclerView) findViewById(R.id.recMovies);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.movieAdapter = new MovieAdapter(this.moviesList);

        this.recyclerView.setAdapter(movieAdapter);
        this.recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        this.playBackgroundAudio();
    }

    public void openForm(View v){
        Intent it = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(it);
    }

    public void playBackgroundAudio(){

        Intent intent = new Intent(MainActivity.this , AudioBackgroundService.class);
        PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this , 0 , intent , PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if(alarmManager !=null) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + (5 * 1000), pendingIntent);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        MovieDao movieDao = new MovieDaoDb(this);
        this.moviesList = movieDao.listAll();

        this.movieAdapter.setMovieList( this.moviesList );
        this.movieAdapter.notifyDataSetChanged();
    }
}
