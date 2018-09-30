package com.example.tiago.mymovies.dao.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tiago.mymovies.dao.MovieScoreDao;
import com.example.tiago.mymovies.model.Movie;
import com.example.tiago.mymovies.model.MovieScore;

public class MovieScoreDaoDb implements MovieScoreDao {

    private DbOpenHelper dbSqlite;

    public MovieScoreDaoDb(Context context) {
        this.dbSqlite = new DbOpenHelper(context);
    }

    @Override
    public long insert(MovieScore movieScore) {

        SQLiteDatabase db = this.dbSqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("movie_id",movieScore.getMovie().getId());
        values.put("actors_score",movieScore.getActorsScore());
        values.put("duration_score",movieScore.getDurationScore());
        values.put("story_score",movieScore.getStoryScore());
        values.put("final_story_score",movieScore.getFinalStoryScore());
        values.put("music_score",movieScore.getMusicScore());

        long id = db.insert("movie_score",null,values);
        db.close();

        return id;
    }

    public MovieScore findByMovieId(String movieId) {

        SQLiteDatabase db =  this.dbSqlite.getReadableDatabase();

        Cursor cursor = db.query("movie_score",
                new String[]{"id","movie_id", "actors_score", "duration_score", "story_score", "final_story_score", "music_score"},
                "movie_id = ?",new String[]{ movieId },null,null,null);

        if( cursor != null ){
            cursor.moveToFirst();
        }

        MovieScore movieScore = new MovieScore(
                cursor.getInt(cursor.getColumnIndex("id")),
                new Movie(cursor.getInt(cursor.getColumnIndex("movie_id"))),
                cursor.getFloat(cursor.getColumnIndex("actors_score")),
                cursor.getFloat(cursor.getColumnIndex("music_score")),
                cursor.getFloat(cursor.getColumnIndex("duration_score")),
                cursor.getFloat(cursor.getColumnIndex("final_story_score")),
                cursor.getFloat(cursor.getColumnIndex("story_score"))
        );

        return movieScore;
    }

    @Override
    public void update(MovieScore movieScore) {

        SQLiteDatabase db = this.dbSqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("movie_id",movieScore.getMovie().getId());
        values.put("actors_score",movieScore.getActorsScore());
        values.put("duration_score",movieScore.getDurationScore());
        values.put("story_score",movieScore.getStoryScore());
        values.put("final_story_score",movieScore.getFinalStoryScore());
        values.put("music_score",movieScore.getMusicScore());

        db.update("movie_score",values,"movie_id = ?",new String[]{ Integer.toString(movieScore.getMovie().getId()) });
        db.close();
    }

    @Override
    public void delete(String movieId) {

        SQLiteDatabase db = this.dbSqlite.getWritableDatabase();

        db.delete("movie_score","movie_id = ?", new String[]{ movieId });
        db.close();
    }
}
