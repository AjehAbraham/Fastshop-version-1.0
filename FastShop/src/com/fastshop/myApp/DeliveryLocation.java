package com.fastshop.myApp;


import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.Manifest;
import android.content.pm.PackageManager;


public class DeliveryLocation{
    private View view;
    private Context context;
    private LinearLayout locationLayout;
    public DeliveryLocation(Context context){
        this.context = context;
    }
    public void LoadUi(FrameLayout container,TextView textView){
     textView.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View view){
      showUi(container);  
     }   
     });   
    }
    private void showUi(FrameLayout frameLayout){
        frameLayout.removeAllViews();
        frameLayout.setVisibility(View.VISIBLE);
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.delivery_location, frameLayout, true);
        
        LoadMethod(frameLayout);
    }
    
  private void loadLocation(LinearLayout layout){
   location_selector selector = new location_selector(context);
   layout.removeAllViews();
   selector.addUi(layout);   
  } 
  private void LoadMethod(FrameLayout container){
      try{  
    Spinner deliverySpinner = view.findViewById(R.id.delivery_type);
    LinearLayout delivery_selector_options = view.findViewById(R.id.delivery_selector_options);
    LinearLayout home_delivery_selector = view.findViewById(R.id.home_delivery_selector);   
    loadLocation(home_delivery_selector);
      
    String[] options = {"","Pickup station", "Home delivery","Warehouse"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, options);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    deliverySpinner.setAdapter(adapter);

    deliverySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         String selectedText = options[position];
            if(selectedText.equalsIgnoreCase("Pickup station")){
            delivery_selector_options.setVisibility(View.VISIBLE); 
           home_delivery_selector.setVisibility(View.GONE);
                
         }else if(selectedText.equalsIgnoreCase("Home delivery")){
           delivery_selector_options.setVisibility(View.GONE);  
            home_delivery_selector.setVisibility(View.VISIBLE);
            
         }else if(selectedText.equalsIgnoreCase("Warehouse")){
          delivery_selector_options.setVisibility(View.GONE);  
          home_delivery_selector.setVisibility(View.GONE);  
             CustomToast.show(context, selectedText + " not avaliable at the moment");
            
         }else{
             CustomToast.show(context, "Please select a valid option");
             delivery_selector_options.setVisibility(View.GONE);
             home_delivery_selector.setVisibility(View.GONE);
             
         }
             
        }
             @Override
        public void onNothingSelected(AdapterView<?> parent) {
            delivery_selector_options.setVisibility(View.GONE);
            CustomToast.show(context, "Please select delivery type");
        }
    });
        
   Button confirm_location = view.findViewById(R.id.confirm_location);    
   confirm_location.setOnClickListener(new View.OnClickListener(){
   @Override
   public void onClick(View view){
    container.setVisibility(View.GONE);   
   }   
   });
    
    addAdapter(); 
  }catch(Exception e){
   CustomToast.show(context, "Delivery: " + e.getMessage());     
 }
  }  
private void addAdapter() {
    Spinner pickupLocationState = view.findViewById(R.id.pickup_location_state);
    String[] options = context.getResources().getStringArray(R.array.nigeria_states);

    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, options);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    pickupLocationState.setAdapter(adapter);

    pickupLocationState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // addListState(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            CustomToast.show(context, "Please select a state");
        }
    });
     TextView textView = view.findViewById(R.id.see_more_delivery_terms);
     TextView textView2 = view.findViewById(R.id.see_more_terms);
     //showTerms(textView,textView2);
    //showTerms(textView,textView2);
    FrameLayout prompt = view.findViewById(R.id.terms_prompt);
    loadTerms(prompt,textView,textView2);
}
  private void loadTerms(FrameLayout container,TextView textView,TextView textView2){
     web_content Webcontent = new web_content(context);
     textView.setOnClickListener(new View.OnClickListener(){
      @Override
       public void onClick(View v) {
          Webcontent.LoadUi(container);
        } 
     });
     textView2.setOnClickListener(new View.OnClickListener(){
      @Override
       public void onClick(View v) {
          Webcontent.LoadUi(container);
        } 
     }); 
  }
 /* private void showTerms( TextView textView_1,TextView textView_2){
   LinearLayout termsContainer = view.findViewById(R.id.delivery_terms);
     ImageView imageView = view.findViewById(R.id.close_terms); 
    imageView.setColorFilter(Color.GRAY,PorterDuff.Mode.SRC_IN);
    /*textView_1.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View view){
     termsContainer.setVisibility(View.VISIBLE);  
     } 
    });
    textView_2.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View view){
     termsContainer.setVisibility(View.VISIBLE);  
     } 
    });
      
    imageView.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View view){
     termsContainer.setVisibility(View.GONE);  
     } 
    });
  }*/

 /*private static final int REQUEST_LOCATION_PERMISSION = 1;  
  private void checkLocationPermission(){
    if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
     requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION); 
    }
  }
    @Override 
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
      if(requestCode == REQUEST_LOCATION_PERMISSION){
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
          //PERMISSION GRANTED//
        }else{
          //FAIL TO GRANT PERMISSION//
        }
      }
    }
  */


}