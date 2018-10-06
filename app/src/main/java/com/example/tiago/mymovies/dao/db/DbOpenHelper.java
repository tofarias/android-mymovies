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
                                                "imdb_id VARCHAR(50) NOT NULL UNIQUE,"+
                                                "imdb_poster VARCHAR(50) NOT NULL UNIQUE,"+
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

        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id', 'imdb_id', 'imdb_poster') VALUES ('Lethal Weapon 1', 'Máquina Mortifera 1', 6,'Após a morte de sua esposa, o detetive da polícia de Los Angeles, Martin Riggs, torna-se temerário e suicida. O detetive é transferido e imediatamente entra em choque com o novo parceiro, Roger Murtaugh, o mais antigo funcionário do departamento. Juntos, eles descobrem um cartel do tráfico de drogas. As situações cada vez mais perigosas formam um vínculo entre os dois.', '1987', 5, 'tt009340', 'https://m.media-amazon.com/images/M/MV5BZTllNWNlZjctMWQwMS00ZDc3LTg5ZjMtNzhmNzhjMmVhYTFlXkEyXkFqcGdeQXVyNTc1NTQxODI@._V1_SX300.jpg')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id', 'imdb_id', 'imdb_poster') VALUES ('Die Hard 1', 'Duro de Matar 1', 5,'O policial de Nova York John McClane está visitando sua família no Natal. Ele participa de uma confraternização de fim de ano na sede da empresa japonesa em que a esposa trabalha. A festa é interrompida por terroristas que invadem o edifício de luxo. McClane não demora a perceber que não há ninguém para salvá-los, a não ser ele próprio.', '1988', 4, 'tt0095016', 'https://m.media-amazon.com/images/M/MV5BMzNmY2IwYzAtNDQ1NC00MmI4LThkOTgtZmVhYmExOTVhMWRkXkEyXkFqcGdeQXVyMTk5NDA3Nw@@._V1_SX300.jpg')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id', 'imdb_id', 'imdb_poster') VALUES ('The Blair Witch Project', 'A Bruxa de Blair', 4,'Um grupo de três jovens cineastas desaparece ao entrar em uma floresta de Maryland para gravar um documentário sobre uma lenda local conhecida como A Bruxa de Blair. Anos depois, a câmera que usavam é encontrada.', '1999', 3, 'tt0185937', 'https://m.media-amazon.com/images/M/MV5BNzQ1NDBlNDItMDAyYS00YTI2LTgwMmYtMzAwMzg4NDFlM2ZmXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id', 'imdb_id', 'imdb_poster') VALUES ('Horrible Bosses 1', 'Quero Matar Meu Chefe 1', 3,'Três amigos têm um problema em comum: eles odeiam seus chefes exploradores. Assim, bolam um plano. Já que não podem pedir demissão, contratam um consultor que os ajudará a matar os chefes.', '2011', 2, 'tt1499658', 'https://m.media-amazon.com/images/M/MV5BNzYxNDI5Njc5NF5BMl5BanBnXkFtZTcwMDUxODE1NQ@@._V1_SX300.jpg')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id', 'imdb_id', 'imdb_poster') VALUES ('Braveheart', 'Coracao Valente', 2,'A história de um herói escocês do século 13, chamado William Wallace, que lidera seus conterrâneos contra o monarca inglês Edward I, após ter sofrido uma tragédia pessoal causada pelos soldados ingleses. O exército amador de Wallace foi maior que o exército da Inglaterra.', '1995', 2, 'tt0112573', 'https://m.media-amazon.com/images/M/MV5BMzkzMmU0YTYtOWM3My00YzBmLWI0YzctOGYyNTkwMWE5MTJkXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id', 'imdb_id', 'imdb_poster') VALUES ('The Green Mile', 'A Espera de Um Milagre', 1, 'Um carcereiro tem um relacionamento incomum e comovente com um preso que está no corredor na morte: Coffey, um negro enorme, condenado por ter matado brutalmente duas gêmeas de nove anos. Ele tem tamanho e força para matar qualquer um, mas seu comportamento é completamente oposto à sua aparência. Além de ser simples, ingênuo e ter pavor do escuro, ele possui um dom sobrenatural. Com o passar do tempo, o carcereiro aprende que, às vezes, os milagres acontecem nos lugares mais inesperados.', '1999', 1, 'tt0120689', 'https://m.media-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1_SX300.jpg')");
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_TABLE+" ('title_en', 'title_pt_br', 'category_id','comment', 'release_year', 'watched_where_id', 'imdb_id', 'imdb_poster') VALUES ('The Man Who Knew Too Little', 'O Homem que Sabia de Menos', 2,'Wallace passa o aniversário com o irmão, que o leva a um espetáculo de representação interativo. Ele se envolve em plano de assassinato e, mesmo cercado de marginais, pensa que tudo é teatro. Os criminosos acham que ele é Spencer, um agente secreto.', '1997', 3, 'tt0120483', 'https://m.media-amazon.com/images/M/MV5BZTA2OTNiZWQtNTBlNi00OGNjLTkzMzgtOTZiZDY2ZWIyNTY1XkEyXkFqcGdeQXVyNjU0NTI0Nw@@._V1_SX300.jpg')");

        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(7, 3, 3.5, 4, 1, 3.5)" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(6, 3, 5.5, 5, 2, 2.5)" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(5, 4, 4.5, 4, 3, 3.5)" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(4, 5.5, 2.5, 3.5, 3.5, 2)" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(3, 3, 2.5, 4, 4, 1.5)" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(2, 2.5, 3, 2.5, 5, 1)" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.MOVIE_SCORE_TABLE +" ('movie_id', 'actors_score', 'duration_score', 'story_score', 'final_story_score', 'music_score') VALUES(1, 4, 5, 1.5, 3.5, 2)" );

        db.execSQL("INSERT INTO "+ DbOpenHelper.WATCHED_WHERE_TABLE +" ('name') VALUES('TV aberta')" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.WATCHED_WHERE_TABLE +" ('name') VALUES('Cinema')" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.WATCHED_WHERE_TABLE +" ('name') VALUES('Netflix')" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.WATCHED_WHERE_TABLE +" ('name') VALUES('Alugou DVD')" );
        db.execSQL("INSERT INTO "+ DbOpenHelper.WATCHED_WHERE_TABLE +" ('name') VALUES('Download, PC, Celular ...')" );
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
