package com.example.passwordcreatorandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.example.passwordcreatorandroid.bussines.Controller;

public class ResetKeysDialog extends DialogFragment {
    Controller appController = null;
    public ResetKeysDialog(Controller appController){
        this.appController = appController;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Reseting encryption keys will give you access to different safe passwords than before.\n" +
                        "It is recommended to save any previous safe passwords that may have been used in services.\n" +
                        "  ¿Would you like to reset encryption keys?")
                .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        appController.resetKeys();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancels the dialog.
                    }
                });
        // Create the AlertDialog object and return it.
        return builder.create();
    }
}
