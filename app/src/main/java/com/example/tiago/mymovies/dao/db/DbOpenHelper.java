package com.example.tiago.mymovies.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "db_mymovies";
    private static int DATABASE_VERSION = 1;
    private static String MOVIE_TABLE = "movie";
    private static String CATEGORY_TABLE = "category";
    private static String MOVIE_CATEGORY_TABLE = "movie_category";

    private static String sqlCreateMovieTable = "CREATE TABLE "+ DbOpenHelper.MOVIE_TABLE+
                                                "(" +
                                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                "title_en VARCHAR(50)," +
                                                "title_pt_br VARCHAR(50)," +
                                                "comment VARCHAR(50)" +
                                                ")";

    private static String sqlCreateCategoryTable = "CREATE TABLE "+ DbOpenHelper.CATEGORY_TABLE+
                                                    "(" +
                                                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    "name VARCHAR(50) NOT NULL" +
                                                    ")";

    private static String sqlCreateMovieCategoryTable = "CREATE TABLE "+ DbOpenHelper.MOVIE_CATEGORY_TABLE+
            "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "movie_id VARCHAR(50) NOT NULL," +
            "category_id VARCHAR(50) NOT NULL" +
            ")";

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DbOpenHelper.sqlCreateCategoryTable);
        db.execSQL(DbOpenHelper.sqlCreateMovieTable);
        db.execSQL(DbOpenHelper.sqlCreateMovieCategoryTable);

        // CARGA INICIAL PARA TESTES
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'comment') VALUES ('Lethal Weapon', 'Máquina Mortifera', 'abc...')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'comment') VALUES ('Die Hard', 'Duro de Matar', 'abc...')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'comment') VALUES ('The Blair Witch Project', 'A Bruxa de Blair', 'abc...')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'comment') VALUES ('Horrible Bosses', 'Quero Matar Meu Chefe', 'abc...')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'comment') VALUES ('Braveheart', 'Coracao Valente', 'abc...')");

        // INSERINDO DADOS PARA A TABELA CATEGORY
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('acao')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('comédia')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('documentário')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('terror')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('ficcao')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('romance')");

        // INSERINDO DADOS PARA O RELACIONAMENTO MOVIE_CATEGORY
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_CATEGORY_TABLE+" ('movie_id', 'category_id') VALUES (1,5)");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_CATEGORY_TABLE+" ('movie_id', 'category_id') VALUES (2,4)");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_CATEGORY_TABLE+" ('movie_id', 'category_id') VALUES (3,3)");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_CATEGORY_TABLE+" ('movie_id', 'category_id') VALUES (4,2)");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_CATEGORY_TABLE+" ('movie_id', 'category_id') VALUES (5,1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if( oldVersion < newVersion ){

            db.execSQL("DROP TABLE IF EXISTS "+DbOpenHelper.CATEGORY_TABLE);
            db.execSQL(DbOpenHelper.sqlCreateCategoryTable);

            db.execSQL("DROP TABLE IF EXISTS "+DbOpenHelper.MOVIE_TABLE);
            db.execSQL(DbOpenHelper.sqlCreateMovieTable);

            db.execSQL("DROP TABLE IF EXISTS "+DbOpenHelper.MOVIE_CATEGORY_TABLE);
            db.execSQL(DbOpenHelper.sqlCreateMovieCategoryTable);
        }
    }
}
