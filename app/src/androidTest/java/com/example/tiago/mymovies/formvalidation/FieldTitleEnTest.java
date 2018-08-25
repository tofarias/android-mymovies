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
public class FieldTitleEnTest {

    Context appContext;
    FormValidation formValidation;
    EditText edtTitleEn;

    public FieldTitleEnTest() {
        this.appContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void theFieldTitleEnWhenEmptyShouldShowErrorValidationMessage()
    {
        // Arrange
        String emptyText = "";
        this.edtTitleEn = new EditText(this.appContext);
        this.formValidation = new RegisterValidation(this.appContext);

        // Action
        this.edtTitleEn.setText( emptyText );
        this.formValidation.validateTitleEn( edtTitleEn );

        // Assertion
        assertEquals("edtTitleEn", "O campo TITLE está em branco.", this.formValidation.getErrorValidationMessage());
    }

    @Test
    public void theFieldTitleEnWhenLengthGreatherThan50CharsShouldShowErrorValidationMessage()
    {
        // Arrange
        String text = "abcdefghijklmnopqrstuvxzabcdefghijklmnopqrstuvxz...";
        this.edtTitleEn = new EditText(this.appContext);
        this.formValidation = new RegisterValidation(this.appContext);

        // Action
        this.edtTitleEn.setText( text );
        this.formValidation.validateTitleEn( edtTitleEn );

        // Assertion
        assertEquals("edtTitleEn", "O campo TITLE deve conter no máximo 50 caracteres.", this.formValidation.getErrorValidationMessage());
    }
}
