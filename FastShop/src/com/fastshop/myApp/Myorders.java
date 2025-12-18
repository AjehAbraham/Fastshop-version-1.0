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
import android.content.Intent;
import android.widget.HorizontalScrollView;
import java.util.Random;


public class Myorders extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_orders);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
         window.setStatusBarColor(getResources().getColor(R.color.home_page));   
        try{ 
        LinearLayout header = findViewById(R.id.header);
       ImageView close_page = (ImageView) header.getChildAt(0);
       close_page.setColorFilter(Color.GRAY);
       close_page.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View v){
        finish();  
       }    
       });     
       ImageView searchBtn = (ImageView) header.getChildAt(2);
       searchBtn.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
        searchBtn.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v){
          Intent intent = new Intent(Myorders.this, SearchOrders.class);
          startActivity(intent);
         }
        });
    }catch(Exception e){
     CustomToast.show(Myorders.this, e.getMessage());
    }
            

     LinearLayout headerLayout = findViewById(R.id.header_categories);
     LoadheaderMethod(headerLayout);
     
          LinearLayout important = findViewById(R.id.important);
      for(int j = 0; j < important.getChildCount(); j++){
        View viewGroup = important.getChildAt(j);
        if(viewGroup instanceof TextView){
          TextView textView = (TextView) viewGroup;
          textView.setSelected(true);
        }
      } 
      
    /* LinearLayout semiImportant = findViewById(R.id.semi_important);
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
      }*/ 
      LoadItemMethod();
    }
 private TextView previouslySelectedTextView;
private void LoadheaderMethod(LinearLayout layout) {
    try {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View layoutView = layout.getChildAt(i);
            if (layoutView instanceof LinearLayout) {
                LinearLayout subLayouts = (LinearLayout) layoutView;
                for (int k = 0; k < subLayouts.getChildCount(); k++) {
                    View child = subLayouts.getChildAt(k);
                    if (child instanceof TextView) {
                        TextView textView = (TextView) child;
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (previouslySelectedTextView != null) {
                                    previouslySelectedTextView.setTextColor(Color.GRAY);
                                    previouslySelectedTextView.setTypeface(null, Typeface.NORMAL);
                                }

                                TextView selectedText = ((TextView) view);
                                selectedText.setTextColor(Color.BLACK);
                                selectedText.setTypeface(null, Typeface.BOLD);
                                previouslySelectedTextView = selectedText;

                                CustomToast.show(Myorders.this, selectedText.getText().toString());
                            }
                        });
                    }
                }
            }
        }
        if (layout.getChildCount() > 0) {
            View layoutView = layout.getChildAt(0);
            if (layoutView instanceof LinearLayout) {
                LinearLayout subLayouts = (LinearLayout) layoutView;
                if (subLayouts.getChildCount() > 0) {
                    View child = subLayouts.getChildAt(0);
                    if (child instanceof TextView) {
                        TextView defaultText = (TextView) child;
                        defaultText.setTextColor(Color.BLACK);
                        defaultText.setTypeface(null, Typeface.BOLD);
                        previouslySelectedTextView = defaultText;
                    }
                }
            }
        }
    } catch (Exception e) {
        CustomToast.show(this, e.getMessage());
    }
}
 private void LoadItemMethod(){
  try{
  LinearLayout parentLayout = findViewById(R.id.parentLayout);
  if(parentLayout.getChildCount() > 0){
   for(int i = 0; i < parentLayout.getChildCount(); i++){
    View view = parentLayout.getChildAt(i);
    if(view instanceof LinearLayout){
     LinearLayout subLayouts = (LinearLayout) view;
     LinearLayout dateLayout = (LinearLayout) subLayouts.getChildAt(0);
     LinearLayout statusLayout = (LinearLayout) subLayouts.getChildAt(1);
     LinearLayout itemLayoutCase = (LinearLayout) subLayouts.getChildAt(2);
     HorizontalScrollView Horizon = (HorizontalScrollView) itemLayoutCase.getChildAt(0);
     LinearLayout itemCase = (LinearLayout) Horizon.getChildAt(0);
     LinearLayout detailsLayout = (LinearLayout) itemLayoutCase.getChildAt(1);
     LinearLayout locationLayout = (LinearLayout) subLayouts.getChildAt(3);
     LinearLayout extraInfo = (LinearLayout) subLayouts.getChildAt(4);
     
     for(int k = 0; k < itemCase.getChildCount(); k++){
      View caseView = itemCase.getChildAt(k);
      if(caseView instanceof LinearLayout){
       LinearLayout Layouts = (LinearLayout) caseView;
       Random random = new Random();
       int id = random.nextInt(8000);  
       Layouts.setId(id);
        Layouts.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v){
          Intent intent = new Intent(Myorders.this, ItemPreview.class);
          intent.putExtra("itemId", id);
          startActivity(intent);
         }
        });
      }
     }
     detailsLayout.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
       Intent intent = new Intent(Myorders.this, ViewOrder.class);
       startActivity(intent);
      }
     });
     if(detailsLayout.getChildCount() > 0){
      View detailsView = detailsLayout.getChildAt(detailsLayout.getChildCount() -1);
      if(detailsView instanceof ImageView){
       ImageView forwardLogo = (ImageView) detailsView;
       forwardLogo.setColorFilter(Color.GRAY,PorterDuff.Mode.SRC_IN);
      }
     }
     
     if (locationLayout.getChildCount() > 0) {
      View lastChild = locationLayout.getChildAt(locationLayout.getChildCount() - 1);
    if (lastChild instanceof TextView) {
        TextView changeBtn = (TextView) lastChild;
        changeBtn.setOnClickListener(new View.OnClickListener(){
         @Override 
         public void onClick(View v){
          CustomToast.show(Myorders.this, "Changing addrs");
         }
        });
       }
      }
     
     if(extraInfo.getChildCount() > 0){
      View extraView = extraInfo.getChildAt(extraInfo.getChildCount() -1);
      if(extraView instanceof TextView){
       TextView buyBtn = (TextView) extraView;
       buyBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
         CustomToast.show(Myorders.this, "Buying again");
        }
       });
      }
     } 
    }else{
     //
    }
    }
   }
  }catch(Exception e){
   CustomToast.show(this, e.getMessage());
  }
  }
 
 
    
}