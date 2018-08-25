package com.example.tiago.mymovies.Validation;

import android.content.Context;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import java.util.Calendar;

public class RegisterValidation extends FormValidation {

    public RegisterValidation(Context context) {
        super(context);
    }

    @Override
    public void validateTitlePtBr(final EditText edtTitlePtBr) {
        if( edtTitlePtBr.getText().toString().trim().isEmpty() ){
            this.errorValidationMessage = "O campo TÍTULO está em branco.";
        }

        if( edtTitlePtBr.getText().length() > 50){
            this.errorValidationMessage = "O campo TÍTULO deve conter no máximo 50 caracteres.";
        }
    }

    @Override
    public void validateTitleEn(EditText edtTitleEn) {
        if( edtTitleEn.getText().toString().trim().isEmpty() ){
            this.errorValidationMessage = "O campo TITLE está em branco.";
        }

        if( edtTitleEn.getText().length() > 50){
            this.errorValidationMessage = "O campo TITLE deve conter no máximo 50 caracteres.";
        }
    }

    @Override
    public void validateReleaseYear(EditText edtReleaseYear) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if( edtReleaseYear.getText().toString().trim().isEmpty() ){
            this.errorValidationMessage = "O campo ANO DE LANÇAMENTO está em branco.";
        }else if( Integer.parseInt( edtReleaseYear.getText().toString() ) > currentYear ){
            this.errorValidationMessage = "O campo ANO DE LANÇAMENTO deve ser menor ou igual ao ano atual ("+currentYear+").";
        }
    }

    @Override
    public void validateActorScore(RatingBar rbActorScore) {
        if(  rbActorScore.getRating() == 0 ){
            this.errorValidationMessage = "O ANO DE LANÇAMENTO deve ser avaliado!";
        }
    }

    @Override
    public void validateMusicScore(RatingBar rbMusicScore) {
        if(  rbMusicScore.getRating() == 0 ){
            this.errorValidationMessage = "A MÚSICA / TRILHA SONORA deve ser avaliada!";
        }
    }

    @Override
    public void validateStoryScore(RatingBar rbStoryScore) {
        if(  rbStoryScore.getRating() == 0 ){
            this.errorValidationMessage = "A ESTÓRIA / TEMA deve ser avaliada!";
        }
    }

    @Override
    public void validateFinalStoryScore(RatingBar rbFinalStoryScore) {
        if(  rbFinalStoryScore.getRating() == 0 ){
            this.errorValidationMessage = "A ESTÓRIA FINAL/ ENCERRAMENTO deve ser avaliada!";
        }
    }

    @Override
    public void validateDurationScore(RatingBar rbDurationScore) {
        if(  rbDurationScore.getRating() == 0 ){
            this.errorValidationMessage = "A DURAÇÃO / TEMPO DO FILME deve ser avaliada!";
        }
    }

    @Override
    public void validateCategory(RadioGroup radioGroupCategories) {
        if (radioGroupCategories.getCheckedRadioButtonId() == -1) {
            this.errorValidationMessage = "O campo Gênero não está preenchido.";
        }
    }
}
