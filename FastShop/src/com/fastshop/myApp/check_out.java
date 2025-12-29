package com.fastshop.myApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.ActionBar;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.EditText;
import java.util.Random;
import android.Manifest;
import android.content.pm.PackageManager;

public class check_out extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.home_page)); 
          
          LinearLayout header = findViewById(R.id.header);
           ImageView imageView = (ImageView) header.getChildAt(0);
            imageView.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
             imageView.setOnClickListener(new View.OnClickListener(){
              @Override
              public void onClick(View view){
                finish();
              } 
             });
      
     LinearLayout important = findViewById(R.id.important);
      for(int j = 0; j < important.getChildCount(); j++){
        View viewGroup = important.getChildAt(j);
        if(viewGroup instanceof TextView){
          TextView textView = (TextView) viewGroup;
          textView.setSelected(true);
          //CustomToast.show(check_out.this, "found one!");
        }
      } 
      
     LinearLayout semiImportant = findViewById(R.id.semi_important);
      for(int k = 0; k < semiImportant.getChildCount(); k++){
        View view = semiImportant.getChildAt(k);
        if(view instanceof LinearLayout){
          LinearLayout subLayouts = (LinearLayout) view;
          for(int q = 0; q < subLayouts.getChildCount(); q++){
            View child = subLayouts.getChildAt(q);
            if(child instanceof ImageView){
              ImageView childImageView = (ImageView) child;
              childImageView.setColorFilter(getResources().getColor(R.color.deep_green),PorterDuff.Mode.SRC_IN);
              
            }
          } 
        }
      } 
        
      LinearLayout checkout_layout = findViewById(R.id.checkout_layout);
      
     FrameLayout container = findViewById(R.id.prompt);      
      TextView openSelector = findViewById(R.id.open_delivery_prompt);
      try{
      DeliveryLocation deliveryLocation = new DeliveryLocation(this);
      deliveryLocation.LoadUi(container,openSelector);
      }catch(Exception e){
        CustomToast.show(this, e.getMessage());
      }
     /*LinearLayout LocationOptions = findViewById(R.id.options);
     location_selector selector = new location_selector(this);
      selector.addSelector(LocationOptions);
      */
      
      checkIntent();
      viewItems();
    }
  
   private void viewItems(){
  LinearLayout items_container = findViewById(R.id.items_container);
  for(int i = 0; i < items_container.getChildCount(); i++){
   View viewGroup = items_container.getChildAt(i);
   if(viewGroup instanceof LinearLayout){
    LinearLayout layout = (LinearLayout) viewGroup;
    layout.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v){
    Intent intent = new Intent(check_out.this,ItemPreview.class);
    if(layout.getId() != -1){
     int layoutID = layout.getId();
     intent.putExtra("ID", layoutID);
    }else{
     int layoutID = GenerateIntID();
     layout.setId(layoutID);
     intent.putExtra("ID", layoutID);
    }
    startActivity(intent);
     }
    });
   }
  }
 } 
 private int GenerateIntID(){
   return new Random().nextInt(Integer.MAX_VALUE);
 }
  private static final int REQUEST_LOCATION_PERMISSION = 1;  
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
  private void checkIntent(){
     if(getIntent().hasExtra("ID")){
         String data = getIntent().getStringExtra("ID");
         CustomToast.show(this,data);
     }
 }

  
}