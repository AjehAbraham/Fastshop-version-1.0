package com.fastshop.myApp;

import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;


public class address extends Activity{
    private Spinner listView_country,listView_state,listView_lga;
    @Override
    protected void onCreate(Bundle saveInstanceState){
     super.onCreate(saveInstanceState);
     setContentView(R.layout.address_activity);
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
     
     TextView open_editor = findViewById(R.id.open_editor);  
     LinearLayout  edit_address_prompt = findViewById(R.id.edit_address_prompt);
     ImageView  close_page_prompt = findViewById(R.id.close_page_prompt);
     
       open_editor.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
       edit_address_prompt.setVisibility(View.VISIBLE);
      }
     });
   close_page_prompt.setColorFilter(Color.GRAY);     
     close_page_prompt.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
       edit_address_prompt.setVisibility(View.GONE);
      }
     });    
     
    LinearLayout add_address_btn = findViewById(R.id.add_address_btn);
     add_address_btn.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
       edit_address_prompt.setVisibility(View.VISIBLE);
      }
     });   
     ImageView add_sign = findViewById(R.id.add_sign);
     add_sign.setColorFilter(Color.GRAY);
     
     add_Adapter();
     
    }
 
 private void add_Adapter() {
    Spinner spinner_country = findViewById(R.id.spinner_country);
    String[] options = getResources().getStringArray(R.array.countries);
    int[] images = {R.drawable.nigeria_flag, R.drawable.ghana_flag, R.drawable.kenya_flag, R.drawable.south_africa_flag, R.drawable.burkina_faso_flag, R.drawable.us_flag, R.drawable.england_flag, R.drawable.france_flag, R.drawable.russian_flag, R.drawable.china_flag, R.drawable.egypt_flag, R.drawable.rwanda_flag};
    CustomAdapter adapter = new CustomAdapter(this, options, images);
    spinner_country.setAdapter(adapter);

    spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            addListState(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Handle the case when nothing is selected
         CustomToast.show(address.this, "Please select a country");
        }
    });
}

private void addListState(int position) {
    String[] NigerianState = getResources().getStringArray(R.array.nigeria_states);
    String[] ghanaRegion = getResources().getStringArray(R.array.ghana_regions);
    String[] SouthAfricaProvience = getResources().getStringArray(R.array.south_africa_provinces); 
    String selectedText = getResources().getStringArray(R.array.countries)[position];
    ArrayAdapter<String> adapter;
    Spinner spinnerState = findViewById(R.id.spinner_state);

    if (selectedText.equalsIgnoreCase("Nigeria")) {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, NigerianState);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(adapter);
    } else if (selectedText.equalsIgnoreCase("Ghana")) {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ghanaRegion);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(adapter);
    }else if(selectedText.equalsIgnoreCase("South Africa")){
       adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SouthAfricaProvience);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(adapter);
    }else {
        spinnerState.setAdapter(null);
        CustomToast.show(this, "Not Available in " + selectedText);
    }
}
 
 

   
 
}