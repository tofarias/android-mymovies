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
    public void insert(Movie movie) {

        SQLiteDatabase db = this.dbSqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title_pt_br",movie.getTitlePtBr());
        values.put("title_en",movie.getTitleEn());
        values.put("category_id",movie.getCategory().getId());
        values.put("comment",movie.getComment());

        db.insert("movie",null,values);
        db.close();
    }

    @Override
    public void update(Movie movie) {

        SQLiteDatabase db = this.dbSqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title_pt_br",movie.getTitlePtBr());
        values.put("title_en",movie.getTitleEn());
        values.put("category_id",movie.getCategory().getId());
        values.put("comment",movie.getComment());

        db.update("movie",values,"id = ?",new String[]{ Integer.toString(movie.getId()) });
        db.close();
    }

    public Movie finById(String id) {

        SQLiteDatabase db =  this.dbSqlite.getReadableDatabase();

        Cursor cursor = db.query("movie",
                new String[]{"id","title_pt_br", "title_en", "comment", "category_id"},
                "id = ?",new String[]{ id },null,null,null);

        if( cursor != null ){
            cursor.moveToFirst();
        }

        Movie movie = new Movie(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("title_en")),
                cursor.getString(cursor.getColumnIndex("title_pt_br")),
                new Category(cursor.getInt(cursor.getColumnIndex("category_id"))),
                cursor.getString(cursor.getColumnIndex("comment"))
            );

            return movie;
        }

        @Override
        public List<Movie> listAll() {

            SQLiteDatabase db =  this.dbSqlite.getReadableDatabase();

            String rawQuery = "SELECT movie.id, title_en, title_pt_br, category.name as category, category.id category_id, comment " +
                              "FROM movie INNER JOIN category ON (category.id = movie.category_id)" +
                              "ORDER BY movie.id DESC";

            Cursor cursor = db.rawQuery( rawQuery, null );

            List<Movie> moviesList = new ArrayList<>();

            while(cursor.moveToNext()){
                int id           = cursor.getInt(cursor.getColumnIndex("id"));
                String titleEn   = cursor.getString(cursor.getColumnIndex("title_en"));
                String titlePtBr = cursor.getString(cursor.getColumnIndex("title_pt_br"));
                String category  = cursor.getString(cursor.getColumnIndex("category"));
                String comment   = cursor.getString(cursor.getColumnIndex("comment"));

                Movie movie = new Movie(id, titleEn, titlePtBr, new Category(category), comment);
                moviesList.add(movie);
            }
            return moviesList;
        }
    }
