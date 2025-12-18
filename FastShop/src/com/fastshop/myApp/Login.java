package com.fastshop.myApp;


import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;

public class Login extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     setContentView(R.layout.login);   
     ActionBar actionBar = getActionBar();
      actionBar.hide();
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);     
      window.setStatusBarColor(getResources().getColor(R.color.home_page)); 
       
     TextView createBtn = findViewById(R.id.create_account_btn); 
     LinearLayout parentLayout = findViewById(R.id.parentLayout); 

    createBtn.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view){
       Intent intent = new Intent(Login.this, Create_account.class);
       startActivity(intent);
      }
     }); 
     
    }
    @Override
    public void onBackPressed(){
     super.onBackPressed();
     Intent intent = new Intent(this, MainActivity.class);
     startActivity(intent);
    }
}