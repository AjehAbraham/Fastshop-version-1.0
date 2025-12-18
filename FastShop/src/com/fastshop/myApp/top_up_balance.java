package com.fastshop.myApp;

import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.text.DecimalFormat;

public class top_up_balance extends Activity{
    private ImageView delete_card,close_page;
    private LinearLayout top_up_prompt,open_prompt;
    private ImageView close_top_up_btn;
    private LinearLayout delete_card_prompt;
    private Button delete_false,delete_true;
    private EditText Amount;
    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
     setContentView(R.layout.top_up_balance);
     ActionBar actionBar = getActionBar();
     actionBar.hide();
     Window window = getWindow();
     window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
     window.setStatusBarColor(getResources().getColor(R.color.home_page));    
         
      close_page = findViewById(R.id.close_page);   
      close_page.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
         
     close_page.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
      finish();    
      }  
     });  
        
      delete_card = findViewById(R.id.delete_card);   
      delete_card.setColorFilter(Color.GRAY,PorterDuff.Mode.SRC_IN); 
     
     delete_card.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
       delete_card_prompt.setVisibility(View.VISIBLE);
      }  
     });      
        
    top_up_prompt   = findViewById(R.id.top_up_prompt);  
    close_top_up_btn   = findViewById(R.id.close_top_up_btn);      
    open_prompt  = findViewById(R.id.open_prompt); 
     
        
     open_prompt.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
      top_up_prompt.setVisibility(View.VISIBLE);  
      }  
     });        
     
     close_top_up_btn.setColorFilter(Color.GRAY,PorterDuff.Mode.SRC_IN);    
    close_top_up_btn.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
      top_up_prompt.setVisibility(View.GONE);  
      }  
     });      
        
     delete_card_prompt = findViewById(R.id.delete_card_prompt);
     delete_false = findViewById(R.id.delete_false);
     delete_true  = findViewById(R.id.delete_true);
     delete_false.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
      delete_card_prompt.setVisibility(View.GONE);  
      }  
     }); 
     delete_true.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
      delete_card_prompt.setVisibility(View.GONE);
       CustomToast.show(top_up_balance.this, "Deleting...");
      }  
     });  
     
       Amount = findViewById(R.id.amount);
        amountFormater(Amount);
    }
 
private void amountFormater(EditText amount){
    amount.addTextChangedListener(new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence s, int start,int count,int after){}
        
        @Override
        public void onTextChanged(CharSequence s, int start,int before,int count){}
        
        @Override
        public void afterTextChanged(Editable s){
            try {
                String plainText = s.toString().replaceAll("[₦,]", "");
                if (plainText.isEmpty()) {
                    plainText = "0";
                }
                DecimalFormat formatter = new DecimalFormat("#,###");
                String formattedString = "₦" + formatter.format(Long.parseLong(plainText));
                if (!s.toString().equals(formattedString)) {
                    amount.removeTextChangedListener(this);
                    amount.setText(formattedString);
                    amount.setSelection(formattedString.length());
                    amount.addTextChangedListener(this);
                }
            } catch (NumberFormatException e) {
                CustomToast.show(top_up_balance.this, "Unknown error occured,please try again");
            }
        }
    });
}
 
}