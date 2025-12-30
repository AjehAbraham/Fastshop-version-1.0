package com.fastshop.myApp;

import android.content.Context;
import android.view.View;
import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

public class Hidekeyboard{
    private Context context;
    private Activity activity;
    private View view;
    public Hidekeyboard(Activity activity){
      //  this.context = context;
        this.activity = activity;
    }
    public void Hide(View view){
      InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}