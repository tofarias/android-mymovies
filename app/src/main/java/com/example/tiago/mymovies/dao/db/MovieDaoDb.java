package com.example.tiago.mymovies.dao.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tiago.mymovies.dao.MovieDao;
import com.example.tiago.mymovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDaoDb implements MovieDao {

    private DbOpenHelper dbSqlite;

    public MovieDaoDb(Context context) {
        this.dbSqlite = new DbOpenHelper(context);
    }

    @Override
    public List<Movie> listar() {

        SQLiteDatabase db =  this.dbSqlite.getReadableDatabase();

        Cursor cursor = db.query("movie",
                new String[]{"id","title_en","title_pt_br"},
                null,null,null,null,"id");

        List<Movie> moviesList = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String titleEn = cursor.getString(cursor.getColumnIndex("title_en"));
            String titlePtBr = cursor.getString(cursor.getColumnIndex("title_pt_br"));

            Movie movie = new Movie(id,titleEn, titlePtBr);
            moviesList.add(movie);
        }
        return moviesList;
    }
}
