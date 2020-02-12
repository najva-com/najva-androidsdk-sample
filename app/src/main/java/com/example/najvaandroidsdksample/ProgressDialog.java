package com.example.najvaandroidsdksample;

import android.app.AlertDialog;
import android.content.Context;

public class ProgressDialog extends AlertDialog {
    protected ProgressDialog(Context context) {
        super(context);
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.dialog_progress);
        setCancelable(false);
    }
}
