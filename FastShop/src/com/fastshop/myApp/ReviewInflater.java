package com.fastshop.myApp;


import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.ImageView;

public class ReviewInflater {
    private View view;
    private Context context;
    public ReviewInflater(Context context){
        this.context = context;
    }
    public void LoadUi(LinearLayout layout){
     LayoutInflater inflater = LayoutInflater.from(context);
     view = inflater.inflate(R.layout.review_inflater, layout, true);  
     ImageView imageView = view.findViewById(R.id.image);  
      imageView.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
    }
}