package com.example.tiago.mymovies;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.tiago.mymovies.dao.MovieDao;
import com.example.tiago.mymovies.dao.db.CategoryDaoDb;
import com.example.tiago.mymovies.dao.db.MovieDaoDb;
import com.example.tiago.mymovies.dao.db.MovieScoreDaoDb;
import com.example.tiago.mymovies.model.Category;
import com.example.tiago.mymovies.model.Movie;
import com.example.tiago.mymovies.model.MovieScore;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    public EditText edtTitleEn, edtTitlePtBr, edtMovieId, edtMovieCategory, edtComment, edtReleaseYear;
    RatingBar rbActorScore, rbMusicScore, rbStoryScore, rbFinalStoryScore, rbDurationScore;
    private String movieId;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        this.edtTitlePtBr = findViewById(R.id.edtTitlePtBr);
        this.edtTitleEn = findViewById(R.id.edtTitleEn);
        this.edtComment = findViewById(R.id.edtComment);
        this.edtReleaseYear = findViewById(R.id.edtReleaseYear);

        this.edtTitlePtBr.requestFocus();

        this.movieId = getIntent().getStringExtra("movie_id");

        MovieDaoDb movieDaoDb = new MovieDaoDb(this);
         this.movie = movieDaoDb.finById( this.movieId );

        this.edtTitlePtBr.setText( this.movie.getTitlePtBr() );
        this.edtTitleEn.setText( this.movie.getTitleEn() );
        this.edtComment.setText( this.movie.getComment() );
        this.edtReleaseYear.setText( this.movie.getReleaseYear() );

        //

        CategoryDaoDb categoryDao = new CategoryDaoDb(this);
        List<Category> categoryList = categoryDao.listAll();

        AppCompatRadioButton[] rb = new AppCompatRadioButton[categoryList.size()];

        RadioGroup rgp = (RadioGroup) findViewById(R.id.rb_categories);
        rgp.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < categoryList.size(); i++) {
            RadioButton rbn = new RadioButton(this);
            rbn.setId(categoryList.get(i).getId());
            rbn.setText(categoryList.get(i).getName());

            if( categoryList.get(i).getId() == movie.getCategory().getId() ){
                rbn.setChecked(true);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1f
            );
            rbn.setLayoutParams(params);
            rgp.addView(rbn);
        }

        //

        this.rbActorScore       = (RatingBar) findViewById(R.id.rbActorScore);
        this.rbMusicScore       = (RatingBar) findViewById(R.id.rbMusicScore);
        this.rbDurationScore    = (RatingBar) findViewById(R.id.rbDurationScore);
        this.rbFinalStoryScore  = (RatingBar) findViewById(R.id.rbFinalStoryScore);
        this.rbStoryScore       = (RatingBar) findViewById(R.id.rbStoryScore);

        MovieScoreDaoDb movieScoreDaoDb = new MovieScoreDaoDb(this);
        MovieScore movieScore = movieScoreDaoDb.findByMovieId( this.movieId );

        this.rbActorScore.setRating( movieScore.getActorsScore() );
        this.rbMusicScore.setRating( movieScore.getMusicScore() );
        this.rbDurationScore.setRating( movieScore.getDurationScore() );
        this.rbFinalStoryScore.setRating( movieScore.getFinalStoryScore() );
        this.rbStoryScore.setRating( movieScore.getStoryScore() );
    }

    public void deleteMovie(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir registro!");
        builder.setMessage("Tem certeza que deseja remover o filme "+ this.movie.getTitlePtBr()  +"?");
        builder.setCancelable(false);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                MovieScoreDaoDb movieScoreDaoDb = new MovieScoreDaoDb(getApplicationContext());
                movieScoreDaoDb.delete(movieId);

                MovieDaoDb movieDao = new MovieDaoDb(getApplicationContext());
                movieDao.delete(movieId);

                Toast.makeText(getApplicationContext(),"Exclusão realizada com sucesso!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setNegativeButton("Não",null);
        builder.show();
    }

    public void updateMovie(View view)
    {
        this.edtTitlePtBr = (EditText) findViewById(R.id.edtTitlePtBr);
        this.edtTitleEn   = (EditText) findViewById(R.id.edtTitleEn);
        this.edtComment   = (EditText) findViewById(R.id.edtComment);
        this.edtReleaseYear = (EditText) findViewById(R.id.edtReleaseYear);

        String titlePtBr = this.edtTitlePtBr.getText().toString().trim();
        String titleEn   = this.edtTitleEn.getText().toString().trim();
        String comment   = this.edtComment.getText().toString().trim();
        String releaseYear = this.edtReleaseYear.getText().toString().trim();

        Movie movie = new Movie(Integer.parseInt(this.movieId),
                                titleEn,
                                titlePtBr,
                                new Category( this.getSelectedCategoryId() ),
                                comment,releaseYear);

        MovieDao movieDaoDb = new MovieDaoDb(this);

        //

        this.rbActorScore       = (RatingBar) findViewById(R.id.rbActorScore);
        this.rbMusicScore       = (RatingBar) findViewById(R.id.rbMusicScore);
        this.rbDurationScore    = (RatingBar) findViewById(R.id.rbDurationScore);
        this.rbFinalStoryScore  = (RatingBar) findViewById(R.id.rbFinalStoryScore);
        this.rbStoryScore       = (RatingBar) findViewById(R.id.rbStoryScore);

        Float actorScore = this.rbActorScore.getRating();
        Float musicScore   = this.rbMusicScore.getRating();
        Float durationScore   = this.rbDurationScore.getRating();
        Float finalStoryScore = this.rbFinalStoryScore.getRating();
        Float storyScore = this.rbStoryScore.getRating();

        MovieScoreDaoDb movieScoreDaoDb = new MovieScoreDaoDb(this);

        MovieScore movieScore = new MovieScore(movie,
                                                actorScore,
                                                musicScore,
                                                durationScore,
                                                finalStoryScore,
                                                storyScore
                                                );

        try{
            movieDaoDb.update(movie);
            movieScoreDaoDb.update(movieScore);

            Toast.makeText(this,"Atualização realizada com sucesso!",Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }

        finish();
    }

    public int getSelectedCategoryId()
    {
        final RadioGroup rg = (RadioGroup) findViewById(R.id.rb_categories);
        int categoryId = 0;

        int selectedRadioButtonID = rg.getCheckedRadioButtonId();

        if (selectedRadioButtonID != -1) {

            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
            categoryId = selectedRadioButton.getId();
        }

        return categoryId;
    }

    public void cancelEdition(View view){
        finish();
    }
}
