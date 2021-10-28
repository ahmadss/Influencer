package com.example.test.utils;

import android.app.ProgressDialog;
import android.content.Context;


public class ProgressDialogUtil extends ProgressDialog {
    Context ctx;
    int dialogType;
    boolean cancelable;
    public ProgressDialogUtil(Context ctx, int dialogType, boolean cancelable) {
        super(ctx);
        this.ctx=ctx;
        this.dialogType = dialogType;
        this.cancelable = cancelable;
    }



    @Override
    public void show() {
        setProgressStyle(dialogType);
        setMessage("Loading...");
        setCancelable(cancelable);
        super.show();
    }


    @Override
    public int getMax() {
        return super.getMax();
    }


    @Override
    public int getProgress() {
        return super.getProgress();
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }
}
