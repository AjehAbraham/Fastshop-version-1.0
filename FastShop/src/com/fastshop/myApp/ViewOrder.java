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
import android.widget.FrameLayout;
import java.util.UUID;
import java.util.Random;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.view.ViewGroup;

public class ViewOrder extends Activity{
    private String TrackingID;
    private LinearLayout report_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_orders);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
         window.setStatusBarColor(getResources().getColor(R.color.home_page));
         
      LinearLayout header = findViewById(R.id.header);
       ImageView close_page = (ImageView) header.getChildAt(0);
       close_page.setColorFilter(Color.GRAY);
       close_page.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View v){
        finish();  
       }    
       });

     LinearLayout important = findViewById(R.id.important);
      for(int j = 0; j < important.getChildCount(); j++){
        View viewGroup = important.getChildAt(j);
        if(viewGroup instanceof TextView){
          TextView textView = (TextView) viewGroup;
          textView.setSelected(true);
        }
      } 
     
    LinearLayout less_important = findViewById(R.id.less_important);
      for(int k = 0; k < less_important.getChildCount(); k++){
        View viewGroup = less_important.getChildAt(k);
        if(viewGroup instanceof TextView){
          TextView textView = (TextView) viewGroup;
          textView.setSelected(true);
        }
      } 
     
     LinearLayout parentLayout = findViewById(R.id.parentLayout);
      LinearLayout tracking_layout = findViewById(R.id.tracking_layout);
      TextView trackTextView = (TextView) tracking_layout.getChildAt(1);
      TrackingID = GenerateID(); 
      trackTextView.setText(TrackingID);
      ImageView copyBtn = (ImageView) tracking_layout.getChildAt(2);
      
      copyBtn.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View v){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied",TrackingID);
        clipboardManager.setPrimaryClip(clip);
        CustomToast.show(ViewOrder.this, "Tracking number copied to clipboard");
       }
      });
     
     
      TextView textView = findViewById(R.id.cancel_order);
     
      FrameLayout container = findViewById(R.id.container);
      CancelOrder cancelOrderFlater = new CancelOrder(this);
      cancelOrderFlater.LoadUi(container, textView);
      
     TextView track_order = findViewById(R.id.track_order);
     String track_type = "Track_order";
     startWebView(track_order,track_type,TrackingID);
     
     TextView change_location = findViewById(R.id.change_location);
     String location_type = "change_location";
     startWebView(change_location,location_type,TrackingID);
     
     TextView buy_again = findViewById(R.id.buy_again);
     buy_again.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
       Intent intent = new Intent(ViewOrder.this, check_out.class);
       intent.putExtra("ID", TrackingID);
       startActivity(intent);
      }
     });

     
     Report();
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
    Intent intent = new Intent(ViewOrder.this,ItemPreview.class);
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
   private void Report(){
    try{
    FrameLayout container = findViewById(R.id.container);
    report_layout = findViewById(R.id.report_layout);
    ImageView imageView = (ImageView) report_layout.getChildAt(1);
    imageView.setColorFilter(getResources().getColor(R.color.red));
    report_layout.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v){
      webContent(container);
     }
    });
    }catch(Exception e){
     CustomToast.show(this, e.getMessage());
    }
   }
    private String GenerateID(){
       return UUID.randomUUID().toString().replace("-","").substring(0,12);
      }
 private int GenerateIntID(){
    return new Random().nextInt(Integer.MAX_VALUE);
}
 private void LoadMethod(){
  
 }
 private void startWebView(TextView textView,String type,String data){
  textView.setOnClickListener(new View.OnClickListener(){
   @Override
   public void onClick(View v){
    FrameLayout container = findViewById(R.id.container);
    webContent(container);
   }
  });
 }
 private void webContent(FrameLayout container){
  web_content Webcontent = new web_content(this);
  Webcontent.LoadUi(container);
 }
 
}