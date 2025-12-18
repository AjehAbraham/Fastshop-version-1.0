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
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Spinner;
import android.content.Intent;

public class Create_account extends Activity{
      private RadioGroup  radioGroup;
      private RadioButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     setContentView(R.layout.create_account);   
     ActionBar actionBar = getActionBar();
      actionBar.hide();
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);     
      window.setStatusBarColor(getResources().getColor(R.color.home_page));  
       
      radioGroup = findViewById(R.id.radio_group);
       
     LinearLayout layout1 = findViewById(R.id.create_account_email);
     LinearLayout layout2 =  findViewById(R.id.create_account_tel);
     loadToggle(radioGroup,layout1,layout2);
     
     TextView bckToLogin = findViewById(R.id.backTologinBtn);
     bckToLogin.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view){
       Intent intent = new Intent(Create_account.this, Login.class);
       startActivity(intent);
      }
     });
     LoadAdapter();
    }
 
 private void loadToggle(RadioGroup radioGroup,LinearLayout loginEmail,LinearLayout loginTel){
  radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
   @Override
   public void onCheckedChanged(RadioGroup group,int checkedId){
    if(checkedId == R.id.email_selector){
     loginEmail.setVisibility(View.VISIBLE);
     loginTel.setVisibility(View.GONE);
    }else if(checkedId == R.id.tel_selector){
     loginTel.setVisibility(View.VISIBLE);
     loginEmail.setVisibility(View.GONE);
    }
   }
  });
  
 }
 
 
 private void LoadAdapter(){
  Spinner spinner = findViewById(R.id.tel_codes);
  String[]  tel_codes = getResources().getStringArray(R.array.tel_codes);
   int[] images = {R.drawable.nigeria_flag, R.drawable.ghana_flag, R.drawable.kenya_flag, R.drawable.south_africa_flag, R.drawable.burkina_faso_flag, R.drawable.us_flag, R.drawable.england_flag, R.drawable.france_flag, R.drawable.russian_flag, R.drawable.china_flag, R.drawable.egypt_flag, R.drawable.rwanda_flag};
    CustomAdapter adapter = new CustomAdapter(this, tel_codes, images);
    spinner.setAdapter(adapter);  
 }
 
 private void checkRadio(RadioGroup radioGroup){
  radioGroup.clearCheck();
  int id = R.id.email_selector;
  radioGroup.check(id);
 }
     @Override
    public void onBackPressed(){
     super.onBackPressed();
     Intent intent = new Intent(this, MainActivity.class);
     startActivity(intent);
    }
 
 @Override
 protected void onResume(){
  super.onResume();
  checkRadio(radioGroup);
 }
 
 
}