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
import android.view.animation.Animation;
import android.os.Handler;
import android.content.Intent;
import android.animation.ObjectAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.ViewGroup;


public class message extends Activity{
    private View nav_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
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
       

          
       loadLayout();     
      addStyle();      
    }
   
   
   private void addStyle(){
 LinearLayout parentLayout = findViewById(R.id.parentLayout);
     parentStyle(parentLayout);
   }
  private void parentStyle(ViewGroup Layout){
       for(int i = 0; i < Layout.getChildCount(); i++){
          View view = Layout.getChildAt(i);
          if(view instanceof ImageView){
             ImageView image = (ImageView) view;
             image.setColorFilter(Color.BLACK);
          }else if(view instanceof LinearLayout){
             LinearLayout newLayout = (LinearLayout) view;
            parentStyle(newLayout); 
          }
       }  
          
  } 
   
    private void loadLayout(){
    LinearLayout parent_layout = findViewById(R.id.parentLayout);
    loadLayoutRecursive(parent_layout);
}

private void loadLayoutRecursive(ViewGroup layout) {
    for (int i = 0; i < layout.getChildCount(); i++) {
        View view = layout.getChildAt(i);
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        } else if (view instanceof LinearLayout) {
            LinearLayout newLayout = (LinearLayout) view;
            newLayout.setElevation(20);
            //loadLayoutRecursive(newLayout);
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Poppins-Medium.ttf");
            textView.setTypeface(typeface);
        }
    }
}    
}