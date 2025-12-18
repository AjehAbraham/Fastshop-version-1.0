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
import android.widget.FrameLayout;

public class ViewOrder extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_orders);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
         window.setStatusBarColor(getResources().getColor(R.color.home_page));
         
      LinearLayout header = findViewById(R.id.header);
       ImageView close_page = (ImageView) header.getChildAt(0);
       close_page.setColorFilter(Color.GRAY);
       close_page.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View v){
        finish();  
       }    
       });

     LinearLayout important = findViewById(R.id.important);
      for(int j = 0; j < important.getChildCount(); j++){
        View viewGroup = important.getChildAt(j);
        if(viewGroup instanceof TextView){
          TextView textView = (TextView) viewGroup;
          textView.setSelected(true);
        }
      } 
     
    LinearLayout less_important = findViewById(R.id.less_important);
      for(int k = 0; k < less_important.getChildCount(); k++){
        View viewGroup = less_important.getChildAt(k);
        if(viewGroup instanceof TextView){
          TextView textView = (TextView) viewGroup;
          textView.setSelected(true);
        }
      } 
     
      TextView textView = findViewById(R.id.cancel_order);
     
      FrameLayout container = findViewById(R.id.container);
      CancelOrder cancelOrderFlater = new CancelOrder(this);
      cancelOrderFlater.LoadUi(container, textView);
      
     
    }
 
 private void LoadMethod(){
  
 }
}