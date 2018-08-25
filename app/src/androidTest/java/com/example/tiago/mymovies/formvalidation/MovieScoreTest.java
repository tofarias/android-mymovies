package com.example.tiago.mymovies.formvalidation;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.RatingBar;

import com.example.tiago.mymovies.Validation.FormValidation;
import com.example.tiago.mymovies.Validation.RegisterValidation;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class MovieScoreTest {

    Context appContext;
    FormValidation formValidation;
    RatingBar rbActorScore, rbMusicScore, rbStoryScore, rbFinalStoryScore, rbDurationScore;

    public MovieScoreTest() {
        this.appContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void theActorScoreWhenNotEvaluatedShouldShowErrorValidationMessage()
    {
        // Arrange
        float rating = 0;
        this.rbActorScore = new RatingBar(this.appContext);
        this.formValidation = new RegisterValidation(this.appContext);

        // Action
        this.rbActorScore.setRating( rating );
        this.formValidation.validateActorScore( this.rbActorScore );

        // Assertion
        assertEquals("rbActorScore", "O ANO DE LANÇAMENTO deve ser avaliado!", this.formValidation.getErrorValidationMessage());
    }

    @Test
    public void theMusicScoreWhenNotEvaluatedShowErrorValidationMessage()
    {
        // Arrange
        float rating = 0;
        this.rbMusicScore = new RatingBar(this.appContext);
        this.formValidation = new RegisterValidation(this.appContext);

        // Action
        this.rbMusicScore.setRating( rating );
        this.formValidation.validateMusicScore( this.rbMusicScore );

        // Assertion
        assertEquals("rbMusicScore", "A MÚSICA / TRILHA SONORA deve ser avaliada!", this.formValidation.getErrorValidationMessage());
    }

    @Test
    public void theStoryScoreWhenNotEvaluatedShowErrorValidationMessage()
    {
        // Arrange
        float rating = 0;
        this.rbStoryScore = new RatingBar(this.appContext);
        this.formValidation = new RegisterValidation(this.appContext);

        // Action
        this.rbStoryScore.setRating( rating );
        this.formValidation.validateStoryScore( this.rbStoryScore );

        // Assertion
        assertEquals("rbStoryScore", "A ESTÓRIA / TEMA deve ser avaliada!", this.formValidation.getErrorValidationMessage());
    }

    @Test
    public void theFinalStoryScoreWhenNotEvaluatedShowErrorValidationMessage()
    {
        // Arrange
        float rating = 0;
        this.rbFinalStoryScore = new RatingBar(this.appContext);
        this.formValidation = new RegisterValidation(this.appContext);

        // Action
        this.rbFinalStoryScore.setRating( rating );
        this.formValidation.validateFinalStoryScore( this.rbFinalStoryScore );

        // Assertion
        assertEquals("rbFinalStoryScore", "A ESTÓRIA FINAL/ ENCERRAMENTO deve ser avaliada!", this.formValidation.getErrorValidationMessage());
    }

    @Test
    public void theDurationScoreWhenNotEvaluatedShowErrorValidationMessage()
    {
        // Arrange
        float rating = 0;
        this.rbDurationScore = new RatingBar(this.appContext);
        this.formValidation = new RegisterValidation(this.appContext);

        // Action
        this.rbDurationScore.setRating( rating );
        this.formValidation.validateDurationScore( this.rbDurationScore );

        // Assertion
        assertEquals("rbDurationScore", "A DURAÇÃO / TEMPO DO FILME deve ser avaliada!", this.formValidation.getErrorValidationMessage());
    }
}
