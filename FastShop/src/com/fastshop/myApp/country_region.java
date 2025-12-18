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
import android.content.Intent;
import android.widget.ListView;
import android.content.SharedPreferences;
import android.widget.AdapterView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Locale;

public class country_region extends Activity{
    
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_region);
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
       
    EditText editText = findViewById(R.id.editText);
    editText.addTextChangedListener(new TextWatcher(){
    @Override
     public void beforeTextChanged(CharSequence s, int start,int count,int after){}
       @Override
       public void onTextChanged(CharSequence s, int start,int count, int before){}
       @Override
       public void afterTextChanged(Editable s){
         String text = editText.getText().toString(); 
          if(text.length()  <= 0){}else{
           filterList(s.toString());  
          }
       }
    });       
       
       
       
   add_Adapter();
    }
    private void add_Adapter() {
    ListView listView = findViewById(R.id.listView);
    String[] options = getResources().getStringArray(R.array.countries);
    int[] images = {R.drawable.nigeria_flag, R.drawable.ghana_flag, R.drawable.kenya_flag, R.drawable.south_africa_flag, R.drawable.burkina_faso_flag, R.drawable.us_flag, R.drawable.england_flag, R.drawable.france_flag, R.drawable.russian_flag, R.drawable.china_flag, R.drawable.egypt_flag, R.drawable.rwanda_flag};
    CustomAdapter adapter = new CustomAdapter(this, options, images);
    listView.setAdapter(adapter);
    listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    setSelected();
}
   
  private void setSelected() {
    ListView listView = findViewById(R.id.listView);
    String[] options = getResources().getStringArray(R.array.countries);
    String[] codes = getResources().getStringArray(R.array.country_codes);
    String[] languages = getResources().getStringArray(R.array.official_languages);
    String[] currencies = getResources().getStringArray(R.array.currencies);
    SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
    String savedCountry = prefs.getString("data", "");
    String decryptedCountry;
    try {
        CryptoManager manager = new CryptoManager(this);
        decryptedCountry = manager.decrypt(savedCountry);
    } catch (Exception e) {
        decryptedCountry = "Nigeria";
    }
    int position = -1;
    for (int i = 0; i < options.length; i++) {
        if (options[i].equals(decryptedCountry)) {
            position = i;
            break;
        }
    }
    if (position != -1) {
        listView.setSelection(position);
        listView.setItemChecked(position, true);
    }
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selectedCountry = (String) parent.getItemAtPosition(position);
            String selectedLanguage = languages[position];
            String selectedCurrency = currencies[position];
            try {
                CryptoManager manager = new CryptoManager(country_region.this);
                String encryptedCountry = manager.encrypt(selectedCountry);
                String encryptedLanguage = manager.encrypt(selectedLanguage);
                String encryptedCurrency = manager.encrypt(selectedCurrency);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("data", encryptedCountry);
                editor.putString("lang", encryptedLanguage);
                editor.putString("currency", encryptedCurrency);
                editor.apply();
                finish();
            } catch (Exception e) {
                CustomToast.show(country_region.this, e.getMessage());
            }
        }
    });
} 
   
   
  /* private void setSelected() {
    ListView listView = findViewById(R.id.listView);
    String[] options = getResources().getStringArray(R.array.countries);
    String[] codes = getResources().getStringArray(R.array.country_codes);
    String[] languages = getResources().getStringArray(R.array.official_languages);
    String[] currencies = getResources().getStringArray(R.array.currencies);
  
      
    Locale locale = getResources().getConfiguration().locale;
    String defaultCountry = locale.getCountry();
    String defaultLang = locale.getLanguage();     
      
    SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
    String savedCountry = prefs.getString("data", "");
      
    String decryptedCountry;
    try {
       CryptoManager manager = new CryptoManager(this);
        decryptedCountry = manager.decrypt(savedCountry);
    } catch (Exception e) {
        decryptedCountry = "Nigeria";
     //  decryptedCountry = defaultCountry;
    }
      
    int position = -1;
    for (int i = 0; i < options.length; i++) {
        if (options[i].equals(decryptedCountry)) {
            position = i;
            break;
        }
    }
    if (position != -1) {
        listView.setSelection(position);
        listView.setItemChecked(position, true);
    }
      
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selectedCountry = (String) parent.getItemAtPosition(position);
            try {
               CryptoManager manager = new CryptoManager(country_region.this);
                String encryptedCountry = manager.encrypt(selectedCountry);
               
                prefs.edit().putString("data", encryptedCountry).apply();
               finish();
            } catch (Exception e) {
                CustomToast.show(country_region.this, e.getMessage());
            }
        }
    });
}*/
   
   private void filterList(String query) {
    ListView listView = findViewById(R.id.listView);
    CustomAdapter adapter = (CustomAdapter) listView.getAdapter();
    adapter.getFilter().filter(query);
    if (adapter.getCount() == 0) {
        // Handle the case where no items are found
    } else {
        // Handle the case where items are found
    }

}
}