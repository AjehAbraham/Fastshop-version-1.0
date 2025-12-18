package com.fastshop.myApp;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.app.ActionBar;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.Typeface;

public class SplashScreen extends Activity{
    
   @Override
   protected void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     setContentView(R.layout.splash_screen);
     ActionBar actionBar = getActionBar();
      actionBar.hide();
     Window window = getWindow();
     window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
     window.setStatusBarColor(getResources().getColor(R.color.home_page));
       
     Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/TiltPrism-Regular-VariableFont_XROT,YROT.ttf");
         TextView textView = findViewById(R.id.textView_header);
         textView.setTypeface(typeface);  
     Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Poppins-Regular.ttf");
     TextView sologanText = findViewById(R.id.slogan_textView);
     sologanText.setTypeface(typeFace);
       
    ImageView imageView = findViewById(R.id.image_view);
    imageView.setImageResource(R.drawable.outline_star_half_white_48);
    imageView.setColorFilter(getResources().getColor(R.color.yellow));           
       
   }    
    
    
   @Override
   protected void onResume(){
     super.onResume();
     
  new Handler().postDelayed(new Runnable(){
    @Override
    public void run(){
      Intent intent = new Intent(SplashScreen.this, Welcome.class);
      startActivity(intent);
      finish();
    }
  },2000);
     
   }
    
}