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


public class Coupon_code extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_code);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.home_page)); 
             
             ImageView close_page = findViewById(R.id.close_page);
       close_page.setColorFilter(Color.GRAY);
       close_page.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View v){
        finish();  
       }    
       });     
            
      ImageView image = findViewById(R.id.image);
       image.setColorFilter(getResources().getColor(R.color.tomato),PorterDuff.Mode.SRC_IN); 
       ImageView image2 = findViewById(R.id.image2);
       image2.setColorFilter(getResources().getColor(R.color.tomato),PorterDuff.Mode.SRC_IN);      
       
    }
    
    
}