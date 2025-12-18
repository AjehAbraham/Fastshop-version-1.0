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
import android.view.ViewGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.FrameLayout;

public class account_security extends Activity{
    private LinearLayout edit_profile_prompt,change_password_prompt;
    private ImageView close_password_prompt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_security);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.home_page)); 
            
            
        LinearLayout header = findViewById(R.id.header);
         View view = header.getChildAt(0);
            if(view instanceof ImageView){
                ImageView imageView = (ImageView) view;
                 imageView.setColorFilter(Color.BLACK);
                    imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               finish(); 
            }    
            });
            }
       LinearLayout options = findViewById(R.id.options);
      optionstyle(options); 
       
       edit_profile_prompt = findViewById(R.id.edit_profile_prompt);
       ImageView close_page_prompt= findViewById(R.id.close_page_prompt);
       TextView  open_prompt = findViewById(R.id.open_prompt);
       
     open_prompt.setOnClickListener(new View.OnClickListener(){
      @Override
       public void onClick(View v){
        edit_profile_prompt.setVisibility(View.VISIBLE); 
       }
     });
       close_page_prompt.setColorFilter(Color.GRAY);
       close_page_prompt.setOnClickListener(new View.OnClickListener(){
      @Override
       public void onClick(View v){
        edit_profile_prompt.setVisibility(View.GONE); 
       }
     });
       
       
      change_password_prompt = findViewById(R.id.change_password_prompt);
      close_password_prompt = findViewById(R.id.close_password_prompt);
      close_password_prompt.setColorFilter(Color.GRAY);
       close_password_prompt.setOnClickListener(new View.OnClickListener(){
      @Override
       public void onClick(View v){
        change_password_prompt.setVisibility(View.GONE); 
       }
     });
       
    TextView change_password_btn = findViewById(R.id.change_password_btn);
     change_password_btn.setOnClickListener(new View.OnClickListener(){
      @Override
       public void onClick(View v){
        change_password_prompt.setVisibility(View.VISIBLE); 
       }
     });
        
       
    }
   private void optionstyle(ViewGroup options){
      
      for(int i = 0; i < options.getChildCount(); i++){
      View view = options.getChildAt(i);
         if(view instanceof ImageView){
            ImageView imageView = (ImageView) view;
            imageView.setColorFilter(Color.BLACK);
         }else if (view instanceof LinearLayout){
            LinearLayout layout = (LinearLayout) view;
            optionstyle((ViewGroup) layout);
         }       
      } 
   }
   
 @Override 
   protected void onResume(){
      super.onResume();
      
   }
}