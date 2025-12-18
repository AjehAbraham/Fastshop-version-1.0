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
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.os.Build;



public class about extends Activity{
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
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
        WebView webView = findViewById(R.id.webView); 
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAllowFileAccess(true); 
        webSettings.setAllowContentAccess(true); 
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setGeolocationEnabled(true); 
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true); 
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        CookieManager.getInstance().setAcceptCookie(true);
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
          }
        
        try{
           webView.loadUrl("https://www.example.com/en/about");
        }catch(Exception e){
         CustomToast.show(this, "Error loading web content"); 
        }
        
      webView.setWebViewClient(new WebViewClient() {
    @Override
    public void onPageFinished(WebView view, String url) {
        // Page loading finished
       // CustomToast.show(about.this, "Loaded..");
    }
}); 
       
    }
   
 @Override 
   protected void onResume(){
      super.onResume();
      
   }
}