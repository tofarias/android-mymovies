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
import android.widget.Toast;

import com.example.tiago.mymovies.dao.MovieDao;
import com.example.tiago.mymovies.dao.db.CategoryDaoDb;
import com.example.tiago.mymovies.dao.db.MovieDaoDb;
import com.example.tiago.mymovies.model.Category;
import com.example.tiago.mymovies.model.Movie;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    public EditText edtTitleEn, edtTitlePtBr, edtMovieId, edtMovieCategory, edtComment;
    private String movieId;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        this.edtTitlePtBr = findViewById(R.id.edtTitlePtBr);
        this.edtTitleEn = findViewById(R.id.edtTitleEn);
        this.edtComment = findViewById(R.id.edtComment);

        this.edtTitlePtBr.requestFocus();

        this.movieId = getIntent().getStringExtra("movie_id");

        MovieDaoDb movieDaoDb = new MovieDaoDb(this);
         this.movie = movieDaoDb.finById( this.movieId );

        this.edtTitlePtBr.setText( this.movie.getTitlePtBr() );
        this.edtTitleEn.setText( this.movie.getTitleEn() );
        this.edtComment.setText( this.movie.getComment() );

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

    public void deleteMovie(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir registro!");
        builder.setMessage("Tem certeza que deseja remover o filme "+ this.movie.getTitlePtBr()  +"?");
        builder.setCancelable(false);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

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

        String titlePtBr = this.edtTitlePtBr.getText().toString().trim();
        String titleEn   = this.edtTitleEn.getText().toString().trim();
        String comment   = this.edtComment.getText().toString().trim();

        Movie movie = new Movie(Integer.parseInt(this.movieId),
                                titleEn,
                                titlePtBr,
                                new Category( this.getSelectedCategoryId() ),
                                comment);

        MovieDao movieDao = new MovieDaoDb(this);

        try{
            movieDao.update(movie);
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
