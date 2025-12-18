package com.fastshop.myApp;

import android.view.View;
import android.view.LayoutInflater;
import android.content.Intent;
import android.app.Activity;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.content.Intent;

public class SignUpInflater{
   private Activity activity;
   private View view; 
 public SignUpInflater(Activity activity){
     this.activity = activity;
 }   
    
   public void showLayout(LinearLayout container){
       LayoutInflater inflater = LayoutInflater.from(activity);
       view = inflater.inflate(R.layout.sign_up_inflater, container,true);
       LinearLayout logintBtn = view.findViewById(R.id.loginBtn);
       LinearLayout createBtn = view.findViewById(R.id.createAcctBtn);
       startIntent(logintBtn,createBtn);
   } 
    
  public void startIntent(LinearLayout layout,LinearLayout btn2){
   layout.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View view){
           Intent intent = new Intent(activity, Login.class);
           try{
           activity.startActivity(intent);
           }catch(Exception e){
               CustomToast.show(activity, e.getMessage());
           }
       }
   });
 btn2.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View view){
           Intent intent = new Intent(activity, Create_account.class);
           activity.startActivity(intent);
       }
   });     
      
  }
    
    
}