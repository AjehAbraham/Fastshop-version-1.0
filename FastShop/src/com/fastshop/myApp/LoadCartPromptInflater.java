package com.fastshop.myApp;


import android.view.View;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.EditText;
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


import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;


public class LoadCartPromptInflater{
    private Context context;
    private ImageView close_cart_prompt,prompt_item_image; 
    private LinearLayout cart_prompt_view_layout;
    private View view;
    //private Callback callBack;
    /*public LoadCartPromptInflater(Context context,Callback callBack){
        this.context = context;
        this.callBack = callBack;
    }
    public interface Callback{
        void onSuccess();
    }*/
    public LoadCartPromptInflater(Context context){
        this.context = context;
    }
   public View LoadUi(FrameLayout container){
    LayoutInflater inflater = LayoutInflater.from(context);
    view = inflater.inflate(R.layout.load_cart_prompt, container, true);
    cart_prompt_view_layout = view.findViewById(R.id.cart_prompt_view_layout);
    close_cart_prompt = view.findViewById(R.id.close_cart_prompt); 
    close_cart_prompt.setColorFilter(context.getResources().getColor(R.color.grey),PorterDuff.Mode.SRC_IN);   
    closePrompt(container,cart_prompt_view_layout,close_cart_prompt);
       
       prompt_item_image= view.findViewById(R.id.prompt_item_image);
        prompt_item_image.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            OpenZoomSlide(prompt_item_image);
            /*
            Intent intent = new Intent(context, ItemPreview.class);
            context.startActivity(intent);*/
        }     
        });
     ImageView zoomBtn = view.findViewById(R.id.zoom_image);
     zoomBtn.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN); 
      previewMethod(); 
       return view; 
    
} 
    
    private void previewMethod(){
    LinearLayout PromptLayout = view.findViewById(R.id.cart_prompt_view_layout);
        try{
        for(int i = 0; i < PromptLayout.getChildCount(); i++){
            View XewView = PromptLayout.getChildAt(i);
            if(XewView instanceof ScrollView){
                ScrollView scrollView = (ScrollView) XewView;
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
                TextView itenName = (TextView) importantLayout.getChildAt(0);
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
               SizeGuide sizeGuide = new SizeGuide(context);
                FrameLayout container_layout = view.findViewById(R.id.container_layout);
                sizeGuide.LoadUi( container_layout, openSizeBtn);
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
                LinearLayout quantityLayout = view.findViewById(R.id.quantity_layout);
                LinearLayout addToCartLayout = view.findViewById(R.id.add_to_cart_layout);
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
            CustomToast.show(context, e.getMessage());
        }           
    }
    
    private void OpenZoomSlide(ImageView imageView){
        try{
        ImageSwipeDetector imageSwipeDetector = new ImageSwipeDetector(context);
        Bitmap[] images = new Bitmap[3];
        Drawable drawable = imageView.getDrawable();
         Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        
       images[0] = bitmap;
       images[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.egypt_flag);
       images[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghana_flag);

       FrameLayout frameLayout = view.findViewById(R.id.frame_layout);
            if(frameLayout.getVisibility() == View.GONE){
                frameLayout.setVisibility(View.VISIBLE); 
            }else{
                frameLayout.setVisibility(View.GONE); 
            }   
       imageSwipeDetector.showImage(frameLayout,frameLayout, images, 0);
        }catch(Exception e){
        CustomToast.show(context, e.getMessage());  
        }
    }

    private void getDrawable(ImageView imageView){
         Drawable drawable = imageView.getDrawable();
         Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
         prompt_item_image.setImageBitmap(bitmap);  
    }
private void getSize(RadioGroup radioGroup){
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId){
            RadioButton radioButton = group.findViewById(checkedId);
            if (radioButton != null) {
                String size = radioButton.getText().toString();
                CustomToast.show(context, size);
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
        CustomToast.show(context, "Quatity exceeded");
    }
    CustomToast.show(context, "You added me");
}    
public void openPrompt(FrameLayout container, TextView openLayoutbtn){
    container.setVisibility(View.VISIBLE);
    container.setScaleY(0f);
    container.animate()
            .scaleY(1f)
            .setDuration(300)
            .setInterpolator(new OvershootInterpolator())
            .start();
}

public void closePrompt(FrameLayout container, LinearLayout cartPrompt, ImageView imageView){
    imageView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            container.animate()
                    .scaleY(0f)
                    .setDuration(300)
                    .setInterpolator(new AccelerateInterpolator())
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            container.setVisibility(View.GONE);
                        }
                    })
                    .start();
        }
    });
}

    
}