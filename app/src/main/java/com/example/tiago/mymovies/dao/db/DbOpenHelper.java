package com.example.tiago.mymovies.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "db_myfavmovies";
    private static int DATABASE_VERSION = 1;
    private static String MOVIE_TABLE = "movie";
    private static String CATEGORY_TABLE = "category";
    private static String MOVIE_SCORE_TABLE = "movie_score";
    private static String WATCHED_WHERE_TABLE = "watched_where";

    private static String sqlCreateMovieScoreTable = "CREATE TABLE "+ DbOpenHelper.MOVIE_SCORE_TABLE+
                                                    "(" +
                                                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    "movie_id int NOT NULL UNIQUE,"  +
                                                    "actors_score FLOAT NOT NULL,"  +
                                                    "duration_score FLOAT NOT NULL,"  +
                                                    "story_score FLOAT NOT NULL,"  +
                                                    "final_story_score FLOAT NOT NULL,"  +
                                                    "music_score FLOAT NOT NULL,"  +
                                                    "FOREIGN KEY(movie_id) REFERENCES movie(id)"+
                                                    ")";

    private static String sqlCreateMovieTable = "CREATE TABLE "+ DbOpenHelper.MOVIE_TABLE+
                                                "(" +
                                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                "category_id int NOT NULL,"  +
                                                "watched_where_id int NOT NULL,"  +
                                                "title_en VARCHAR(50) UNIQUE," +
                                                "title_pt_br VARCHAR(50) UNIQUE," +
                                                "comment VARCHAR(100)," +
                                                "release_year CHAR(4) NOT NULL,"  +
                                                "FOREIGN KEY(category_id) REFERENCES category(id),"+
                                                "FOREIGN KEY(watched_where_id) REFERENCES watched_where(id)"+
                                                ")";

    private static String sqlCreateCategoryTable = "CREATE TABLE "+ DbOpenHelper.CATEGORY_TABLE+
                                                    "(" +
                                                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    "name VARCHAR(50) NOT NULL UNIQUE" +
                                                    ")";

    private static String sqlCreateWatchedWhereTable = "CREATE TABLE "+ DbOpenHelper.WATCHED_WHERE_TABLE+
                                                    "(" +
                                                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    "name VARCHAR(50) NOT NULL UNIQUE" +
                                                    ")";

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DbOpenHelper.sqlCreateWatchedWhereTable);
        db.execSQL(DbOpenHelper.sqlCreateCategoryTable);
        db.execSQL(DbOpenHelper.sqlCreateMovieTable);
        db.execSQL(DbOpenHelper.sqlCreateMovieScoreTable);

        // CARGA INICIAL PARA TESTES

        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Ação')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Comédia')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Documentário')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Terror')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Ficção Científica')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Drama')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Seriado')");

        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id') VALUES ('Lethal Weapon 1', 'Máquina Mortifera 1', 6,'...', '1987', 5)");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id') VALUES ('Die Hard 1', 'Duro de Matar 1', 5,'...', '1988', 4)");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id') VALUES ('The Blair Witch Project', 'A Bruxa de Blair', 4,'...', '1999', 3)");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id') VALUES ('Horrible Bosses 1', 'Quero Matar Meu Chefe 1', 3,'...', '2011', 2)");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id') VALUES ('Braveheart', 'Coracao Valente', 2,'abc...', '1995', 2)");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id') VALUES ('The Green Mile', 'A espera de um milagre', 1,'...', '1999', 1)");

        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(6, 3, 5.5, 5, 2, 2.5)" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(5, 4, 4.5, 4, 3, 3.5)" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(4, 5.5, 2.5, 3.5, 3.5, 2)" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(3.5, 3, 2.5, 4, 4, 1.5)" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(2, 2.5, 3, 2.5, 5, 1)" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(1.5, 4, 5, 1.5, 3.5, 2)" );

        db.execSQL("INSERT INTO "+ DbOpenHelper.WATCHED_WHERE_TABLE +" ('name') VALUES('TV aberta')" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.WATCHED_WHERE_TABLE +" ('name') VALUES('Cinema')" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.WATCHED_WHERE_TABLE +" ('name') VALUES('Netflix')" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.WATCHED_WHERE_TABLE +" ('name') VALUES('Alugou DVD')" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.WATCHED_WHERE_TABLE +" ('name') VALUES('Download')" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if( oldVersion < newVersion ){

            db.execSQL("DROP TABLE IF EXISTS "+DbOpenHelper.CATEGORY_TABLE);
            db.execSQL(DbOpenHelper.sqlCreateCategoryTable);

            db.execSQL("DROP TABLE IF EXISTS "+DbOpenHelper.MOVIE_TABLE);
            db.execSQL(DbOpenHelper.sqlCreateMovieTable);

            db.execSQL("DROP TABLE IF EXISTS "+DbOpenHelper.MOVIE_SCORE_TABLE);
            db.execSQL(DbOpenHelper.sqlCreateMovieScoreTable);

            db.execSQL("DROP TABLE IF EXISTS "+DbOpenHelper.WATCHED_WHERE_TABLE);
            db.execSQL(DbOpenHelper.sqlCreateWatchedWhereTable);
        }
    }
}
