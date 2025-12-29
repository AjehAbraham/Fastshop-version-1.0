package com.fastshop.myApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.ActionBar;
import android.widget.FrameLayout;
import android.view.View;

public class myReview extends Activity{
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_review);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.home_page));    
     
          /* ImageView close_page = findViewById(R.id.close_page);
            close_page.setColorFilter(Color.GRAY);
            close_page.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               finish(); 
            }    
            });*/
          FrameLayout container = findViewById(R.id.container);
          web_content Webcontent = new web_content(this);
          Webcontent.LoadUi(container); 
    }
}