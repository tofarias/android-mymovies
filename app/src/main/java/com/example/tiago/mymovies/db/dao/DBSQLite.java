package com.example.tiago.mymovies.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSQLite extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "db_mymovies";
    private static int DATABASE_VERSION = 1;

    private static String sqlCreateMovieTable = "CREATE TABLE movie " +
                                                "(" +
                                                "id INTEGER PRIMARY KEY," +
                                                "title_en VARCHAR(50)," +
                                                "title_pt_br VARCHAR(50)," +
                                                ")";

    public DBSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DBSQLite.sqlCreateMovieTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if( oldVersion < newVersion ){

            db.execSQL("DROP TABLE IF EXISTS movie");
            db.execSQL(DBSQLite.sqlCreateMovieTable);
        }
    }
}
