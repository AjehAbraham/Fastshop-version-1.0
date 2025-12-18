package com.fastshop.myApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.ActionBar;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;

public class SearchOrders extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_orders);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
         window.setStatusBarColor(getResources().getColor(R.color.home_page));
         
      LinearLayout header = findViewById(R.id.header);
       ImageView close_page = (ImageView) header.getChildAt(0);
       close_page.setColorFilter(Color.BLACK);
       close_page.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View v){
        finish();  
       }    
       });     
       EditText editText = (EditText) header.getChildAt(1);
       editText.requestFocus();
       getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
      
    }
}