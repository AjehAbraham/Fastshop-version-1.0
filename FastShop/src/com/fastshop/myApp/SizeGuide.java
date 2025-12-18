package com.fastshop.myApp;

import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.PorterDuff;


public class SizeGuide {
    private Context context;
    private FrameLayout container;
    private View inflaterView;
    private LayoutInflater inflater;

    public SizeGuide(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void showSizeGuide() {
        container.setVisibility(View.VISIBLE);
        container.removeAllViews();
        inflaterView = inflater.inflate(R.layout.size_guide, container, true);
        ImageView closePrompt = inflaterView.findViewById(R.id.close_size_preview);
        closePrompt.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        closePrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.removeView(inflaterView);
                container.setVisibility(View.GONE);
            }
        });
    }

    public void LoadUi(FrameLayout container, TextView textView) {
        this.container = container;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSizeGuide();
            }
        });
    }
}