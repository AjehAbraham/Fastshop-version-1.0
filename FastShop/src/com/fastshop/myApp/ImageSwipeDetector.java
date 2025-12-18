package com.fastshop.myApp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ImageSwipeDetector {
    private Context context;
    private LinearLayout subLayout;
    private ImageView imageView;
    private TextView imageCounter;
    private Bitmap[] images;
    private int currentImage = 0;
    private float x1, x2, y1, y2;
    private ImageView closeBtn;
    public ImageSwipeDetector(Context context) {
        this.context = context;
    }

    public View showImage(FrameLayout container, ViewGroup parent, Bitmap[] images, int currentIndex) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.image_swipe_detector, parent, false);

        subLayout = (LinearLayout) view.findViewById(R.id.sub_layout);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageCounter = (TextView) view.findViewById(R.id.image_counter);
        closeBtn = (ImageView) view.findViewById(R.id.close_detector);
        closeBtn.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View view){
           subLayout.setVisibility(View.GONE);
           container.setVisibility(View.GONE);   
         }   
        });
        this.images = images;
        currentImage = currentIndex;

        imageView.setImageBitmap(images[currentImage]);
        updateImageCounter();

        subLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x1 = event.getX();
                    y1 = event.getY();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    x2 = event.getX();
                    y2 = event.getY();

                    float deltaX = x2 - x1;
                    float deltaY = y2 - y1;

                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        // Horizontal swipe
                        if (x1 < x2) {
                            // Swipe right
                            if (currentImage > 0) {
                                currentImage--;
                            } else {
                                currentImage = images.length - 1;
                            }
                        } else {
                            // Swipe left
                            if (currentImage < images.length - 1) {
                                currentImage++;
                            } else {
                                currentImage = 0;
                            }
                        }
                        imageView.setImageBitmap(images[currentImage]);
                        updateImageCounter();
                    } else {
                        // Vertical swipe
                        if (y1 < y2) {
                            // Swipe down
                            ((ViewGroup) view.getParent()).removeView(view);
                            container.setVisibility(View.GONE);
                        } else {
                            // Swipe up
                            ((ViewGroup) view.getParent()).removeView(view);
                            container.setVisibility(View.GONE);
                        }
                    }
                }
                return true;
            }
        });

        parent.addView(view);
        return view;
    }

    private void updateImageCounter() {
        imageCounter.setText((currentImage + 1) + "/" + images.length);
    }
}