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
import android.text.InputFilter;
import android.widget.EditText;


public class AddRatingInflater {
    private Context context;
    private FrameLayout container;
    private View inflaterView;
    private LayoutInflater inflater;

    public AddRatingInflater(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void showRatingPreview() {
        container.setVisibility(View.VISIBLE);
        container.removeAllViews();
        inflaterView = inflater.inflate(R.layout.rate_preview_item, container, true);
        ImageView closePrompt = inflaterView.findViewById(R.id.close_rating_preview);
        closePrompt.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        EditText editText = inflaterView.findViewById(R.id.review_editText);
        LimiteditText(editText);
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
                showRatingPreview();
            }
        });
    }

    private void LimiteditText(EditText editText) {
        InputFilter.LengthFilter lengthFilter = new InputFilter.LengthFilter(250);
        editText.setFilters(new InputFilter[]{lengthFilter});
    }
}