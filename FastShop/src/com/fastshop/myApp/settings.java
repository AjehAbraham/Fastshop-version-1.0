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
import android.view.ViewGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.FrameLayout;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class settings extends Activity{
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.home_page)); 
            
            
        LinearLayout header = findViewById(R.id.header);
         View view = header.getChildAt(0);
            if(view instanceof ImageView){
                ImageView imageView = (ImageView) view;
                 imageView.setColorFilter(Color.BLACK);
                    imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               finish(); 
            }    
            });
            }
       
   FrameLayout sign_out_prompt = findViewById(R.id.sign_out_prompt);
   TextView logout_false = findViewById(R.id.logout_false);   
    TextView logout_true = findViewById(R.id.logout_true);
   LinearLayout logout = findViewById(R.id.logout);
    ImageView close_sign_out_prompt = findViewById(R.id.close_sign_out_prompt);
     close_sign_out_prompt.setColorFilter(Color.BLACK);
       
     logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               sign_out_prompt.setVisibility(View.VISIBLE);
               sign_out_prompt.bringToFront();
               sign_out_prompt.invalidate();
               CustomToast.show(settings.this, "Click!");
            }    
            });     
        close_sign_out_prompt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               sign_out_prompt.setVisibility(View.GONE);
            }    
            }); 

     logout_false.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               sign_out_prompt.setVisibility(View.GONE);
            }    
            });     
       
        logout_true.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               CustomToast.show(settings.this, "Logging out");
               sign_out_prompt.setVisibility(View.GONE);
            }    
            });     
                          
       
       
       loadLayout();     
        addStyle();    
    }
   private void addStyle(){
 LinearLayout parentLayout = findViewById(R.id.parentLayout);
     parentStyle(parentLayout);
   }
  private void parentStyle(ViewGroup Layout){
       for(int i = 0; i < Layout.getChildCount(); i++){
          View view = Layout.getChildAt(i);
          if(view instanceof ImageView){
             ImageView image = (ImageView) view;
             image.setColorFilter(Color.BLACK);
          }else if(view instanceof LinearLayout){
             int index = i/2;
             LinearLayout newLayout = (LinearLayout) view;
          newLayout.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v) {
             if(index == 0){
             Intent intent = new Intent(settings.this, account_security.class);
              startActivity(intent); 
             }else if(index == 1){
                Intent intent = new Intent(settings.this, country_region.class);
                startActivity(intent);
             }else if(index == 2){
             Intent intent = new Intent(settings.this, lang_selector.class);
             startActivity(intent);
             }else if(index == 3){
                Intent intent = new Intent(settings.this, currency_selector.class);
                startActivity(intent);
             
             }else if(index == 4){
                Intent intent = new Intent(settings.this, about.class);
                 startActivity(intent);
             
             }else if(index == 5){
               openLogout(newLayout); 
             }
          }  
          });
            parentStyle(newLayout); 
          }
       }  
          
  }    
   private void openLogout(LinearLayout logout){
    FrameLayout sign_out_prompt = findViewById(R.id.sign_out_prompt);
           logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               sign_out_prompt.setVisibility(View.VISIBLE);
        }    
            }); 
   }
   
 private void loadLayout(){
    LinearLayout parent_layout = findViewById(R.id.parentLayout);
    loadLayoutRecursive(parent_layout);
}

private void loadLayoutRecursive(ViewGroup layout) {
    for (int i = 0; i < layout.getChildCount(); i++) {
        View view = layout.getChildAt(i);
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        } else if (view instanceof LinearLayout) {
            LinearLayout newLayout = (LinearLayout) view;
            newLayout.setElevation(20);
            //loadLayoutRecursive(newLayout);
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Poppins-Medium.ttf");
            textView.setTypeface(typeface);
        }
    }
}
   private void setData(){
    String[] countries = getResources().getStringArray(R.array.countries);
    String[] codes = getResources().getStringArray(R.array.country_codes);
    String[] languages = getResources().getStringArray(R.array.official_languages);
    String[] currencies = getResources().getStringArray(R.array.currencies);
    int[] images = {R.drawable.nigeria_flag, R.drawable.ghana_flag, R.drawable.kenya_flag, R.drawable.south_africa_flag, R.drawable.burkina_faso_flag, R.drawable.us_flag, R.drawable.england_flag, R.drawable.france_flag, R.drawable.russian_flag, R.drawable.china_flag, R.drawable.egypt_flag, R.drawable.rwanda_flag};
      
    SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
    String data = prefs.getString("data", "");
    String Lang = prefs.getString("lang", "");
    String savedCurrency = prefs.getString("currency", "");
      
    String decryptedData;
    if (!data.equals("") || !data.isEmpty()) {
        try {
            CryptoManager cryptoManger = new CryptoManager(this);
            decryptedData = cryptoManger.decrypt(data);
        } catch (Exception e) {
            decryptedData = "";
        }
    } else {
        decryptedData = "Nigeria";
    }
 
      String decryptedCurrency;
      if(!savedCurrency.equals("") || !savedCurrency.isEmpty()){
       try {
            CryptoManager cryptoManger = new CryptoManager(this);
            decryptedCurrency = cryptoManger.decrypt(savedCurrency);
        } catch (Exception e) {
            decryptedCurrency = "";
        }
      }else{
       decryptedCurrency = "";  
      }    
      
   String decryptedLang;
      if(!Lang.equals("") || !Lang.isEmpty()){
       try {
            CryptoManager cryptoManger = new CryptoManager(this);
            decryptedLang = cryptoManger.decrypt(Lang);
        } catch (Exception e) {
            decryptedLang = "";
        }
      }else{
       decryptedLang = "";  
      }       
      
    int index = -1;
    for (int i = 0; i < countries.length; i++) {
        if (countries[i].equals(decryptedData)) {
            index = i;
            break;
        }
    }

    if (index != -1) {
        String countryCode = codes[index];
        String language = languages[index];
        String currency = currencies[index];
        int image = images[index];
       
         TextView countryTextView = findViewById(R.id.country);
         countryTextView.setText(decryptedData+ "("+countryCode+")");
         ImageView country_image = findViewById(R.id.country_image);
         Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image);
         loadCountryImage(country_image,bitmap);
         country_image.setColorFilter(null);
       
        TextView languageTextView = findViewById(R.id.language);
      if(!decryptedLang.isEmpty()){
       language = decryptedLang;   
       }
        languageTextView.setText(language);
       
     TextView currencyTextView = findViewById(R.id.currency);
       if(!decryptedCurrency.isEmpty()){
       currency = decryptedCurrency;   
       }
        currencyTextView.setText(currency);
        
       
       
    } else {
        CustomToast.show(this, "No country found");
    }
}
   
   private void loadCountryImage(ImageView country_image, Bitmap bitmap){
    rounded_bitmap roundBitmap = new rounded_bitmap();
    Bitmap roundedImage = roundBitmap.getRoundedShape(bitmap);
    country_image.setImageBitmap(roundedImage);
   }
   
   @Override 
   protected void onResume(){
      super.onResume();
      setData();
   }
}    