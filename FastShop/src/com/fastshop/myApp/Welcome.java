package com.fastshop.myApp;

import android.app.*;
import android.view.*;
import android.os.Bundle;
import android.content.*;
import android.content.SharedPreferences;
import android.content.Intent;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ImageView;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Color;

import java.io.File;
import java.io.FileOutputStream;
import android.os.Environment;
import android.media.MediaScannerConnection;


public class Welcome extends Activity{
    
   @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.home_page)); 
          
       //getStarted_btn 
      Button getStarted_btn = findViewById(R.id.getStarted_btn);
      getStarted_btn.setOnClickListener(new View.OnClickListener(){
       @Override 
       public void onClick(View v){
       setUniqueData();   
       }   
      });  
     
      ImageProcessor imageProcessor = new ImageProcessor();
      Bitmap roundedCornerBitmap = imageProcessor.getRoundedCornerImage(Welcome.this, R.drawable.ic_launcher, 25);
      ImageView imageView = findViewById(R.id.image_view);
      imageView.setImageBitmap(roundedCornerBitmap);     
   
       
    }
    
   private void getUniqueData(){
     SharedPreferences prefrence = getSharedPreferences("Unique",MODE_PRIVATE);
    boolean IsFirst_time =  prefrence.getBoolean("is_first_time", false);
       if(IsFirst_time){
      Intent intent = new Intent(this, MainActivity.class);
       startActivity(intent);
       finish();          
       }
   }
   private void setUniqueData(){
     SharedPreferences prefrence =  getSharedPreferences("Unique",MODE_PRIVATE);
     SharedPreferences.Editor editor = prefrence.edit();
     editor.putBoolean("is_first_time", true);
     editor.apply(); 
     Intent intent = new Intent(this, MainActivity.class);
     startActivity(intent);
     finish();     
   }
 @Override
  protected void onResume(){
     super.onResume();
     getUniqueData();
  }  
    
 /* private void Edit_save_image(){
   *            
try {
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    Bitmap mutableBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(mutableBitmap);
    Paint paint = new Paint();
    paint.setColorFilter(new PorterDuffColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN));
    canvas.drawBitmap(bitmap, 0, 0, paint);
    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "output.png");
    FileOutputStream fos = new FileOutputStream(file);
    mutableBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
    fos.close();

    // Add the image to the media store
    MediaScannerConnection.scanFile(Welcome.this, new String[]{file.getAbsolutePath()}, null, null);
} catch (Exception e) {
    Toast.makeText(Welcome.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
}
  }*/
    
}
