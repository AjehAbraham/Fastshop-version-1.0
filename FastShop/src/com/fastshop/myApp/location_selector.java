package com.fastshop.myApp;


import android.view.View;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.FrameLayout;
import android.widget.Button;

public class location_selector{
    private Context context;
    private View view;
    private LinearLayout container;
    public  location_selector(Context context){
        this.context = context;
    }
    
    public void addUi(LinearLayout container){
        try{
      container.removeAllViews();    
      LayoutInflater inflater =  LayoutInflater.from(context);
      view = inflater.inflate(R.layout.location_selector,container, true);
      container.setVisibility(View.VISIBLE);  
      toggleRadio();
      permissionMethod();      
        }catch(Exception e){
            CustomToast.show(context, "location " + e.getMessage());
        }            
    }
    
private void toggleRadio(){
        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
       LinearLayout saved_location_layout = view.findViewById(R.id.saved_location_layout);
       LinearLayout new_location_layout = view.findViewById(R.id.new_location_layout);
    
radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.saved_location:
                // Saved location is selected
            saved_location_layout.setVisibility(View.VISIBLE);
            new_location_layout.setVisibility(View.GONE);
                break;
            case R.id.new_location:
                // New location is selected
            saved_location_layout.setVisibility(View.GONE);
            new_location_layout.setVisibility(View.VISIBLE);
                break;
        }
    }
});
}
    private void permissionMethod(){
    Button live_location = view.findViewById(R.id.live_location); 
      live_location.setOnClickListener(new View.OnClickListener(){
   @Override
   public void onClick(View view){
    //checkLocationPermission();  
   }   
   }); 
           
    }

/*private Activity activity;
private static final int REQUEST_LOCATION_PERMISSION = 1;

private void checkLocationPermission(){
    if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
        activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
    }
}
 */ 

}