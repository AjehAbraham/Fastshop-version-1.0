package com.fastshop.myApp;

import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.AdapterView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.graphics.PorterDuff;
import android.content.SharedPreferences;
import android.os.Handler;

public class lang_selector extends Activity {

    private ListView listView;
    private EditText editText;
    private ArrayAdapter<String> adapter;
    private String[] languages;
    private ImageView close_activity;
    //private String SelectedLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lang_selector);


        getActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.home_page));
         
     close_activity = findViewById(R.id.close_activity);  
     close_activity.setColorFilter(getResources().getColor(R.color.black),PorterDuff.Mode.SRC_IN);
     close_activity.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
       finish();
      }        
     });  
        
        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        
        languages = getResources().getStringArray(R.array.official_language);

        
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, languages);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              String  SelectedLanguage = (String) parent.getItemAtPosition(position);
              setSelectedLang(SelectedLanguage);
             
                hideSoftKeyboard();
                finish();
            }
        });
        
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filterList(s.toString());
            }
        });
    }

    private void filterList(String query) {
        adapter.getFilter().filter(query);
        TextView textView = findViewById(R.id.sorting_result);
        if (adapter.getCount() <= 0) {
            textView.setText("No Result found");
        } else {
            textView.setText("");
        }
    }

    private void hideSoftKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
 
 private void setSelectedLang(String SelectedLanguage){
  SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
  try{
    CryptoManager manager = new CryptoManager(this);
    String encryptedLang = manager.encrypt(SelectedLanguage);
   
   SharedPreferences.Editor editor = prefs.edit();
   editor.putString("lang", encryptedLang);
   editor.apply();
   
  }catch(Exception e){
   CustomToast.show(this, "Error occured saving lanaguage");
  }
  
 }
 
 
 
}