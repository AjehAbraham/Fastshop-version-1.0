package com.fastshop.myApp;

import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.graphics.PorterDuff;
import android.graphics.Color;
import android.webkit.WebView;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.os.Build;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import android.webkit.WebChromeClient;

public class web_content {
    private View view;
    private Context context;
    
    public web_content(Context context){
      this.context = context;  
    }
    public void LoadUi(FrameLayout container){
      LayoutInflater flater = LayoutInflater.from(context);
        container.removeAllViews();
       view = flater.inflate(R.layout.web_content,container, true);
      // container.setVisibility(View.VISIBLE);
      showAnimation(container);
       LoadURI(container);
      
       ImageView imageView = view.findViewById(R.id.close_page);
       imageView.setColorFilter(Color.BLACK);
       imageView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
        removeInflater(container);  
        }
       });
    }
    public void removeInflater(FrameLayout container){
        container.removeAllViews();
        //container.setVisibility(View.GONE);
       hideAnimation(container);
    }
  private void showAnimation(FrameLayout container){
    container.setVisibility(View.VISIBLE);
     container.animate()
    .translationY(0)
    .setDuration(320)
    .setInterpolator(new AccelerateDecelerateInterpolator())
    .start();
  }
  private void hideAnimation(FrameLayout container){
     container.animate()
    .translationY(container.getHeight())
    .setDuration(320)
    .setInterpolator(new AccelerateDecelerateInterpolator())
    .withEndAction(new Runnable() {
        @Override
        public void run() {
            container.setVisibility(View.GONE);
        }
    })
    .start();   
  }
  public void LoadURI(FrameLayout container){
    WebView webView = view.findViewById(R.id.webView);
    webView.setWebViewClient(new WebViewClient() {
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        // Hide the WebView and show the error view
        webView.setVisibility(View.GONE);
        View errorView = web_content.this.view.findViewById(R.id.error_view);
        errorView.setVisibility(View.VISIBLE);
       // errorTextView.setText(description + " "+ "(" + errorCode + ")");
       // int errorCode = error.getErrorCode();
       // String description = error.getDescription().toString();
   String errorMessage;
    switch (errorCode) {
        case WebViewClient.ERROR_HOST_LOOKUP:
        case WebViewClient.ERROR_CONNECT:
        case WebViewClient.ERROR_IO:
            errorMessage = "No internet connection";
            break;
        case WebViewClient.ERROR_TIMEOUT:
            errorMessage = "Connection timed out";
            break;
        case WebViewClient.ERROR_FAILED_SSL_HANDSHAKE:
            errorMessage = "Failed to establish a secure connection";
            break;
        case WebViewClient.ERROR_BAD_URL:
            errorMessage = "Invalid URL";
            break;
        case WebViewClient.ERROR_UNSUPPORTED_SCHEME:
            errorMessage = "Unsupported URL scheme";
            break;
        default:
            errorMessage = "Unknown error: " + description;
            break;
    }
    TextView errorTextView = web_content.this.view.findViewById(R.id.error_text);
     errorTextView.setText(errorMessage);

    }
});
    WebSettings();
    setupWebView(webView,container);
    webView.loadUrl("https://google.com");
    loaderAnimation(webView);
    loadingError(webView);
  }
  public void WebSettings(){
       WebView webView = view.findViewById(R.id.webView);
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
        
  }
  private void loaderAnimation(WebView webView){
    try{
    webView.setWebChromeClient(new WebChromeClient() {
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        // Update the width of the loader view based on the progress
        View loaderView = web_content.this.view.findViewById(R.id.loader);
        loaderView.setVisibility(View.VISIBLE);
        loaderView.getLayoutParams().width = (int) ((newProgress / 100f) * loaderView.getResources().getDisplayMetrics().widthPixels);
        loaderView.requestLayout();

        // Hide the loader view when progress reaches 100%
        if (newProgress == 100) {
            loaderView.setVisibility(View.GONE);
        }
    }
});
    }catch(Exception e){
      CustomToast.show(context,e.getMessage());
    }
  }
  private void loadingError(WebView webView){
    Button retryButton = web_content.this.view.findViewById(R.id.retry_button);
   retryButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        webView.reload();
        webView.setVisibility(View.VISIBLE);
        View errorView = web_content.this.view.findViewById(R.id.error_view);
        errorView.setVisibility(View.GONE);
        loaderAnimation(webView);
    }
});
  }
  private void checkCookie(){
    CookieManager cookieManager = CookieManager.getInstance();
    String cookie = cookieManager.getCookie("https://example.com");
    //TextView cookieTextView = findViewById(R.id.cookie_text_view);
   // cookieTextView.setText(cookie);
  }

private void setupWebView(WebView webView, FrameLayout container) {
    webView.addJavascriptInterface(new WebAppInterface(context, container), "Android");
}

}