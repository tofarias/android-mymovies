package com.example.tiago.mymovies;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tiago.mymovies.OMDBApi.ControlLifeCycle;
import com.example.tiago.mymovies.Validation.RegisterValidation;
import com.example.tiago.mymovies.dao.MovieDao;
import com.example.tiago.mymovies.dao.db.CategoryDaoDb;
import com.example.tiago.mymovies.dao.db.MovieDaoDb;
import com.example.tiago.mymovies.dao.db.MovieScoreDaoDb;
import com.example.tiago.mymovies.dao.db.WatchedWhereDaoDb;
import com.example.tiago.mymovies.model.Category;
import com.example.tiago.mymovies.model.Movie;
import com.example.tiago.mymovies.model.MovieScore;
import com.example.tiago.mymovies.model.WatchedWhere;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    RatingBar rbActorScore, rbMusicScore, rbStoryScore, rbFinalStoryScore, rbDurationScore;
    EditText edtTitlePtBr, edtTitleEn, edtComment, edtReleaseYear;
    Spinner spinnerWatchedWhere;
    List<WatchedWhere> watchedWhereList;
    RadioGroup radioGroupCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText edtTitlePtBr = findViewById(R.id.edtTitlePtBr);
        edtTitlePtBr.requestFocus();

        this.createCategoryRadioButtons();
        this.populateSpinner();

        //

        edtTitleEn = findViewById(R.id.edtTitleEn);
        edtTitleEn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    requestOMDBApi();
                }
            }
        });
    }

    private class DownloadImageFromUrl extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromUrl(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

    private void populateSpinner() {
        WatchedWhereDaoDb watchedWhereDao = new WatchedWhereDaoDb(this);
        this.watchedWhereList = watchedWhereDao.listAll();

        ArrayAdapter<WatchedWhere> adapter = new ArrayAdapter<WatchedWhere>(this, android.R.layout.simple_list_item_1, watchedWhereList);

        this.spinnerWatchedWhere = (Spinner) findViewById(R.id.spinnerWatchedWhere);
        this.spinnerWatchedWhere.setAdapter(adapter);
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

    public int getRadioButtonSelectedCategoryId()
    {
        this.radioGroupCategories = (RadioGroup) findViewById(R.id.rb_categories);
        int categoryId = 0;

        int selectedRadioButtonID = radioGroupCategories.getCheckedRadioButtonId();

        if (selectedRadioButtonID != -1) {

            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
            categoryId = selectedRadioButton.getId();
        }

        return categoryId;
    }

    public int getSpinnerWatchedWhereSelectedId()
    {
        return this.watchedWhereList.get( this.spinnerWatchedWhere.getSelectedItemPosition() ).getId();
    }

    public void requestOMDBApi()
    {
        this.edtTitleEn   = (EditText) findViewById(R.id.edtTitleEn);
        String titleEn     = this.edtTitleEn.getText().toString().trim();

        Call<com.example.tiago.mymovies.OMDBApi.Movie> call = ControlLifeCycle.service.detailTitle(titleEn);

        call.enqueue(new Callback<com.example.tiago.mymovies.OMDBApi.Movie>() {
            @Override
            public void onResponse(
                    Call<com.example.tiago.mymovies.OMDBApi.Movie> call,
                    Response<com.example.tiago.mymovies.OMDBApi.Movie> response) {

                if (response.isSuccessful()) {

                    com.example.tiago.mymovies.OMDBApi.Movie movie = response.body();

                    if( movie.imdbID == null ){

                        Toast.makeText(RegisterActivity.this,"Filme não encontrado",Toast.LENGTH_SHORT).show();

                    }else{
                        edtReleaseYear = (EditText) findViewById(R.id.edtReleaseYear);
                        edtReleaseYear.setText( movie.Year.toString() );

                        new DownloadImageFromUrl((ImageView) findViewById(R.id.imageView)).execute(movie.Poster);
                    }
                }
            }

            @Override
            public void onFailure(
                    Call<com.example.tiago.mymovies.OMDBApi.Movie> call,
                    Throwable t) {

                Log.d("onFailure",t.getMessage());
            }
        });
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
        formValidation.validateCategory( (RadioGroup) findViewById(R.id.rb_categories) );

        if( formValidation.isValid() ){

            Category category = new Category(this.getRadioButtonSelectedCategoryId());
            WatchedWhere watchedWhere = new WatchedWhere( this.getSpinnerWatchedWhereSelectedId() );

            Movie movie = new Movie(titleEn, titlePtBr, category, comment, releaseYear, watchedWhere);

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
