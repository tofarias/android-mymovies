package com.example.tiago.mymovies.Validation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public abstract class FormValidation implements IFormValidation {

    protected AlertDialog.Builder builder;
    protected Context context;
    protected String errorValidationMessage;

    public FormValidation(Context context) {
        this.context = context;
        this.errorValidationMessage = "";
    }

    public String getErrorValidationMessage() {
        return errorValidationMessage;
    }

    @Override
    public boolean isValid() {

        Boolean isValid = true;

        if( !this.errorValidationMessage.isEmpty() ){

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Existem campos inválidos");
            builder.setMessage( this.errorValidationMessage );
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            });

            builder.show();
            isValid = false;
        }

        return isValid;
    }
}
