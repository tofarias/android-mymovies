package com.example.tiago.mymovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tiago.mymovies.dao.db.CategoryDaoDb;
import com.example.tiago.mymovies.dao.db.MovieDaoDb;
import com.example.tiago.mymovies.model.Category;
import com.example.tiago.mymovies.model.Movie;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    public EditText edtTitleEn, edtTitlePtBr, edtMovieId, edtMovieCategory, edtComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtTitlePtBr = findViewById(R.id.edtTitlePtBr);
        edtTitleEn = findViewById(R.id.edtTitleEn);
        edtComment = findViewById(R.id.edtComment);

        String movieId = getIntent().getStringExtra("movie_id");

        MovieDaoDb movieDaoDb = new MovieDaoDb(this);
        Movie movie = movieDaoDb.finById( movieId );

        edtTitlePtBr.setText( movie.getTitlePtBr() );
        edtTitleEn.setText( movie.getTitleEn() );
        edtComment.setText( movie.getComment() );

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
    }

    public void cancelEdition(View view){
        finish();
    }
}
