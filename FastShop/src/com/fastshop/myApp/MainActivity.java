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
import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.PorterDuff;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ScrollView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.text.TextWatcher;
import android.text.Editable;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.os.Handler;
import android.widget.AdapterView;
import android.widget.Adapter;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.provider.MediaStore;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.animation.ObjectAnimator;
import android.view.animation.OvershootInterpolator;
import android.content.SharedPreferences;
import android.view.ViewTreeObserver;

public class MainActivity extends Activity {
    private TextView search_textView;
    private View nav_bar;
    private boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.home_page));
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/TiltPrism-Regular-VariableFont_XROT,YROT.ttf");
        TextView textView = findViewById(R.id.textView_header);
        textView.setTypeface(typeface);
        ImageView imageView = findViewById(R.id.image_view);
        imageView.setImageResource(R.drawable.outline_star_half_white_48);
        imageView.setColorFilter(getResources().getColor(R.color.yellow));
        search_textView = findViewById(R.id.search_textView);
        search_textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, search_categories.class);
                startActivity(intent);
            }
        });

        LinearLayout searchLayout = findViewById(R.id.search_layout);
        for(int i = 0; i < searchLayout.getChildCount(); i++){
            View view = searchLayout.getChildAt(i);
            if(view instanceof ImageView){
                ImageView AllImage = (ImageView) view;
                AllImage.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
            }
        }

      ImageView ratingbar_1 = findViewById(R.id.ratingbar_1);
      ImageView ratingbar_2= findViewById(R.id.ratingbar_2);
      ImageView ratingbar_3 = findViewById(R.id.ratingbar_3);   
      ratingbar_1.setColorFilter(getResources().getColor(R.color.rating_gold),PorterDuff.Mode.SRC_IN);  
      ratingbar_2.setColorFilter(getResources().getColor(R.color.rating_gold),PorterDuff.Mode.SRC_IN);  
      ratingbar_3.setColorFilter(getResources().getColor(R.color.deep_green),PorterDuff.Mode.SRC_IN);  
      TextView text_view = findViewById(R.id.your_text_view);
      text_view.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        
        LinearLayout navigation_layout = findViewById(R.id.navigation_layout);
        nav_bar = navigation_bar_inflater.Navigators(this);
        navigation_layout.addView(nav_bar);
        loadCategroiesOption();
        hideSoftKeyboard();
       // allCartMethod();
    LoadCartMethod();
    scrollToTop();    
    }
    
private void LoadCartMethod(){
     LoadCartPromptInflater flater = new LoadCartPromptInflater(this);
     FrameLayout container = findViewById(R.id.cart_prompt_layout_view); 
     flater.LoadUi(container);
    
    GridLayout grid_layout = findViewById(R.id.grid_layout);
    if(grid_layout.getChildCount() > 0){
        for(int i = 0; i < grid_layout.getChildCount(); i++){
            View view = grid_layout.getChildAt(i);
            if(view instanceof LinearLayout){
                LinearLayout parentLayout = (LinearLayout) view;
                if(parentLayout.getChildCount() > 0){
                    LinearLayout mainSubLayout_1 = (LinearLayout) parentLayout.getChildAt(0);
                    ImageView itemImage = (ImageView) mainSubLayout_1.getChildAt(0);
                    TextView itemTitle = (TextView) mainSubLayout_1.getChildAt(1);
                    TextView price;
                    if(mainSubLayout_1.getChildCount() == 4){
                        TextView oldPrice = (TextView) mainSubLayout_1.getChildAt(2);
                        oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        price = (TextView) mainSubLayout_1.getChildAt(3);
                    }else{
                        price = (TextView) mainSubLayout_1.getChildAt(2);
                    }

                    mainSubLayout_1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Intent intent = new Intent(MainActivity.this, ItemPreview.class);
                            intent.putExtra("Item_id", "My_id");
                            startActivity(intent);
                        }
                    });
                    
                    LinearLayout salesLayout = null;
LinearLayout ratingLayout = null;

for(int q = 1; q < parentLayout.getChildCount(); q++){
    View Xiew = parentLayout.getChildAt(q);
    if(Xiew instanceof LinearLayout){
        LinearLayout layout = (LinearLayout) Xiew;
        if(layout.getChildCount() > 4 && layout.getChildAt(1) instanceof ImageView){
            ratingLayout = layout;
        } else if(layout.getChildCount() >= 2 && layout.getChildAt(0) instanceof TextView && layout.getChildAt(1) instanceof TextView){
            salesLayout = layout;
        }
    }
}

if(salesLayout != null){
    TextView sales = (TextView) salesLayout.getChildAt(0);
    TextView itemsLeft = (TextView) salesLayout.getChildAt(1);
}

if(ratingLayout != null){
    TextView totalRate = (TextView) ratingLayout.getChildAt(0);
    ImageView starI = (ImageView) ratingLayout.getChildAt(1);
    ImageView starII = (ImageView) ratingLayout.getChildAt(2);
    ImageView starIII = (ImageView) ratingLayout.getChildAt(3);
    starI.setColorFilter(getResources().getColor(R.color.rating_gold), PorterDuff.Mode.SRC_IN);
    starII.setColorFilter(getResources().getColor(R.color.rating_gold), PorterDuff.Mode.SRC_IN);
    starIII.setColorFilter(getResources().getColor(R.color.deep_green), PorterDuff.Mode.SRC_IN);
    TextView totalUsers = (TextView) ratingLayout.getChildAt(4);
}

                    if(parentLayout.getChildCount() > 1){
                        LinearLayout actionLayout = (LinearLayout) parentLayout.getChildAt(parentLayout.getChildCount() - 1);
                        for(int j = 0; j < actionLayout.getChildCount(); j++){
                            View viewInsideActionLayout = actionLayout.getChildAt(j);
                            if(viewInsideActionLayout instanceof Button){
                                Button addToCart = (Button) viewInsideActionLayout;
                                addToCartMethod(addToCart);
                            } else if(viewInsideActionLayout instanceof TextView && j == actionLayout.getChildCount() - 1){
                                TextView optionsBtn = (TextView) viewInsideActionLayout;
                                optionsBtn.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view){
                                        flater.openPrompt(container,optionsBtn);
                                        CustomToast.show(MainActivity.this, "You clicked option!");
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }
}
    
 private void addToCartMethod(Button button){
  button.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View view){
        CustomToast.show(MainActivity.this, "Item added");
    }  
  });   
 } 
   
   private void styleCatalogue(){
       LinearLayout cart_items_parent_layout = findViewById(R.id.cart_items_parent_layout);
       for(int i = 0; i < cart_items_parent_layout.getChildCount(); i++){
           
       } 
   }
    
  private void getDrawable(){
   /*   Drawable drawable = imageView1.getDrawable();
      Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
      imageView2.setImageBitmap(bitmap);*/
  } 
    
public void scrollToTop() {
    LinearLayout layout = findViewById(R.id.back_to_top_container);
    ScrollView scrollView = findViewById(R.id.scrollView);
    layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(View.FOCUS_UP);
                }
            });
        }
    });
    showBackToTop(layout,scrollView);
}
 private void showBackToTop(LinearLayout layout, ScrollView scrollView) {
     ImageView imageView = (ImageView) layout.getChildAt(1);
     imageView.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
     
   /* scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
        @Override
        public void onScrollChanged() {
            if (scrollView.getScrollY() > 100) { 
                layout.setVisibility(View.VISIBLE);
            } else {
                layout.setVisibility(View.GONE);
            }
        }
    });*/
     LinearLayout imageLayout = findViewById(R.id.imageLayout);
final boolean[] isScrolledDown = {false};
scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
    @Override
    public void onScrollChanged() {
        if (scrollView.getScrollY() > 0 && !isScrolledDown[0]) {
            isScrolledDown[0] = true;
            imageLayout.animate().translationY(-imageLayout.getHeight()).alpha(0).setDuration(300).withEndAction(new Runnable() {
                @Override
                public void run() {
                    imageLayout.setVisibility(View.GONE);
                }
            }).start();
        } else if (scrollView.getScrollY() == 0 && isScrolledDown[0]) {
            isScrolledDown[0] = false;
            imageLayout.setVisibility(View.VISIBLE);
            imageLayout.animate().translationY(0).alpha(1).setDuration(300).start();
        }
    }
});
     
}
    /*public interface CartCallback {
        void onCartUpdated(int count);
    }

    @Override
    public void onCartUpdated(int count) {
        // Update the cart count here
    }

    private void allCartMethod(){
        LinearLayout container = findViewById(R.id.load_cart_layout);
        //LoadCart_inflater loadCarts = new LoadCart_inflater(this);
        loadCarts.LoadUi(container);
        //loadCartInflater.updateCartCount();
       /* FrameLayout Container = findViewById(R.id.cart_prompt_layout_view);
        LoadCartPromptInflater promptInflater = new LoadCartPromptInflater(this);
        promptInflater.LoadUi(Container);
       if(subLayout != null){
            promptInflater.openPropmt(Container,subLayout);
        }
    }*/

   /* private Bitmap bitmap;
    public void loadCartMethod() {
        FrameLayout Container = findViewById(R.id.cart_prompt_layout_view);
        LoadCartPromptInflater promptInflater = new LoadCartPromptInflater(this);
        promptInflater.LoadUi(Container);
      
       loadCartInflater cart_loader_scrollView = view.findViewById(R.id.cart_loader_scrollView);
        LinearLayout parentLayout = (LinearLayout) cart_loader_scrollView.getChildAt(0);
        if (parentLayout.getChildCount() > 0) {
            for (int i = 0; i < parentLayout.getChildCount(); i++) {
                final LinearLayout subLayout = (LinearLayout) parentLayout.getChildAt(i);
                LinearLayout itemLayout = (LinearLayout) subLayout.getChildAt(0);
                itemId = String.valueOf(itemLayout.getId()); // Getting id_can_change_with_each_item
                if (subLayout.getChildCount() > 1) {
                    Button addBtn = (Button) subLayout.getChildAt(1); // Button is second child
                    Drawable drawable = getResources().getDrawable(R.drawable.outline_shopping_cart_white_48);
                    drawable.setBounds(0, 0, (int) (20 * getResources().getDisplayMetrics().density), (int) (20 * getResources().getDisplayMetrics().density));
                    addBtn.setCompoundDrawables(drawable, null, null, null);
                    addBtn.setCompoundDrawablePadding(5); // nice padding
                    ImageView itemImage = (ImageView) itemLayout.getChildAt(0);
                    Drawable drawableX = itemImage.getDrawable();
                    if (drawableX instanceof BitmapDrawable) {
                        bitmap = ((BitmapDrawable) drawableX).getBitmap(); // Use drawableX
                        TextView textView = (TextView) itemLayout.getChildAt(1);
                        ItemName = textView.getText().toString();
                        TextView pricetextView = (TextView) itemLayout.getChildAt(2);
                        ItemPrice = pricetextView.getText().toString();
                        //openPrompt(subLayout);
                        promptInflater.openPropmt(Container,subLayout);
                    }
                    addBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Add_to_cart(ItemName, bitmap, Integer.parseInt(ItemPrice.replace(",", "")), itemId, subLayout);
                        }
                    });
                }
            }
        } else {
            CustomToast.show(this, "No item load error");
        }
    }

    public void Add_to_cart(String itemName, Bitmap ItemImage, int price, String itemId, LinearLayout subLayout) {
        SharedPreferences prefs = getSharedPreferences("sets", MODE_PRIVATE);
        try {
            CryptoManager cryptoManager = new CryptoManager(this);
            String data = prefs.getString("data", "");
            String encryptedData;
            if (data.isEmpty()) {
                encryptedData = cryptoManager.encrypt(itemName + "," + price + "," + itemId + ",1");
            } else {
                String decryptedData = cryptoManager.decrypt(data);
                String[] items = decryptedData.split("\\|");
                boolean itemExists = false;
                StringBuilder sb = new StringBuilder();
                for (String item : items) {
                    String[] itemData = item.split(",");
                    if (itemData[2].equals(itemId)) {
                        int quantity = Integer.parseInt(itemData[3]) + 1;
                        sb.append(itemName + "," + price + "," + itemId + "," + quantity).append("|");
                        itemExists = true;
                    } else {
                        sb.append(item).append("|");
                    }
                }
                if (!itemExists) {
                    sb.append(itemName + "," + price + "," + itemId + ",1").append("|");
                }
                encryptedData = cryptoManager.encrypt(sb.toString().replaceAll("\\|$", ""));
            }
            prefs.edit().putString("data", encryptedData).apply();
            updateCartCount();
            showPrompt(subLayout);
        } catch (Exception e) {
            CustomToast.show(this, "Error loading cart");
        }
    }

    public void updateCartCount() {
        SharedPreferences prefs = getSharedPreferences("sets", MODE_PRIVATE);
        try {
            CryptoManager cryptoManager = new CryptoManager(this);
            String data = prefs.getString("data", "");
            if (data.isEmpty()) {
                onCartUpdated(0);
            } else {
                String decryptedData = cryptoManager.decrypt(data);
                String[] items = decryptedData.split("\\|");
                int totalItems = 0;
                for (String item : items) {
                    String[] itemData = item.split(",");
                    totalItems += Integer.parseInt(itemData[3]);
                }
                onCartUpdated(totalItems);
            }
        } catch (Exception e) {
            CustomToast.show(this, "Error loading cart");
        }
    }

    public void showPrompt(LinearLayout subLayout) {
        FrameLayout Container = findViewById(R.id.cart_prompt_layout_view);
        LoadCartPromptInflater promptInflater = new LoadCartPromptInflater(this);
        promptInflater.LoadUi(Container);
        if(subLayout != null){
            promptInflater.openPropmt(Container,subLayout);
        }
    }
*/
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

    private String selectedText = "";
    private TextView previouslySelectedTextView = null;
    private void loadCategroiesOption(){
        LinearLayout categories_options = findViewById(R.id.categories_options);
        categories_options.removeAllViews();
        
         String[] categories = getResources().getStringArray(R.array.search_categories);
        HorizontalScrollView scrollView = new HorizontalScrollView(this);
        LinearLayout.LayoutParams scrollParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        scrollView.setLayoutParams(scrollParam);
        scrollView.setPadding(0, 10, 0, 10);
        scrollView.setHorizontalScrollBarEnabled(false);
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(layoutParam);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setBackground(null);
        for (int i = 0; i < categories.length; i++) {
            final TextView textView = new TextView(this);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textParams.setMargins(8, 0, 8, 0);
            textView.setLayoutParams(textParams);
            textView.setTextColor(Color.WHITE);
            textView.setText(categories[i]);
            final String category = categories[i];
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (previouslySelectedTextView != null) {
                        previouslySelectedTextView.setTextColor(Color.WHITE);
                    }
                    textView.setTextColor(getResources().getColor(R.color.light_gold));
                    previouslySelectedTextView = textView;
                    selectedText = category;
                    encryptString(selectedText);
                    CustomToast.show(MainActivity.this, selectedText);
                }
            });
            layout.addView(textView);
        }
        layout.setBackgroundColor(Color.TRANSPARENT);
        scrollView.addView(layout);
        scrollView.setBackgroundColor(Color.TRANSPARENT);
        categories_options.addView(scrollView);
        loadSelected();
    }

    private void saveSelected(String selected){
        SharedPreferences data_selected = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = data_selected.edit();
        editor.putString("is_selected",selected);
        editor.commit();
    }

    private void encryptString(String data){
        try{
            CryptoManager cryptoManager = new CryptoManager(this);
            String newData = cryptoManager.encrypt(data);
            saveSelected(newData);
        }catch(Exception e){
            CustomToast.show(this, e.getMessage());
        }
    }

    private void DecryptSelected(){
        SharedPreferences preference = getSharedPreferences("data", MODE_PRIVATE);
        String values = preference.getString("is_selected", "");
        LinearLayout categories_options = findViewById(R.id.categories_options);
        try {
            if(!values.isEmpty() || !values.equals("")){
                CryptoManager cryptoManager = new CryptoManager(this);
                String decryptedData = cryptoManager.decrypt(values);
                if (decryptedData != null && !decryptedData.isEmpty()) {
                    selectedText = decryptedData;
                } else {
                    selectedText = "All";
                    encryptString(selectedText);
                }
            }else{
                selectedText = "All";
                encryptString(selectedText);
            }
            HorizontalScrollView scrollView = (HorizontalScrollView) categories_options.getChildAt(0);
            LinearLayout layoutInsideScrollView = (LinearLayout) scrollView.getChildAt(0);
            for (int i = 0; i < layoutInsideScrollView.getChildCount(); i++) {
                View view = layoutInsideScrollView.getChildAt(i);
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    if (textView.getText().toString().equals(selectedText)) {
                        textView.setTextColor(getResources().getColor(R.color.light_gold));
                        previouslySelectedTextView = textView;
                        textView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int scrollX = textView.getLeft();
                                if (scrollX < scrollView.getScrollX()) {
                                    scrollView.smoothScrollTo(scrollX, 0);
                                } else if (scrollX + textView.getWidth() > scrollView.getScrollX() + scrollView.getWidth()) {
                                    scrollView.smoothScrollTo(scrollX + textView.getWidth() - scrollView.getWidth(), 0);
                                }
                            }
                        },1000);
                    } else {
                        textView.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        } catch (Exception e) {
            CustomToast.show(this, e.getMessage());
        }
    }

    private void loadSelected(){
        DecryptSelected();
    }

    public int dpToPx (int dp){
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    protected void onResume(){
        super.onResume();
        View navLayout = nav_bar;
        navLayout.postDelayed(new Runnable(){
            @Override
            public void run(){
                navigation_bar_inflater.changeAllTheme();
                navigation_bar_inflater.ChangeFirst();
            }
        },50);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        CustomToast.show(this, "Press back again to exit");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}