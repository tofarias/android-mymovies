package com.example.tiago.mymovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.tiago.mymovies.Validation.RegisterValidation;
import com.example.tiago.mymovies.dao.MovieDao;
import com.example.tiago.mymovies.dao.db.CategoryDaoDb;
import com.example.tiago.mymovies.dao.db.MovieDaoDb;
import com.example.tiago.mymovies.dao.db.MovieScoreDaoDb;
import com.example.tiago.mymovies.model.Category;
import com.example.tiago.mymovies.model.Movie;
import com.example.tiago.mymovies.model.MovieScore;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    RatingBar rbActorScore, rbMusicScore, rbStoryScore, rbFinalStoryScore, rbDurationScore;
    EditText edtTitlePtBr, edtTitleEn, edtComment, edtReleaseYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText edtTitlePtBr = findViewById(R.id.edtTitlePtBr);
        edtTitlePtBr.requestFocus();

        this.createCategoryRadioButtons();
    }

    public void createCategoryRadioButtons()
    {
        CategoryDaoDb categoryDao = new CategoryDaoDb(this);
        List<Category> categoryList = categoryDao.listAll();

        AppCompatRadioButton[] rb = new AppCompatRadioButton[categoryList.size()];

        RadioGroup rgp = (RadioGroup) findViewById(R.id.rb_categories);
        rgp.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < categoryList.size(); i++) {
            RadioButton rbn = new RadioButton(this);
            rbn.setId(categoryList.get(i).getId());
            rbn.setText(categoryList.get(i).getName());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1f
            );
            rbn.setLayoutParams(params);
            rgp.addView(rbn);
        }
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

    public void addMovie(View view)
    {
        this.edtTitlePtBr = (EditText) findViewById(R.id.edtTitlePtBr);
        this.edtTitleEn   = (EditText) findViewById(R.id.edtTitleEn);
        this.edtComment   = (EditText) findViewById(R.id.edtComment);
        this.edtReleaseYear = (EditText) findViewById(R.id.edtReleaseYear);

        String titlePtBr   = this.edtTitlePtBr.getText().toString().trim();
        String titleEn     = this.edtTitleEn.getText().toString().trim();
        String comment     = this.edtComment.getText().toString().trim();
        String releaseYear = this.edtReleaseYear.getText().toString().trim();

        this.rbActorScore       = (RatingBar) findViewById(R.id.rbActorScore);
        this.rbMusicScore       = (RatingBar) findViewById(R.id.rbMusicScore);
        this.rbDurationScore    = (RatingBar) findViewById(R.id.rbDurationScore);
        this.rbFinalStoryScore  = (RatingBar) findViewById(R.id.rbFinalStoryScore);
        this.rbStoryScore       = (RatingBar) findViewById(R.id.rbStoryScore);

        //

        RegisterValidation formValidation = new RegisterValidation(this);
        formValidation.validateTitlePtBr( edtTitlePtBr );
        formValidation.validateTitleEn( edtTitleEn );
        formValidation.validateReleaseYear( edtReleaseYear );
        formValidation.validateActorScore( rbActorScore );
        formValidation.validateMusicScore( rbMusicScore );
        formValidation.validateDurationScore( rbDurationScore );
        formValidation.validateFinalStoryScore( rbFinalStoryScore );
        formValidation.validateStoryScore( rbStoryScore );

        if( formValidation.isValid() ){

            Movie movie = new Movie(titleEn, titlePtBr, new Category( this.getSelectedCategoryId() ), comment, releaseYear);

            MovieDao movieDao = new MovieDaoDb(this);

            try{

                long movieId = movieDao.insert(movie);
                movie.setId( (int) movieId);

                MovieScore movieScore = new MovieScore(movie,
                        this.rbActorScore.getRating(),
                        this.rbMusicScore.getRating(),
                        this.rbDurationScore.getRating(),
                        this.rbFinalStoryScore.getRating(),
                        this.rbStoryScore.getRating()
                );

                MovieScoreDaoDb movieScoreDaoDb = new MovieScoreDaoDb(this);
                long idMovieScore = movieScoreDaoDb.insert(movieScore);

                Toast.makeText(this,"Cadastro realizado com sucesso!",Toast.LENGTH_SHORT).show();

                finish();

            }catch (Exception e) {
                Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
                toast.show();
                Log.d("addMovie", e.getMessage());
            }
        }
    }

    public void cancelRegistration(View view){
        finish();
    }
}
