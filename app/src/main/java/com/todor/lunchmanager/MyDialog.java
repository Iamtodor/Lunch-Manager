package com.todor.lunchmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by todor on 23.07.15.
 */
public class MyDialog extends DialogFragment{
    CustomDialog customDialog;

    public static MyDialog newInstance(String title) {
        MyDialog dialog = new MyDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(getArguments().getString("title"));

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                passData("Yes");
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                passData("No");
            }
        });

        return dialog.create();
    }

    public interface CustomDialog {
        void onButtonClick(String nameButton, Dialog dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        customDialog = (CustomDialog) activity;
    }

    public void passData(String data) {
        customDialog.onButtonClick(data, getDialog());
    }
}
