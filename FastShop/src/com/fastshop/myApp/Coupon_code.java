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
import android.util.TypedValue;
import android.graphics.Typeface;

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
            
     headrMethods(); 
    }
   private TextView previousSelected ;
   private String selectedText;
    private void headrMethods(){
    LinearLayout header_categories = findViewById(R.id.header_categories);
if(header_categories.getChildCount() > 0){
    View newView = header_categories.getChildAt(0);
    LinearLayout subLayout_one = (LinearLayout) newView;
    if(subLayout_one.getChildAt(0) instanceof TextView ){
        TextView defaultTextView = (TextView) subLayout_one.getChildAt(0);
        defaultTextView.setTypeface(null, Typeface.BOLD);
        defaultTextView.setTextColor(Color.BLACK);
        selectedText = defaultTextView.getText().toString();
        previousSelected = defaultTextView; // set previousSelected to the default text view
        CustomToast.show(this, selectedText);
    }
}

for(int i = 0; i < header_categories.getChildCount(); i++){
    try{
        View view = header_categories.getChildAt(i);
        if(view instanceof LinearLayout){
            LinearLayout subLayout = (LinearLayout) view;
            if(subLayout.getChildAt(0) instanceof TextView){
                TextView textView = (TextView) subLayout.getChildAt(0);
                final TextView finalTextView = textView; // make it final to use in the listener
                subLayout.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        if(previousSelected != null){
                            previousSelected.setTextColor(Color.GRAY);
                            previousSelected.setTypeface(null,Typeface.NORMAL);
                        }
                        finalTextView.setTextColor(Color.BLACK);
                        finalTextView.setTypeface(null, Typeface.BOLD);
                        previousSelected = finalTextView;
                        CustomToast.show(Coupon_code.this, finalTextView.getText().toString());
                        selectedText = finalTextView.getText().toString();
                       loadRedeemUi(selectedText);
                    }
                });
            }
        }
    }catch(Exception e){
        CustomToast.show(this, e.getMessage());
    }
}
    }
 private void loadRedeemUi(String code){
  LinearLayout container = findViewById(R.id.parentLayout);
  if(code != null && code.equalsIgnoreCase("Redeem")){
   RedeemCoupon redeemCoupon = new RedeemCoupon(this);
   redeemCoupon.LoadUi(container);
  }else{
   container.removeAllViews();
  }
 }
}