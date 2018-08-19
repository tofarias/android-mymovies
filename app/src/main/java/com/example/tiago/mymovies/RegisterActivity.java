package com.example.tiago.mymovies;

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

public class RegisterActivity extends AppCompatActivity {

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
        EditText edtTitlePtBr = (EditText) findViewById(R.id.edtTitlePtBr);
        EditText edtTitleEn   = (EditText) findViewById(R.id.edtTitleEn);
        EditText edtComment   = (EditText) findViewById(R.id.edtComment);

        String titlePtBr = edtTitlePtBr.getText().toString().trim();
        String titleEn   = edtTitleEn.getText().toString().trim();
        String comment   = edtComment.getText().toString().trim();

        Movie movie = new Movie(titleEn, titlePtBr, new Category( this.getSelectedCategoryId() ), comment);

        MovieDao movieDao = new MovieDaoDb(this);

        try{
            movieDao.insert(movie);
            Toast.makeText(this,"Cadastro realizado com sucesso!",Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }

        finish();
    }

    public void cancelRegistration(View view){
        finish();
    }
}
