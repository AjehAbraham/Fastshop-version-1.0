package com.fastshop.myApp;

import android.app.Activity;
import android.view.View;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.provider.MediaStore;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.animation.ObjectAnimator;
import android.view.animation.OvershootInterpolator;


public class SearchImageInflater {
    private Context context;
    private View view;
    private ImageView lock_image, close_prompt, gallery_btn;
    private LinearLayout scan_list;
    private LinearLayout gallery_layout, camera_layout;
    private Activity activity;
    public ImageView selected_image;
    public SearchImageInflater(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public View addUi(FrameLayout container, ImageView imageView) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.search_image_inflater, container, true);
        
        selected_image = view.findViewById(R.id.selected_image);
        lock_image = view.findViewById(R.id.lock_image);
        lock_image.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
        scan_list = view.findViewById(R.id.scan_list);
        gallery_btn = view.findViewById(R.id.gallery_btn);
        gallery_btn.setColorFilter(Color.BLACK);
        gallery_layout = view.findViewById(R.id.gallery_layout);
        camera_layout = view.findViewById(R.id.camera_layout);
        close_prompt = view.findViewById(R.id.close_prompt);
        close_prompt.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (container.getVisibility() == View.GONE) {
                    container.setVisibility(View.VISIBLE);
                    scan_list.setVisibility(View.VISIBLE);
                    playAnimation();
                } else {
                    container.setVisibility(View.GONE);
                    scan_list.setVisibility(View.GONE);
                    playAnimation();
                }
            }
        });
        closePrompt(close_prompt, container);
        Functions(container);
        return view;
    }

    private void Functions(FrameLayout container) {
        camera_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
        gallery_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(gallery_layout);
            }
        });
        
       LinearLayout cancel_prompt_layout = view.findViewById(R.id.cancel_prompt_layout);
         cancel_prompt_layout.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v){
         scan_list.setVisibility(View.GONE);
         container.setVisibility(View.GONE);    
         } 
         });
       LinearLayout history_layout = view.findViewById(R.id.history_layout);
        history_layout.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v){
         CustomToast.show(context, "Not Avalaible");
         } 
         });
    }

    private void playAnimation() {
        scan_list.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(scan_list, "translationX", -scan_list.getWidth(), 0);
                animator.setDuration(500);
                animator.setInterpolator(new OvershootInterpolator());
                animator.start();
            }
        }, 50);
        hideSoftKeyboard();
    }

    private void closePrompt(ImageView myImage, final FrameLayout container) {
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.setVisibility(View.GONE);
                scan_list.setVisibility(View.GONE);
            }
        });
    }

    private void openGallery(LinearLayout gallery) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, 2);
    }

    public static final int REQUEST_CODE_CAMERA = 1;
    private void openCamera() {
        if (context.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            activity.startActivityForResult(intent, REQUEST_CODE_CAMERA);
        }
    }

    private void hideSoftKeyboard() {
        View view = activity.getCurrentFocus();
        if (view != null) {
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }, 100);
        }
    }
}