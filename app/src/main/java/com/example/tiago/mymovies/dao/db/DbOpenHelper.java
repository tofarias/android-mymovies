package com.example.tiago.mymovies.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "db_mymovies";
    private static int DATABASE_VERSION = 1;
    private static String MOVIE_TABLE = "movie";
    private static String CATEGORY_TABLE = "category";

    private static String sqlCreateMovieTable = "CREATE TABLE "+ DbOpenHelper.MOVIE_TABLE+
                                                "(" +
                                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                "category_id int NOT NULL,"  +
                                                "title_en VARCHAR(50) UNIQUE," +
                                                "title_pt_br VARCHAR(50) UNIQUE," +
                                                "comment VARCHAR(100)," +
                                                "FOREIGN KEY(category_id) REFERENCES category(id)"+
                                                ")";

    private static String sqlCreateCategoryTable = "CREATE TABLE "+ DbOpenHelper.CATEGORY_TABLE+
                                                    "(" +
                                                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    "name VARCHAR(50) NOT NULL UNIQUE" +
                                                    ")";

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DbOpenHelper.sqlCreateCategoryTable);
        db.execSQL(DbOpenHelper.sqlCreateMovieTable);

        // INSERINDO DADOS PARA A TABELA CATEGORY
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Ação')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Comédia')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Documentário')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Terror')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Ficção Científica')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.CATEGORY_TABLE+" ('name') VALUES ('Drama')");

        // CARGA INICIAL PARA TESTES
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment') VALUES ('Lethal Weapon', 'Máquina Mortifera', 6,'abc...')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment') VALUES ('Die Hard', 'Duro de Matar', 5,'abc...')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment') VALUES ('The Blair Witch Project', 'A Bruxa de Blair', 4,'abc...')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment') VALUES ('Horrible Bosses', 'Quero Matar Meu Chefe', 3,'abc...')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment') VALUES ('Braveheart', 'Coracao Valente', 2,'abc...')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment') VALUES ('The Green Mile', 'A espera de um milagre', 1,'abc...')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if( oldVersion < newVersion ){

            db.execSQL("DROP TABLE IF EXISTS "+DbOpenHelper.CATEGORY_TABLE);
            db.execSQL(DbOpenHelper.sqlCreateCategoryTable);

            db.execSQL("DROP TABLE IF EXISTS "+DbOpenHelper.MOVIE_TABLE);
            db.execSQL(DbOpenHelper.sqlCreateMovieTable);
        }
    }
}
