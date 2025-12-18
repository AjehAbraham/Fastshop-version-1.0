package com.fastshop.myApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.widget.TextView;
import android.content.Intent;
import android.app.Activity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.graphics.Color;
import android.content.SharedPreferences;
import org.json.JSONObject;
import org.json.JSONArray;

public class navigation_bar_inflater{
    private static View layout;
    private static Context context;
    private static Activity activity;
    public static void navigation_bar_inflater(Context context){
        navigation_bar_inflater.context = context;
    }

    public static void setActivity(Activity activity){
        navigation_bar_inflater.activity = activity;
    }

    public static View Navigators(Context context){
        navigation_bar_inflater.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        layout = inflater.inflate(R.layout.navigation_bar,null);
       // fetch_cart_pref();
        LoadFuctions(); 
        return layout;
    }
   private static void LoadFuctions(){
    LinearLayout Layout = layout.findViewById(R.id.navigatior_bar_container);
       for(int i = 0; i < Layout.getChildCount(); i++){
      View view = Layout.getChildAt(i);  
           if(view instanceof LinearLayout){
         LinearLayout subLayout = (LinearLayout) view;
               for(int j = 0; j < subLayout.getChildCount(); j++){
                   View childView = subLayout.getChildAt(j);
                   if(childView instanceof ImageView){
                       ImageView imageView = (ImageView) childView;
                       imageView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN); 
                       final int position = i;
                       imageView.setOnClickListener(new View.OnClickListener(){
                          @Override
                          public void onClick(View view){
                              playAnimation(imageView);
                              startIntent(position);
                          }
                      }); 
                   }else if(childView instanceof TextView){
                       TextView textView = (TextView) childView;
                      Typeface typeFace = Typeface.createFromAsset(context.getAssets(),"fonts/Poppins-Regular.ttf");
                      textView.setTypeface(typeFace);
                   }
               }      
           }else if(view instanceof ImageView){
               ImageView imageView = (ImageView) view;
               imageView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
               final int position = i;
               imageView.setOnClickListener(new View.OnClickListener(){
                   @Override
                   public void onClick(View view){
                       playAnimation(imageView);
                       startIntent(position);
                   }
               });
           }else if(view instanceof TextView){
               TextView textView = (TextView) view;
             Typeface typeFace = Typeface.createFromAsset(context.getAssets(),"fonts/Poppins-Regular.ttf");
             textView.setTypeface(typeFace);
           }     
           
       }   
       
   }
 
  private static void startIntent(int position){
   Intent intent = null;
      if(position == 0){
          intent = new Intent(context, MainActivity.class);
      }else if(position == 1){
          intent = new Intent(context, search_categories.class);
      }else if(position == 2){
          intent = new Intent(context, quick_action.class);
      }else if(position == 4){
          intent = new Intent(context, cart_items.class);
      }
      if(intent != null){
          context.startActivity(intent);
      }
  }
    public static void changeAllTheme(){
    LinearLayout Layout = layout.findViewById(R.id.navigatior_bar_container);
        for(int i = 0; i < Layout.getChildCount(); i++){
         View view = Layout.getChildAt(i);
            if(view instanceof LinearLayout){
                LinearLayout subLayout = (LinearLayout) view;
                for(int j = 0; j < subLayout.getChildCount(); j++){
                    View chidView = subLayout.getChildAt(j);
                    if(chidView instanceof ImageView){
                        ImageView imageView = (ImageView) chidView;
                        imageView.setColorFilter(Color.BLACK);
                        imageView.setBackground(null);
                    }else if(chidView instanceof TextView){
                        TextView textView = (TextView) chidView;
                        textView.setTextColor(Color.BLACK);
                    }
                }
            }   
        }
    }
    public static void ChangeFirst(){
      LinearLayout Layout = layout.findViewById(R.id.navigatior_bar_container);
      LinearLayout subLayout =(LinearLayout) Layout.getChildAt(0);
      ImageView imageView = (ImageView) subLayout.getChildAt(0); 
      TextView textView = (TextView) subLayout.getChildAt(1);   
      imageView.setColorFilter(Color.WHITE);
      imageView.setBackgroundResource(R.drawable.circular_background);
      textView.setTextColor(context.getResources().getColor(R.color.home_page));    
    }
    
    public static void ChangeSecond(){
      LinearLayout Layout = layout.findViewById(R.id.navigatior_bar_container);
      LinearLayout subLayout =(LinearLayout) Layout.getChildAt(1);
      ImageView imageView = (ImageView) subLayout.getChildAt(0); 
      TextView textView = (TextView) subLayout.getChildAt(1);   
      imageView.setColorFilter(Color.WHITE);
      imageView.setBackgroundResource(R.drawable.circular_background);
      textView.setTextColor(context.getResources().getColor(R.color.home_page));    
    }
   public static void ChangeThird(){
      LinearLayout Layout = layout.findViewById(R.id.navigatior_bar_container);
      LinearLayout subLayout =(LinearLayout) Layout.getChildAt(2);
      ImageView imageView = (ImageView) subLayout.getChildAt(0); 
      TextView textView = (TextView) subLayout.getChildAt(1);   
      imageView.setColorFilter(Color.WHITE);
      imageView.setBackgroundResource(R.drawable.circular_background);
      textView.setTextColor(context.getResources().getColor(R.color.home_page));    
    }
    public static void ChangeFourth(){
      LinearLayout Layout = layout.findViewById(R.id.navigatior_bar_container);
      LinearLayout subLayout =(LinearLayout) Layout.getChildAt(4);
      ImageView imageView = (ImageView) subLayout.getChildAt(0); 
      TextView textView = (TextView) subLayout.getChildAt(1);   
      imageView.setColorFilter(Color.WHITE);
      imageView.setBackgroundResource(R.drawable.circular_background);
      textView.setTextColor(context.getResources().getColor(R.color.home_page));    
    }

  private static void playAnimation(ImageView imageView){
           imageView.setBackgroundResource(R.drawable.navigator_splash_anim);
            Animation animation = new AlphaAnimation(0, 1);
            animation.setInterpolator(new AccelerateDecelerateInterpolator());
            animation.setDuration(1000);
            imageView.startAnimation(animation);
     }
 
    
    private static int count = 0;
    private static void fetch_cart_pref(){
   /* LoadCart_inflater loadCartInflater = new LoadCart_inflater(context, new LoadCart_inflater.CartCallback() {
      @Override
    public void onCartUpdated(int totalItems) {
        count = totalItems;
     //   Log.d("Cart Count", "Total Items: " + totalItems);
        CustomToast.show(context, " " + count);
            LinearLayout imageLayout = layout.findViewById(R.id.imageLayout);
            View view = imageLayout.getChildAt(5);
            if(view instanceof TextView){
                TextView textView = (TextView) view;
                if(count <=0){
                    textView.setText("empty");
                    textView.setBackgroundResource(R.drawable.default_resources);
                    textView.setTextColor(Color.BLACK);
                }else{
                    textView.setText(String.valueOf(count));
                    textView.setBackgroundResource(R.drawable.circular_background);
                    textView.setTextColor(Color.WHITE);
                }
            }
    }
});*/
        
        
        
        
       /* try{
            SharedPreferences prefs = context.getSharedPreferences("cart_data",Context.MODE_PRIVATE);
            String cart_items = prefs.getString("cart_items","[]");
            try{
                if(!cart_items.isEmpty() || !cart_items.equals("")){
                    CryptoManager cryptoManager = new CryptoManager(context);
                    String decryptedData = cryptoManager.decrypt(cart_items);
                    JSONArray array = new JSONArray(decryptedData);
                    count = array.length();
                }
            }catch(Exception e){
                CustomToast.show(context, e.getMessage());
            }
            CustomToast.show(context, " " + count);
            LinearLayout imageLayout = layout.findViewById(R.id.imageLayout);
            View view = imageLayout.getChildAt(5);
            if(view instanceof TextView){
                TextView textView = (TextView) view;
                if(count <=0){
                    textView.setText("empty");
                    textView.setBackgroundResource(R.drawable.default_resources);
                    textView.setTextColor(Color.BLACK);
                }else{
                    textView.setText(String.valueOf(count));
                    textView.setBackgroundResource(R.drawable.circular_background);
                    textView.setTextColor(Color.WHITE);
                }
            }
        }catch(Exception e) {
            CustomToast.show(context, "Unlikely " + e.getMessage());
        }*/
    }
}



     
     
  