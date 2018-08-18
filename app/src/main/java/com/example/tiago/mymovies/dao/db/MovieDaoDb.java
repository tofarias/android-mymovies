package com.example.tiago.mymovies.dao.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tiago.mymovies.dao.MovieDao;
import com.example.tiago.mymovies.model.Category;
import com.example.tiago.mymovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDaoDb implements MovieDao {

    private DbOpenHelper dbSqlite;

    public MovieDaoDb(Context context) {
        this.dbSqlite = new DbOpenHelper(context);
    }

    @Override
    public void save(Movie movie) {

        SQLiteDatabase db = this.dbSqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title_pt_br",movie.getTitlePtBr());
        values.put("title_en",movie.getTitleEn());

        db.insert("movie",null,values);
        db.close();
    }

    @Override
    public List<Movie> listAll() {

        SQLiteDatabase db =  this.dbSqlite.getReadableDatabase();

        /*Cursor cursor = db.query("movie",
                new String[]{"id","title_en","title_pt_br"},
                null,null,null,null,"id");*/

        String rawQuery = "SELECT movie.id, title_en, title_pt_br, category.name as category " +
                          "FROM movie INNER JOIN movie_category ON (movie_category.movie_id = movie.id) " +
                                     "INNER JOIN category ON (category.id = movie_category.id)";

        Cursor cursor = db.rawQuery( rawQuery, null );

        List<Movie> moviesList = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String titleEn = cursor.getString(cursor.getColumnIndex("title_en"));
            String titlePtBr = cursor.getString(cursor.getColumnIndex("title_pt_br"));
            String movieCategory = cursor.getString(cursor.getColumnIndex("category"));

            List<Category> categoryList = new ArrayList<Category>();
            categoryList.add( new Category(movieCategory) );

            Movie movie = new Movie(id, titleEn, titlePtBr, categoryList);
            moviesList.add(movie);
        }
        return moviesList;
    }
}
