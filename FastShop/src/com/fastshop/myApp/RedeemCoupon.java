package com.fastshop.myApp;

import android.view.View;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.content.Context;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.inputmethod.InputMethodManager;


public class RedeemCoupon{
    private Context context;
    private View view;
    public RedeemCoupon(Context context){
        this.context = context;
    }
    public void LoadUi(LinearLayout container){
     LayoutInflater flater =  LayoutInflater.from(context);
        container.removeAllViews();
        container.setVisibility(View.VISIBLE);
     view = flater.inflate(R.layout.redeem_coupon, container, true); 
     loadMethod();  
    }
    private void loadMethod(){
     EditText editText = view.findViewById(R.id.editText);
        editText.requestFocus();
     Button button = view.findViewById(R.id.submit); 
     button.setEnabled(false);
       
        editText.addTextChangedListener(new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence s,int start, int count, int after){}
         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count){} 
            @Override
            public void afterTextChanged(Editable s){
             String text = editText.getText().toString();
                if(text != null && text.length() >= 5){
                 button.setEnabled(true);
                 button.setBackgroundResource(R.drawable.cornered_default); 
                // sendRequest(text);   
                }else{
                    button.setEnabled(false);
                    button.setBackgroundResource(R.drawable.cornered_lighter_default);
                }
            }
        });
       
        button.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String inputText = editText.getText().toString();
            if(inputText != null && inputText.length() >= 5){
              sendRequest(inputText); 
            }else{
                CustomToast.show(context, "Incomplete coupon code");
            }
        }   
        });
    }
    private void sendRequest(String text){
            InputMethodManager imm = (InputMethodManager) context.getSystemService (Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        CustomToast.show(context, "Redeeming " + text);
    }
}