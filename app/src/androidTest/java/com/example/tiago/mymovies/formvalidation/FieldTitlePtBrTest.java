package com.example.tiago.mymovies.formvalidation;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import com.example.tiago.mymovies.Validation.FormValidation;
import com.example.tiago.mymovies.Validation.RegisterValidation;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class FieldTitlePtBrTest {

    Context appContext;
    FormValidation formValidation;
    EditText edtTitlePtBr, edtTitleEn;

    public FieldTitlePtBrTest() {
        this.appContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void theFieldTitlePtBrWhenEmptyShouldShowErrorValidationMessage()
    {
        // Arrange
        String emptyText = "";
        this.edtTitlePtBr = new EditText(this.appContext);
        this.formValidation = new RegisterValidation(this.appContext);

        // Action
        this.edtTitlePtBr.setText( emptyText );
        this.formValidation.validateTitlePtBr( edtTitlePtBr );

        // Assertion
        assertEquals("edtTitlePtBr", "O campo TÍTULO está em branco.", this.formValidation.getErrorValidationMessage());
    }

    @Test
    public void theFieldTitlePtBrWhenLengthGreatherThan50CharsShouldShowErrorValidationMessage()
    {
        // Arrange
        String text = "abcdefghijklmnopqrstuvxzabcdefghijklmnopqrstuvxz...";
        this.edtTitlePtBr = new EditText(this.appContext);
        this.formValidation = new RegisterValidation(this.appContext);

        // Action
        this.edtTitlePtBr.setText( text );
        this.formValidation.validateTitlePtBr( edtTitlePtBr );

        // Assertion
        assertEquals("edtTitlePtBr", "O campo TÍTULO deve conter no máximo 50 caracteres.", this.formValidation.getErrorValidationMessage());
    }
}
