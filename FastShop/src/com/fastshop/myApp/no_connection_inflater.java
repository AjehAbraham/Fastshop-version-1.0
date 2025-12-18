package com.fastshop.myApp;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.ActionBar;
import android.widget.LinearLayout;
import android.view.View;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.graphics.Typeface;
import android.graphics.Color;
import android.widget.FrameLayout;

public class no_connection_inflater{
   private static View layout;
   private static Context context;
   private static Activity activity;
     
 public static void setActivity(Activity activity){
      activity = activity;
 }
     
    public static View getNoConnectionView(Context context) {
         LayoutInflater inflater = LayoutInflater.from(context);
         View layout = inflater.inflate(R.layout.no_connection, null);
         LinearLayout connectionHeader = layout.findViewById(R.id.connection_header);
         View view = connectionHeader.getChildAt(0);
         LinearLayout Xlayout = (LinearLayout) view;
         View childView = Xlayout.getChildAt(0);
      if (childView instanceof ImageView) {
      ImageView connectionImage = (ImageView) childView;
      connectionImage.setColorFilter(Color.BLACK);
      }
        return layout;
    }

}