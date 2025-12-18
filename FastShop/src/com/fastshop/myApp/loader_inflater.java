package com.fastshop.myApp;

import android.app.Activity;
import android.view.View;
import android.view.LayoutInflater;
import android.app.Fragment;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

public class loader_inflater extends Fragment{
   @Override
   public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
    View view = inflater.inflate(R.layout.loader,container, false);
    ImageView imageView = view.findViewById(R.id.loader_imageView);
    /*TextView textView = view.findViewById(R.id.loader_textView);
    Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/TiltPrism-Regular-VariableFont_XROT,YROT.ttf");
    textView.setTypeface(typeface);*/
    ImageView loader_imageView2 = view.findViewById(R.id.loader_imageView2);
    
     loadAnimation(imageView,loader_imageView2);
     return view;
   }    
    
   private void loadAnimation(ImageView imageView,ImageView loader_imageView2){
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        imageView.startAnimation(rotateAnimation);
    
        ObjectAnimator animator = ObjectAnimator.ofFloat(loader_imageView2, "rotation", 0f, 360f);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
       // loader_imageView2.startAnimation(rotateAnimation);
   }
 
    
}