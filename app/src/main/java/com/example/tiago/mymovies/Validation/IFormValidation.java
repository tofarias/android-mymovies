package com.example.tiago.mymovies.Validation;

import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;

public interface IFormValidation {
    public void validateTitlePtBr(EditText edtTitlePtBr);
    public void validateTitleEn(EditText edtTitleEn);
    public void validateReleaseYear(EditText edtReleaseYear);
    public void validateActorScore(RatingBar rbActorScore);
    public void validateMusicScore(RatingBar rbMusicScore);
    public void validateStoryScore(RatingBar rbStoryScore);
    public void validateFinalStoryScore(RatingBar rbFinalStoryScore);
    public void validateDurationScore(RatingBar rbDurationScore);
    public void validateCategory( RadioGroup radioGroupCategories );
    public boolean isValid();
}
