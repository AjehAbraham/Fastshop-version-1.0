package com.fastshop.myApp;


import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.view.View;

public class WebAppInterface {
    private FrameLayout container;
    private Context context;

    public WebAppInterface(Context context, FrameLayout container){
        this.context = context;
        this.container = container;
    }

    @JavascriptInterface
    public void close_web_content(String data) {
        switch(data){
            case "Terminated":
                container.removeAllViews();
                container.setVisibility(View.GONE);
                break;
            case "success":
                container.removeAllViews();
                container.setVisibility(View.GONE);
                break;
            case "Error":
                CustomToast.show(context, "Error: " + data);
                break;
            default:
                CustomToast.show(context, "Error occurred");
        }
    }
    /*
     * JAVASCRIPT CODE TO SEND MESSAGE TO ANDRIOD;
     * function sendDataToAndroid(data) {
    if (typeof Android !== 'undefined') {
        Android.close_web_content(data);
    } else {
        console.log('Android interface not available');
    }
}

// Example usage:
sendDataToAndroid('Terminated'); // or 'success', 'Error', etc.
     */
}