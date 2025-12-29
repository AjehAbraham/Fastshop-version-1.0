package com.fastshop.myApp;

import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.EditText;
import android.text.InputFilter;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.view.ViewGroup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ItemPreview extends Activity {
    private TextView addRatingBtn;
    private LinearLayout container;
    private TextView size_guide_btn;
    private LinearLayout quantity_frame;
    private FrameLayout frame_layout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_preview);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.home_page)); 
         
        ImageView closePage = findViewById(R.id.close_item_preview); 
         closePage.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
         
       closePage.setOnClickListener(new View.OnClickListener(){
           @Override 
           public void onClick(View view){
            finish();
           }
       });  
     
     LinearLayout previewContainer = findViewById(R.id.rating_container_layout);
      ReviewInflater Rflater = new ReviewInflater(this);
      Rflater.LoadUi(previewContainer);
     
       frame_layout = findViewById(R.id.frame_layout);
     
     AddRatingInflater addRatinginflater = new AddRatingInflater(this);
     addRatingBtn = findViewById(R.id.addRatingBtn);
     addRatinginflater.LoadUi(frame_layout,addRatingBtn);
     
     try{
      if(getIntent() != null && getIntent().hasExtra("itemId")){
      int ItemId = getIntent().getIntExtra("itemId", -1);
       CustomToast.show(this, String.valueOf(ItemId));
      }
     }catch(Exception e){
      CustomToast.show(this, e.getMessage());
     }
     
     previewMethod();
     checkIntent();
    }
 
 private ImageView item_image;
private void previewMethod(){
   item_image = findViewById(R.id.item_image);
            item_image.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
         OpenZoomSlide(item_image);
        }     
        });
 
    LinearLayout PromptLayout = findViewById(R.id.cart_container_layout);
        try{
        for(int i = 0; i < PromptLayout.getChildCount(); i++){
            View XewView = PromptLayout.getChildAt(i);
            if(XewView instanceof ScrollView){
                ScrollView scrollView = (ScrollView) XewView;
                scrollToTop(scrollView);
             
                LinearLayout parentLayout = (LinearLayout) scrollView.getChildAt(0);
                LinearLayout imageLayout = (LinearLayout) parentLayout.getChildAt(0);
                ImageView itemImage = (ImageView) imageLayout.getChildAt(0);
                ImageView zoomBtn = (ImageView) imageLayout.getChildAt(1);
                zoomBtn.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
                zoomBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                      OpenZoomSlide(itemImage);  
                    }
                });
                LinearLayout importantLayout = (LinearLayout) parentLayout.getChildAt(1);
                TextView itemName = (TextView) importantLayout.getChildAt(0);
                TextView discountPercent = (TextView) importantLayout.getChildAt(1);
                TextView oldPrice = (TextView) importantLayout.getChildAt(2);
                TextView price = (TextView) importantLayout.getChildAt(3);
                
                oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                
                LinearLayout noteLayout = (LinearLayout) parentLayout.getChildAt(2);
                LinearLayout colorLayout = (LinearLayout) parentLayout.getChildAt(3);
                HorizontalScrollView colorPickerLayout = (HorizontalScrollView) colorLayout.getChildAt(1);
                LinearLayout ColorCase = (LinearLayout)colorPickerLayout.getChildAt(0);
                if(ColorCase.getChildCount() > 0){
                    for(int k = 0; k < ColorCase.getChildCount(); k++){
                     View colorView = ColorCase.getChildAt(k);
                        if(colorView instanceof ImageView){
                            ImageView imageView = (ImageView) colorView;
                            imageView.setOnClickListener(new View.OnClickListener(){
                             @Override
                             public void onClick(View view){
                                 for(int j = 0; j < ColorCase.getChildCount(); j++){
                                     View child = ColorCase.getChildAt(j);
                                     if(child instanceof ImageView){
                                         ImageView iV = (ImageView) child;
                                         iV.setBackgroundResource(0);
                                     }
                                     view.setBackgroundResource(R.drawable.size_display_selected);
                                     getDrawable((ImageView)view);
                                 }  
                             } 
                           }); 
                        }  
                    }
                }
              LinearLayout SizeLayout = (LinearLayout) parentLayout.getChildAt(4);
              LinearLayout sizeTitleLayout = (LinearLayout) SizeLayout.getChildAt(0);
              TextView openSizeBtn = (TextView) sizeTitleLayout.getChildAt(1);
              SizeGuide sizeGuide = new SizeGuide(this);
              sizeGuide.LoadUi(frame_layout,openSizeBtn);
     
                for(int p = 0; p < SizeLayout.getChildCount(); p++){
                 View sizeView = SizeLayout.getChildAt(p);
                    if(sizeView instanceof HorizontalScrollView){
                        HorizontalScrollView sizeCase = (HorizontalScrollView) sizeView;
                        RadioGroup radioGroup = (RadioGroup) sizeCase.getChildAt(0);
                        if(radioGroup.getChildCount() > 0){
                            getSize(radioGroup);
                            for(int x = 0; x < radioGroup.getChildCount(); x++){
                             View radioView = radioGroup.getChildAt(x);
                                if(radioView instanceof RadioButton){
                                    RadioButton radioButton = (RadioButton) radioView;
                                }  
                            }
                        }
                    }   
                } 
            }else{
                LinearLayout quantityLayout = findViewById(R.id.quantity_layout);
                LinearLayout addToCartLayout = findViewById(R.id.add_to_cart_layout);
                ImageView minusImage = (ImageView) quantityLayout.getChildAt(3);
                EditText editText = (EditText) quantityLayout.getChildAt(2);
                ImageView plusImage = (ImageView) quantityLayout.getChildAt(1);
                minusImage.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
                plusImage.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
                increaseQuantity(editText,minusImage,plusImage);
                
                addToCartLayout.setOnClickListener(new View.OnClickListener(){
                  @Override
                  public void onClick(View v){
                      AddToCartMethod(editText);
                  }  
                });
            }
        }
        }catch(Exception e){
            CustomToast.show(this, e.getMessage());
        }           
    }
    
    private void OpenZoomSlide(ImageView imageView){
        try{
        ImageSwipeDetector imageSwipeDetector = new ImageSwipeDetector(this);
        Bitmap[] images = new Bitmap[3];
        Drawable drawable = imageView.getDrawable();
         Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        
       images[0] = bitmap;
       images[1] = BitmapFactory.decodeResource(getResources(), R.drawable.egypt_flag);
       images[2] = BitmapFactory.decodeResource(getResources(), R.drawable.ghana_flag);

       FrameLayout frameLayout = findViewById(R.id.frame_layout);
            if(frameLayout.getVisibility() == View.GONE){
                frameLayout.setVisibility(View.VISIBLE); 
            }else{
                frameLayout.setVisibility(View.GONE); 
            }   
       imageSwipeDetector.showImage(frameLayout,frameLayout, images, 0);
        }catch(Exception e){
        CustomToast.show(this, e.getMessage());  
        }
    }

    private void getDrawable(ImageView imageView){
         Drawable drawable = imageView.getDrawable();
         Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
         item_image.setImageBitmap(bitmap);  
    }
private void getSize(RadioGroup radioGroup){
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId){
            RadioButton radioButton = group.findViewById(checkedId);
            if (radioButton != null) {
                String size = radioButton.getText().toString();
                CustomToast.show(ItemPreview.this, size);
            }
        }
    });
}
 private void increaseQuantity(EditText editText, ImageView minus, ImageView plus){
    minus.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            String value = editText.getText().toString();
            int number = Integer.parseInt(value);
            if(number > 0){
                int newValue = number - 1;
                editText.setText(String.valueOf(newValue));
            }
        }
    });

    plus.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            String value = editText.getText().toString();
            int number = Integer.parseInt(value);
            if(number < 100){
                int newValue = number + 1;
                editText.setText(String.valueOf(newValue));
            }
        }
    });
}
private void AddToCartMethod(EditText editText){
    String value = editText.getText().toString();
    int quantity = Integer.parseInt(value);
    if(quantity >= 0 && quantity < 100){
        int Number = quantity + 1; 
    editText.setText(String.valueOf(Number));   
    }else if(quantity >= 100){
        CustomToast.show(this, "Quatity exceeded");
    }
    CustomToast.show(this, "You added me");
}   
 private void checkIntent(){
     if(getIntent().hasExtra("ID")){
         String data = getIntent().getStringExtra("ID");
         CustomToast.show(this,data);
     }
 }
 public void scrollToTop(ScrollView scrollView) {
    LinearLayout layout = findViewById(R.id.back_to_top_container);
     layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(View.FOCUS_UP);
                }
            });
        }
    });
  
}
}