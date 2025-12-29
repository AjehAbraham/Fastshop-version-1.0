package com.fastshop.myApp;

import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.PorterDuff;
import android.graphics.Color;
import android.content.Intent;
import android.content.SharedPreferences;


public class credit_bal extends Activity{
    private ImageView bal_visibility;
    private TextView balance_text;
    private String MyBalance ;
    @Override
    protected void onCreate(Bundle saveInstanceState){
     super.onCreate(saveInstanceState);
     setContentView(R.layout.credit_bal);
     ActionBar actionBar = getActionBar();
     actionBar.hide();
     Window window = getWindow();
     window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
     window.setStatusBarColor(getResources().getColor(R.color.home_page));
      
     ImageView close_page = findViewById(R.id.close_page);
     close_page.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
     close_page.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
       finish();
      }
     }); 
     
     
    TextView open_saved_details = findViewById(R.id.open_saved_details);
     open_saved_details.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
     Intent intent = new Intent(credit_bal.this, top_up_balance.class);
     startActivity(intent);
     }
     }); 
     bal_visibility = findViewById(R.id.bal_visibility);
     bal_visibility.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
     MyBalance = "₦48,550.89";
     balance_text = findViewById(R.id.balance_text);
     toggleBalance(bal_visibility,MyBalance,balance_text);
     
     
     LinearLayout parentLayout = findViewById(R.id.parentLayout);
     loadFont(parentLayout);
     loadBalanceStatus(balance_text,MyBalance);
     Add_account_card();
     changeTransactionTheme();
    }
  private void Add_account_card(){
    TextView open_top_up_btn = findViewById(R.id.open_top_up_btn);
    open_top_up_btn.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v){
      Intent intent = new Intent(credit_bal.this, Add_card_account.class);
      startActivity(intent);
     }
    });
   }
 
 private void loadFont(LinearLayout parentLayout){
  for(int i = 0; i < parentLayout.getChildCount(); i ++){
   View view = parentLayout.getChildAt(i);
   if(view instanceof TextView){
    TextView textView = (TextView) view;
    Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Poppins-Regular.ttf");
    textView.setTypeface(typeface);
   }
  }
 }
 
   private void toggleBalance(ImageView visibility_btn, final String MyBalance, final TextView balance_text){
    SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
    String BalanceStatus = prefs.getString("myBalanceStatus","show");
    if(!BalanceStatus.isEmpty()){
        try {
            CryptoManager cryptoManger = new CryptoManager(this);
            BalanceStatus = cryptoManger.decrypt(BalanceStatus);
        } catch (Exception e) {
            BalanceStatus = "show";
        }
    }
    loadBalanceStatus(balance_text, MyBalance);
    visibility_btn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
            final SharedPreferences.Editor editor = prefs.edit();
            String BalanceStatus = prefs.getString("myBalanceStatus","show");
            if(!BalanceStatus.isEmpty()){
                try {
                    CryptoManager cryptoManger = new CryptoManager(credit_bal.this);
                    BalanceStatus = cryptoManger.decrypt(BalanceStatus);
                } catch (Exception e) {
                    BalanceStatus = "show";
                }
            }
            if(BalanceStatus.equalsIgnoreCase("hide")){
                try {
                    CryptoManager cryptoManager = new CryptoManager(credit_bal.this);
                    editor.putString("myBalanceStatus", cryptoManager.encrypt("show"));
                    editor.apply();
                } catch (Exception e) {
                    // Handle exception
                }
            }else{
                try {
                    CryptoManager cryptoManager = new CryptoManager(credit_bal.this);
                    editor.putString("myBalanceStatus", cryptoManager.encrypt("hide"));
                    editor.apply();
                } catch (Exception e) {
                    // Handle exception
                }
            }
            loadBalanceStatus(balance_text, MyBalance);
        }
    });
}
private void loadBalanceStatus(TextView balance_text, String MyBalance){
    SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
    String BalanceStatus = prefs.getString("myBalanceStatus","show");
    if(!BalanceStatus.isEmpty()){
        try {
            CryptoManager cryptoManger = new CryptoManager(this);
            BalanceStatus = cryptoManger.decrypt(BalanceStatus);
        } catch (Exception e) {
            BalanceStatus = "show";
        }
    }
    if(BalanceStatus.equalsIgnoreCase("hide")){
        balance_text.setText("*****");
        bal_visibility.setImageResource(R.drawable.outline_visibility_off_white_48);
    }else{
        balance_text.setText(MyBalance);
        bal_visibility.setImageResource(R.drawable.outline_visibility_white_48);
    }
}
 
 private void changeTransactionTheme(){
    LinearLayout subLayout = findViewById(R.id.transaction_content);
    for (int i = 0; i < subLayout.getChildCount(); i++) {
        View childView = subLayout.getChildAt(i);
        childView.setBackgroundColor(getResources().getColor(i % 2 == 0 ? R.color.dark_blue : R.color.black));
        if(childView instanceof LinearLayout){
            LinearLayout sub_sub_layout = (LinearLayout) childView;
            for (int j = 0; j < sub_sub_layout.getChildCount(); j++) {
                View subChildView = sub_sub_layout.getChildAt(j);
                if(subChildView instanceof TextView){
                    TextView textView = (TextView) subChildView;
                    textView.setTextColor(getResources().getColor(R.color.white));
                }
            }
        }
    }
}
     @Override
     protected void onResume(){
      super.onResume();
     }
 
 
 
}