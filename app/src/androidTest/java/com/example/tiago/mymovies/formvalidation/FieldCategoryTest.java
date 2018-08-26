package com.example.tiago.mymovies.formvalidation;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tiago.mymovies.Validation.FormValidation;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class FieldCategoryTest {

    Context appContext;
    FormValidation formValidation;

    public FieldCategoryTest() {
        this.appContext = InstrumentationRegistry.getTargetContext();
    }

    @Ignore
    @Test
    public void theFieldTitleEnWhenEmptyShouldShowErrorValidationMessage()
    {
        final RadioButton[] rb = new RadioButton[5];
        RadioGroup rg = new RadioGroup(this.appContext);

        for(int i=0; i<5; i++){
            rb[i]  = new RadioButton(this.appContext);
            rb[i].setText(" radio button " + i);
            rb[i].setId(i);
            rg.addView(rb[i]);
        }

        this.formValidation.validateCategory( (RadioGroup) rg );

        // Assertion
        assertEquals("edtReleaseYear", "O Gênero deve ser informado!", this.formValidation.getErrorValidationMessage());
    }
}
