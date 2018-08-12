package com.example.tiago.mymovies.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "db_mymovies";
    private static int DATABASE_VERSION = 1;
    private static String MOVIE_TABLE = "movie";

    private static String sqlCreateMovieTable = "CREATE TABLE "+ DbOpenHelper.MOVIE_TABLE+
                                                "(" +
                                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                "title_en VARCHAR(50)," +
                                                "title_pt_br VARCHAR(50)" +
                                                ")";

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DbOpenHelper.sqlCreateMovieTable);

        // CARGA INICIAL PARA TESTES
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br') VALUES ('Lethal Weapon', 'Máquina Mortifera')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br') VALUES ('Die Hard', 'Máquina Mortifera')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br') VALUES ('The Blair Witch Project', 'A Bruxa de Blair')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br') VALUES ('Horrible Bosses', 'Quero Matar Meu Chefe')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if( oldVersion < newVersion ){

            db.execSQL("DROP TABLE IF EXISTS movie");
            db.execSQL(DbOpenHelper.sqlCreateMovieTable);
        }
    }
}
