package com.example.tiago.mymovies.formvalidation;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import com.example.tiago.mymovies.Validation.FormValidation;
import com.example.tiago.mymovies.Validation.RegisterValidation;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class FieldReleaseYearTest {

    Context appContext;
    FormValidation formValidation;
    EditText edtReleaseYear;

    public FieldReleaseYearTest() {
        this.appContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void theFieldTitleEnWhenEmptyShouldShowErrorValidationMessage()
    {
        // Arrange
        String emptyText = "";
        this.edtReleaseYear = new EditText(this.appContext);
        this.formValidation = new RegisterValidation(this.appContext);

        // Action
        this.edtReleaseYear.setText( emptyText );
        this.formValidation.validateReleaseYear( edtReleaseYear );

        // Assertion
        assertEquals("edtReleaseYear", "O campo ANO DE LANÇAMENTO está em branco.", this.formValidation.getErrorValidationMessage());
    }

    @Test
    public void theFieldTitleEnWhenLengthGreatherThan50CharsShouldShowErrorValidationMessage()
    {
        // Arrange
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String text;
        this.edtReleaseYear = new EditText(this.appContext);
        this.formValidation = new RegisterValidation(this.appContext);

        // Action
        text = String.valueOf( currentYear + 1 );
        this.edtReleaseYear.setText( text );
        this.formValidation.validateReleaseYear( edtReleaseYear );

        // Assertion
        assertEquals("edtReleaseYear", "O campo ANO DE LANÇAMENTO deve ser menor ou igual ao ano atual ("+currentYear+").", this.formValidation.getErrorValidationMessage());
    }
}
