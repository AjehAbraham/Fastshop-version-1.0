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
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.AccelerateDecelerateInterpolator;

public class quick_action extends Activity{
    private View nav_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_action);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.home_page));    
        LinearLayout linearLayout = findViewById(R.id.parentLayout);
        linearLayout.setVisibility(View.VISIBLE); 

      linearLayout.postDelayed(new Runnable(){
          @Override 
          public void run(){
    linearLayout.setTranslationX(-linearLayout.getWidth());
    linearLayout.setAlpha(0);
    linearLayout.animate().translationX(0).alpha(1).setDuration(500).start();
          }       
      },50);
    // To hide the layout
    /*linearLayout.animate().translationX(-linearLayout.getWidth()).alpha(0).setDuration(500).withEndAction(new Runnable() {
        @Override
        public void run() {
            linearLayout.setVisibility(View.GONE);
        }
    }).start();*/
  
            
            
        window.setStatusBarColor(getResources().getColor(R.color.home_page)); 
        LinearLayout navigation_layout = findViewById(R.id.navigation_layout);
         nav_bar =  navigation_bar_inflater.Navigators(this); 
        navigation_layout.addView(nav_bar);     
        
     LinearLayout  header = findViewById(R.id.header);
        View count = header.getChildAt(0);
        if(count instanceof ImageView){
            ImageView image = (ImageView) count;
            image.setColorFilter(Color.BLACK);
            image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              Intent intent = new Intent(quick_action.this, MainActivity.class);
             startActivity(intent);
               finish(); 
            }    
            });
        }        
              
    loadLayout();
    LoadLinearLayout();
        loadPrompt();
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
            newLayout.setElevation(10);
            loadLayoutRecursive(newLayout);
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Poppins-Medium.ttf");
            textView.setTypeface(typeface);
        }
    }
}
 private void LoadLinearLayout(){
  LinearLayout parentLayout = findViewById(R.id.parentLayout);
  showAnimation(parentLayout);      
 }  
 private void showAnimation(ViewGroup headerLayout) {
     for(int i = 0; i < headerLayout.getChildCount(); i++){
         View view = headerLayout.getChildAt(i);
         final int index = i/2;
         
         if(view instanceof LinearLayout){
             LinearLayout layout = (LinearLayout) view;
  
            layout.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
            Animation animation = new AlphaAnimation(0, 1);
            animation.setInterpolator(new AccelerateDecelerateInterpolator());
            animation.setDuration(1000);
            layout.startAnimation(animation);
                    if(index == 0){
                     Intent intent = new Intent(quick_action.this, message.class);
                     startActivity(intent);
                 }else if(index == 1){
                 Intent intent = new Intent(quick_action.this, Myorders.class);
                  startActivity(intent);
                 }else if(index == 2){
                 Intent intent = new Intent(quick_action.this, myReview.class);
                    startActivity(intent);
                 }else if(index == 3){
                  Intent intent = new Intent(quick_action.this, Coupon_code.class); 
                  startActivity(intent);
                 }else if(index == 4){
                 Intent intent = new Intent(quick_action.this, credit_bal.class);
                 startActivity(intent);
                 }else if(index == 5){
                  Intent intent = new Intent(quick_action.this, address.class);
                   startActivity(intent);
                 }else if(index == 6){
                    Intent intent = new Intent(quick_action.this, webView.class);
                    intent.putExtra("url", "live_chat");
                    startActivity(intent);
                 }else if(index == 7){
                     Intent intent = new Intent(quick_action.this, settings.class);
                     startActivity(intent);
                 }
             } 
            });
         }
     }
     
 } 
    
  private void loadPrompt(){
   LinearLayout container = findViewById(R.id.add_login_prompt);
   SignUpInflater flater = new SignUpInflater(this);
    flater.showLayout(container);
  }
    
    private void addConnectionstatus(){
        LinearLayout add_connection_status = findViewById(R.id.add_connection_status);
        View view = no_connection_inflater.getNoConnectionView(this);
        add_connection_status.addView(view);
    }
  @Override
 protected void onResume(){
  super.onResume();
   //  addConnectionstatus();
     
  View navLayout =  nav_bar;
  navLayout.postDelayed(new Runnable(){
   @Override
   public void run(){
  navigation_bar_inflater.changeAllTheme();        
   navigation_bar_inflater.ChangeThird();
       
   }
  },50); 
 }
   
   @Override
   public void onBackPressed(){
       finish();
   }  
    
}