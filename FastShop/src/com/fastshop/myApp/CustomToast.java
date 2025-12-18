package com.fastshop.myApp;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Bitmap;

public class CustomToast {
    public static void show(Context context, String message) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);
      ImageProcessor imageProcessor = new ImageProcessor();
      Bitmap roundedCornerBitmap = imageProcessor.getRoundedCornerImage(context, R.drawable.ic_launcher, 2);
      ImageView imageView = layout.findViewById(R.id.toast_icon);
      imageView.setImageBitmap(roundedCornerBitmap);     
        
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
//CALLING IT IN ANY ACTIVITY 
//CustomToast.show(context, " message here");