package com.fastshop.myApp;

import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.EditText;

public class CancelOrder {
    private View view;
    private Context context;
    public CancelOrder(Context context){
        this.context = context;
    }
    public void LoadUi(FrameLayout container,TextView textView){
      textView.setOnClickListener(new View.OnClickListener(){
       @Override 
       public void onClick(View v){
         AddUi(container); 
       }   
      });  
    }
    private void AddUi(FrameLayout container){
     LayoutInflater inflater = LayoutInflater.from(context);
      container.removeAllViews();
      container.setVisibility(View.VISIBLE);   
      view = inflater.inflate(R.layout.cancel_order, container,true); 
     LoadMethods(container);   
    }
    private void LoadMethods(FrameLayout container){
    LinearLayout layout = view.findViewById(R.id.prompt_container); 
    ImageView closePrompt = (ImageView) layout.getChildAt(0);
    closePrompt.setColorFilter(Color.GRAY,PorterDuff.Mode.SRC_IN);    
     closePrompt.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v){
          container.bringToFront();
          container.invalidate(); 
          container.removeAllViews();
          container.setVisibility(View.GONE);    
         }
     });
        Spinner spinner = (Spinner) layout.getChildAt(3);
        EditText editText = (EditText) layout.getChildAt(4);
        
       String[] array = context.getResources().getStringArray(R.array.cancel_order_reasons);
       ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, array);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedText = parent.getItemAtPosition(position).toString();
        if (selectedText.equalsIgnoreCase("Others")) {
            editText.setVisibility(View.VISIBLE);
        } else {
            editText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Handle nothing selected
    }
});
    }
}