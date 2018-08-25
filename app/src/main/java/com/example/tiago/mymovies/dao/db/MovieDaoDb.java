package com.example.tiago.mymovies.dao.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tiago.mymovies.dao.MovieDao;
import com.example.tiago.mymovies.model.Category;
import com.example.tiago.mymovies.model.Movie;
import com.example.tiago.mymovies.model.WatchedWhere;

import java.util.ArrayList;
import java.util.List;

public class MovieDaoDb implements MovieDao {

    private DbOpenHelper dbSqlite;

    public MovieDaoDb(Context context) {
        this.dbSqlite = new DbOpenHelper(context);
    }

    @Override
    public long insert(Movie movie) {

        SQLiteDatabase db = this.dbSqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title_pt_br",movie.getTitlePtBr());
        values.put("title_en",movie.getTitleEn());
        values.put("category_id",movie.getCategory().getId());
        values.put("comment",movie.getComment());
        values.put("release_year",movie.getReleaseYear());
        values.put("watched_where_id",movie.getWatchedWhere().getId());

        long id = db.insert("movie",null,values);
        db.close();

        return id;
    }

    @Override
    public void update(Movie movie) {

        SQLiteDatabase db = this.dbSqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title_pt_br",movie.getTitlePtBr());
        values.put("title_en",movie.getTitleEn());
        values.put("category_id",movie.getCategory().getId());
        values.put("comment",movie.getComment());
        values.put("release_year",movie.getReleaseYear());
        values.put("watched_where_id",movie.getWatchedWhere().getId());

        db.update("movie",values,"id = ?",new String[]{ Integer.toString(movie.getId()) });
        db.close();
    }

    @Override
    public void delete(String id) {

        SQLiteDatabase db = this.dbSqlite.getWritableDatabase();

        db.delete("movie","id = ?", new String[]{ id });
        db.close();
    }

    public Movie finById(String id) {

        SQLiteDatabase db =  this.dbSqlite.getReadableDatabase();

        Cursor cursor = db.query("movie",
                new String[]{"id","title_pt_br", "title_en", "comment", "category_id", "release_year", "watched_where_id"},
                "id = ?",new String[]{ id },null,null,null);

        if( cursor != null ){
            cursor.moveToFirst();
        }

        Movie movie = new Movie(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("title_en")),
                cursor.getString(cursor.getColumnIndex("title_pt_br")),
                cursor.getString(cursor.getColumnIndex("comment")),
                new Category(cursor.getInt(cursor.getColumnIndex("category_id"))),
                cursor.getString(cursor.getColumnIndex("release_year")),
                new WatchedWhere(cursor.getInt(cursor.getColumnIndex("watched_where_id")))
            );

            return movie;
        }

        @Override
        public List<Movie> listAll() {

            SQLiteDatabase db =  this.dbSqlite.getReadableDatabase();

            String rawQuery = "SELECT movie.id, title_en, title_pt_br, category.name as category_category, category.id category_id, comment, release_year, watched_where_id, watched_where.name watched_where_name " +
                              "FROM movie " +
                                            "INNER JOIN category ON (category.id = movie.category_id)" +
                                            "INNER JOIN watched_where ON (watched_where.id = movie.watched_where_id)" +
                              "ORDER BY movie.id DESC";

            Cursor cursor = db.rawQuery( rawQuery, null );

            List<Movie> moviesList = new ArrayList<>();

            while(cursor.moveToNext()){
                int id           = cursor.getInt(cursor.getColumnIndex("id"));
                String titleEn   = cursor.getString(cursor.getColumnIndex("title_en"));
                String titlePtBr = cursor.getString(cursor.getColumnIndex("title_pt_br"));
                String categoryName  = cursor.getString(cursor.getColumnIndex("category_category"));
                int categoryId  = cursor.getInt(cursor.getColumnIndex("category_id"));
                String comment   = cursor.getString(cursor.getColumnIndex("comment"));
                String releaseYear   = cursor.getString(cursor.getColumnIndex("release_year"));
                int watchedWhereId   = cursor.getInt(cursor.getColumnIndex("watched_where_id"));
                String watchedWhereName   = cursor.getString(cursor.getColumnIndex("watched_where_name"));

                Category categoryModel = new Category(categoryId, categoryName);
                WatchedWhere watchedWhereModel = new WatchedWhere( watchedWhereId, watchedWhereName );

                Movie movie = new Movie(id, titleEn, titlePtBr, comment, new Category(categoryId, categoryName), releaseYear, watchedWhereModel);
                moviesList.add(movie);
            }
            return moviesList;
        }
    }
