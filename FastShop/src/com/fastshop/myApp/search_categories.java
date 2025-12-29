
 
package com.fastshop.myApp;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.ActionBar;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.content.Intent;
import android.widget.FrameLayout;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.os.Handler;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.content.SharedPreferences;
import java.util.ArrayList;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.GridLayout;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;

import android.animation.ObjectAnimator;
import android.view.animation.OvershootInterpolator;

import android.app.FragmentTransaction;
import android.app.FragmentManager;

public class search_categories extends Activity{
    private View nav_bar;
    private EditText search_editText;
    private ImageView clear_search,search_btn,scan_options_btn,close_prompt,gallery_btn;
    private FrameLayout scan_list;
    private LinearLayout gallery_layout,camera_layout;
    private LinearLayout search_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.home_page)); 
      
        LinearLayout navigation_layout = findViewById(R.id.navigation_layout);
         nav_bar =  navigation_bar_inflater.Navigators(this); 
        navigation_layout.addView(nav_bar); 
     
     
    LinearLayout  header = findViewById(R.id.header);
        View count = header.getChildAt(0);
        if(count instanceof ImageView){
            ImageView image = (ImageView) count;
            image.setColorFilter(Color.BLACK);
            image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
             Intent intent = new Intent(search_categories.this, MainActivity.class);
              startActivity(intent);
               finish(); 
            }    
            });
        }    
     
     search_editText  = findViewById(R.id.search_editText);
     search_editText.requestFocus();
     //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
      
     
     clear_search = findViewById(R.id.clear_search);
     search_btn = findViewById(R.id.search_btn);
     
     scan_options_btn = findViewById(R.id.scan_options_btn);
     try{
     FrameLayout fragment_container = findViewById(R.id.fragment_container);
     SearchImageInflater searchInflater = new SearchImageInflater(this,this);
     searchInflater.addUi(fragment_container,scan_options_btn);
      //fragment_container.bringToFront();
     SearchImage = searchInflater.selected_image; 
  
     }catch(Exception e){
      CustomToast.show(this, e.getMessage());
     }
      
     styleSort();
     styleSearchImage(); 
     ImageView search_btn = findViewById(R.id.search_btn);
     search_btn.setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);
     
      clear_search = findViewById(R.id.clear_search);
      clearSearchInput(clear_search);
      startSearch(search_editText);
     
     add_remove_sorting_list();
     
      search_editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          String Value = search_editText.getText().toString();
         CustomToast.show(search_categories.this, "searhing .." + Value);
            return true;
        }
        return false;
    }
});
     
  LinearLayout sorting_list = findViewById(R.id.sorting_list);
  search_editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
       addSearchList();
       removeSortingList(sorting_list);
        }else{
         removeSearchList();
        }
    }
});    
     
     FragmentManager fragmentManager = getFragmentManager();
     FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
     loader_inflater loadFragment = new loader_inflater();
    // fragmentTransaction.add(R.id.fragment_container,loadFragment);
    // fragmentTransaction.commit();
     
     
    }
 private void add_remove_sorting_list() {
    LinearLayout sorting_list = findViewById(R.id.sorting_list);
    ImageView image = findViewById(R.id.sorting_btn);

    image.postDelayed(new Runnable() {
        @Override
        public void run() {
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sorting_list.getVisibility() == View.GONE) {
                        sorting_list.setVisibility(View.VISIBLE);
                        sorting_list.animate().translationX(0).setDuration(300).start();
                    } else {
                        sorting_list.animate().translationX(-sorting_list.getWidth()).setDuration(300).start();
                        sorting_list.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                sorting_list.setVisibility(View.GONE);
                            }
                        }, 300); 
                    }
                }
            });
        }
    }, 50);
}
 
  private void styleSort(){
  LinearLayout sorting_layout = findViewById(R.id.sorting_layout);
  View view = sorting_layout.getChildAt(1);
      if(view instanceof ImageView){
          ImageView imageView = (ImageView) view;
          imageView.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
      }     
      
  }
    
 private void styleSearchImage(){
  LinearLayout layout = findViewById(R.id.search_layout);
  for(int i = 0; i < layout.getChildCount(); i++){
   View view = layout.getChildAt(i);
   if(view instanceof ImageView){
    ImageView image = (ImageView) view;
    image.setColorFilter(Color.BLACK);
   }
  }
 }
 
 private void startSearch(EditText search_edit_text){
  search_edit_text.addTextChangedListener(new TextWatcher(){
  @Override
   public void beforeTextChanged(CharSequence s, int start,int count , int before){}
   @Override 
    public void onTextChanged(CharSequence s,int start,int before,int count){}
   @Override 
   public void afterTextChanged(Editable s){
    String text = search_edit_text.getText().toString();
    LinearLayout sorting_list = findViewById(R.id.sorting_list);
    
    if(text.length() > 0){
     addSearchList();
    // add_remove_sorting_list();
     removeSortingList(sorting_list);
     
     clear_search.setVisibility(View.VISIBLE);
     search_btn.setVisibility(View.VISIBLE);
     scan_options_btn.setVisibility(View.GONE);
    }else if(text.length() == 0 || text.equals("")){
     clear_search.setVisibility(View.GONE);
     scan_options_btn.setVisibility(View.VISIBLE);
     search_btn.setVisibility(View.GONE);
    }else{
     clear_search.setVisibility(View.GONE);
     search_btn.setVisibility(View.GONE);
     scan_options_btn.setVisibility(View.VISIBLE);
    }
   }
    
  });
 }
 
 
  private void clearSearchInput(ImageView image){
   image.setOnClickListener(new View.OnClickListener(){
   @Override
   public void onClick(View v){
    search_editText.setText("");
    clear_search.setVisibility(View.GONE);
    scan_options_btn.setVisibility(View.VISIBLE);
   }
  });
}
 private TextView selectedTextView;
 private void Search_Sorting_list(){
    LinearLayout sorting_list = findViewById(R.id.sorting_list);
    sorting_list.removeAllViews();
    String[] categories = getResources().getStringArray(R.array.search_categories);
   GridLayout gridLayout = new GridLayout(this);
  LinearLayout.LayoutParams gridParams = new LinearLayout.LayoutParams(
  LinearLayout.LayoutParams.MATCH_PARENT,
  LinearLayout.LayoutParams.WRAP_CONTENT
  );
  gridParams.setMargins(5,5,5,5);
  gridLayout.setLayoutParams(gridParams);
  gridLayout.setColumnCount(3);
  
  TextView[] textView = new TextView[categories.length];
for (int i = 0; i < categories.length; i++) {
    textView[i] = new TextView(this);
    GridLayout.LayoutParams textParams = new GridLayout.LayoutParams(
            GridLayout.spec(GridLayout.UNDEFINED, 1f),
            GridLayout.spec(GridLayout.UNDEFINED, 1f));
    textParams.width = 0;
    textParams.setMargins(8, 8, 8, 8);
    textView[i].setLayoutParams(textParams);
    textView[i].setPadding(10, 10, 10, 10);
    textView[i].setGravity(Gravity.CENTER);
    textView[i].setBackgroundResource(R.drawable.cornered_layout);
    textView[i].setText(categories[i]);
    textView[i].setTextSize(12);
     final int index = i;
     if(index == 0){
     textView[index].setTextColor(Color.WHITE);
     textView[index].setBackgroundResource(R.drawable.cornered_selected);
     }
    gridLayout.addView(textView[i]);
    
    textView[i].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int j = 0; j < categories.length; j++) {
                textView[j].setTextColor(Color.BLACK);
                textView[j].setBackgroundResource(R.drawable.cornered_layout);
            }
            textView[index].setTextColor(Color.WHITE);
            textView[index].setBackgroundResource(R.drawable.cornered_selected);
            String selectedText = textView[index].getText().toString();
            CustomToast.show(search_categories.this, selectedText);
        }
    });
}
  sorting_list.addView(gridLayout);
}
 
 private void removeSortingList(LinearLayout list){
  list.setVisibility(View.GONE);
 }
 private void addSortingList(LinearLayout list){
  list.setVisibility(View.VISIBLE);
 }
 private void addSearchList(){
  search_list = findViewById(R.id.search_list);
   search_list.setVisibility(View.VISIBLE);
  loadSearchSuggestions();
 }
 private void removeSearchList(){
    search_list = findViewById(R.id.search_list);
   search_list.setVisibility(View.GONE);
 }
 
 


 
private void loadSearchSuggestions() {
    String[] suggestions = getResources().getStringArray(R.array.suggestion);
    SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
    String savedSuggestions = prefs.getString("list", "");

    if (!savedSuggestions.isEmpty()) {
        String[] encryptedSuggestions = savedSuggestions.split(",");
        if (encryptedSuggestions.length > 0) {
            String[] decryptedSuggestions = new String[encryptedSuggestions.length];
            for (int i = 0; i < encryptedSuggestions.length; i++) {
                try {
                    CryptoManager cryptoManager = new CryptoManager(this);
                    decryptedSuggestions[i] = cryptoManager.decrypt(encryptedSuggestions[i]);
                } catch (Exception e) {
                    CustomToast.show(this, "Error occurred" + e.getMessage());
                }
            }
            loadSearchList(decryptedSuggestions);
        } else {
            loadSearchList(suggestions);
        }
    } else {
        loadSearchList(suggestions);
    }
}
 
 
 private void saveSearchSuggestions(String[] suggestions) {
    StringBuilder encryptedSuggestions = new StringBuilder();

    for (String suggestion : suggestions) {
        try {
            CryptoManager cryptoManager = new CryptoManager(this);
            String encryptedSuggestion = cryptoManager.encrypt(suggestion);
            encryptedSuggestions.append(encryptedSuggestion).append(",");
        } catch (Exception e) {
            CustomToast.show(this, "Error occurred" + e.getMessage());
        }
    }
    if (encryptedSuggestions.length() > 0) {
        encryptedSuggestions.deleteCharAt(encryptedSuggestions.length() - 1);
    }

    SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
    prefs.edit().putString("list", encryptedSuggestions.toString()).apply();
}
 
 
 private void loadSearchList(String[] mySuggestions){
    search_list = findViewById(R.id.search_list);
    search_list.removeAllViews();
    LinearLayout horizontalLayout = new LinearLayout(this);
    TextView titleTextView = new TextView(this);
    LinearLayout.LayoutParams horzontalParams = new LinearLayout.LayoutParams(
    LinearLayout.LayoutParams.MATCH_PARENT,
    LinearLayout.LayoutParams.WRAP_CONTENT
    );
    
    horizontalLayout.setLayoutParams(horzontalParams);
    LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
     LinearLayout.LayoutParams.WRAP_CONTENT,
     LinearLayout.LayoutParams.WRAP_CONTENT,1
    );
  horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
   titleParams.setMargins(8,5,15,8);
   titleTextView.setLayoutParams(titleParams);
   titleTextView.setText("Recent ");
   titleTextView.setTextSize(13);
   titleTextView.setTypeface(null,Typeface.BOLD);
  
    TextView clearList = new TextView(this);
    LinearLayout.LayoutParams clearParams = new LinearLayout.LayoutParams(
    LinearLayout.LayoutParams.WRAP_CONTENT,
    LinearLayout.LayoutParams.WRAP_CONTENT
  );
 
  clearParams.gravity = Gravity.RIGHT;
  clearParams.setMargins(0,5,15,8);
  clearList.setLayoutParams(clearParams);
  clearList.setGravity(Gravity.RIGHT);
  clearList.setTextSize(13);
  clearList.setTypeface(null,Typeface.BOLD);
  clearList.setTextColor(getResources().getColor(R.color.tomato));
  clearList.setText("clear");
  clearAllList(clearList);
 // search_list.addView(clearList);
  
  horizontalLayout.addView(titleTextView);
  horizontalLayout.addView(clearList); 
  search_list.addView(horizontalLayout);
  
    for(String data : mySuggestions){
        TextView textView = new TextView(this);
         ImageView image = new ImageView(this);
     LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
     50,50
     );
     imageParams.setMargins(5,0,8,5);
     image.setLayoutParams(imageParams);
     image.setImageResource(R.drawable.outline_close_white_48);
     image.setColorFilter(Color.GRAY);
     LinearLayout bodyLayout = new LinearLayout(this);
     LinearLayout.LayoutParams bodyParams = new LinearLayout.LayoutParams(
     LinearLayout.LayoutParams.MATCH_PARENT,
     LinearLayout.LayoutParams.WRAP_CONTENT
     );
     
     bodyLayout.setLayoutParams(bodyParams);
     bodyLayout.setOrientation(LinearLayout.HORIZONTAL);
     
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,1
        );
        textParams.setMargins(10,0,0,5);
        textView.setLayoutParams(textParams);
        textView.setTextSize(12);
       // textView.setTypeface(null, Typeface.BOLD);
        textView.setText(data);
        listFunctions(textView,image);
        bodyLayout.addView(textView);
        bodyLayout.addView(image);
        search_list.addView(bodyLayout);
     
    }
  LoadTrend();
}
 
 private void clearAllList(TextView listTextView){
  listTextView.setOnClickListener(new View.OnClickListener(){
   @Override
   public void onClick(View view){
    CustomToast.show(search_categories.this, "Clearing search...");
   }
  });
 }
 
 
 private void listFunctions(TextView textView,ImageView image){
  textView.setOnClickListener(new View.OnClickListener(){
  @Override
   public void onClick(View view){
    CustomToast.show(search_categories.this, "Searching...");
   }
  });
  
    image.setOnClickListener(new View.OnClickListener(){
  @Override
   public void onClick(View view){
    CustomToast.show(search_categories.this, "removing search item...");
   }
  });
 }
  private void LoadTrend(){
   try{
   search_list = findViewById(R.id.search_list);
   LinearLayout subLayout = new LinearLayout(this);
   GridLayout gridLayout = new GridLayout(this);
   TextView title = new TextView(this);
   
   LinearLayout.LayoutParams subParams = new LinearLayout.LayoutParams(
   LinearLayout.LayoutParams.MATCH_PARENT,
   LinearLayout.LayoutParams.WRAP_CONTENT
   );
   subLayout.setLayoutParams(subParams);
   subLayout.setOrientation(LinearLayout.VERTICAL);
   
   LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
   LinearLayout.LayoutParams.WRAP_CONTENT,
   LinearLayout.LayoutParams.WRAP_CONTENT
   );
   titleParams.setMargins(8,0,0,8);
   title.setLayoutParams(titleParams);
   title.setTextSize(13);
   title.setTypeface(null,Typeface.BOLD);
   title.setText("Trending");
   subLayout.addView(title);
   
 LinearLayout.LayoutParams gridParams = new LinearLayout.LayoutParams(
   LinearLayout.LayoutParams.MATCH_PARENT,
   LinearLayout.LayoutParams.WRAP_CONTENT
   );
   gridParams.setMargins(0,8,0,0);
   gridLayout.setLayoutParams(gridParams);
   gridLayout.setColumnCount(3);
   
   //String[] array = getResources().getStringArray(R.array.search_categories);
   String[] array = getResources().getStringArray(R.array.suggestion);
   TextView[] Result = new TextView[array.length];
   for(int i = 0; i < array.length; i++){
      Result[i] = new TextView(this);
    GridLayout.LayoutParams resultParams = new GridLayout.LayoutParams(
    GridLayout.spec(GridLayout.UNDEFINED,1f),
    GridLayout.spec(GridLayout.UNDEFINED,1f)
    );
    resultParams.width = 0;
    resultParams.setMargins(5,8,5,8);
    Result[i].setLayoutParams(resultParams);
    Result[i].setBackgroundResource(R.drawable.cornered_layout);
    Result[i].setPadding(10,10,10,10);
    Result[i].setTextSize(12);
    Result[i].setText(array[i]);
    Result[i].setGravity(Gravity.CENTER);
    gridLayout.addView(Result[i]);
   }
   subLayout.addView(gridLayout);
   search_list.addView(subLayout);
   }catch(Exception e){
    CustomToast.show(this, e.getMessage());
   }
  }
 
 
 private void removeSelectedtext(TextView textView){
  textView.setBackgroundResource(R.drawable.cornered_layout);
  textView.setTextColor(Color.BLACK);
 }
 
 
 private void hideSoftKeyboard(){
      View view = getCurrentFocus();
      if (view != null) {
    view.postDelayed(new Runnable() {
        @Override
        public void run() {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }, 100);
}
  
 }
 
 

private  ImageView SearchImage ;
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
     
    if (requestCode == SearchImageInflater.REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
        // Handle the camera result
      Bitmap photo= (Bitmap) data.getExtras().get("data");
       SearchImage.setImageBitmap(photo);
     
        CustomToast.show(this, "Camera result");
    } else if (requestCode == 2 && resultCode == RESULT_OK) {
        // Handle the gallery result
        Uri imageUri = data.getData(); 
         SearchImage.setImageURI(imageUri);
        CustomToast.show(this, "Gallery result");
    }
}

@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == SearchImageInflater.REQUEST_CODE_CAMERA) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, open camera
            CustomToast.show(this, "Permission granted");
        } else {
            // Permission denied
            CustomToast.show(this, "Permission denied");
        }
    }
}

 
 @Override
 protected void onResume(){
  super.onResume();
  View navLayout =  nav_bar;
  navLayout.postDelayed(new Runnable(){
   @Override
   public void run(){
 navigation_bar_inflater.changeAllTheme();     
 navigation_bar_inflater.ChangeSecond();   
   }
  },50);
  Search_Sorting_list();
 }
   
   @Override
   public void onBackPressed(){
       finish();
   }} 

    