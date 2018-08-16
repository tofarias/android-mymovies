package com.example.tiago.mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiago.mymovies.dao.MovieDao;
import com.example.tiago.mymovies.dao.db.MovieDaoDb;
import com.example.tiago.mymovies.model.Movie;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void addMovie(View view){

        EditText edtTitlePtBr = (EditText) findViewById(R.id.edtTitlePtBr);
        EditText edtTitleEn   = (EditText) findViewById(R.id.edtTitleEn);

        String titlePtBr = edtTitlePtBr.getText().toString().trim();
        String titleEn   = edtTitleEn.getText().toString().trim();

        Movie movie = new Movie(titleEn, titlePtBr);

        MovieDao movieDao = new MovieDaoDb(this);
        movieDao.save(movie);

        Toast.makeText(this,"Cadastro realizado com sucesso!",Toast.LENGTH_SHORT).show();

        Intent mainActivityIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(mainActivityIntent);
    }

    public void cancelRegistration(View view){
        finish();
    }
}
