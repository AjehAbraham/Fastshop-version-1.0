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
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.inputmethod.InputMethodManager;

import android.util.DisplayMetrics;
import android.widget.ScrollView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;

import android.content.SharedPreferences;

public class cart_items extends Activity{
     private View nav_bar;
     private ListView listView;
     private LinearLayout cart_items_layout;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_items);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.home_page)); 
         
    LinearLayout  header = findViewById(R.id.header);
        View count = header.getChildAt(0);
        if(count instanceof ImageView){
            ImageView image = (ImageView) count;
            image.setColorFilter(Color.BLACK);
            image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            // Intent intent = new Intent(cart_items.this, MainActivity.class);
              //startActivity(intent);
               finish(); 
            }    
            });
        }    
     ImageView title_image = findViewById(R.id.title_image);
     title_image.setColorFilter(Color.BLACK);
     LinearLayout cart_items_layout = findViewById(R.id.cart_items_layout);
    // styleImage(cart_items_layout);
     
     ScrollView scroll = findViewById(R.id.scroll);
    // scrollViewHeight(scroll);
    // scroll.bringToFront();
     //scroll.invalidate();
     
    LinearLayout  clear_cart_checkout_layout= findViewById(R.id.clear_cart_checkout_layout);  
    FrameLayout clear_cart_item_prompt_layout = findViewById(R.id.clear_cart_item_prompt_layout);
     LinearLayout continue_btn = findViewById(R.id.continue_btn);
     LinearLayout clear_all_btn = findViewById(R.id.clear_all_btn);
     
       clear_cart_checkout_layout.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v){
      
      clear_cart_item_prompt_layout.setVisibility(View.VISIBLE);
     }
     }); 
     
  continue_btn.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v){
      
      clear_cart_item_prompt_layout.setVisibility(View.GONE);
     }
     });     
     
   View Xiew =   clear_all_btn.getChildAt(1);
     if(Xiew instanceof ImageView ){
      ImageView Ximage = (ImageView) Xiew;
      Ximage.setColorFilter(Color.BLACK);
     }
    clear_all_btn.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v){
      clear_cart_item_prompt_layout.setVisibility(View.GONE);
     CustomToast.show(cart_items.this, "All cart items cleared");
      redirect();
     }
     });     
      
     
     LinearLayout checkout_layout = findViewById(R.id.checkout_layout);
     
     checkout_layout.setOnClickListener(new View.OnClickListener(){
     @Override
      public void onClick(View view){
       Intent intent = new Intent(cart_items.this, check_out.class);
        startActivity(intent);
      }
     });
     
     
    cart_items_layout = findViewById(R.id.cart_items_layout); 
     changeCartTheme();
     
    }
 
  private void redirect(){
   new Handler().postDelayed(new Runnable(){
    @Override
    public void run(){
     Intent intent = new Intent(cart_items.this, MainActivity.class);
     startActivity(intent);
     finish();
    }
   },2000);
  }
 
 /*private void changeCartTheme() {
  EditText editText = null;
  LinearLayout cart_items_layout = findViewById(R.id.cart_items_layout);
    try {
        if (cart_items_layout != null && cart_items_layout.getChildCount() > 0) {
            for (int i = 0; i < cart_items_layout.getChildCount(); i++) {
                View view = cart_items_layout.getChildAt(i);
                if (view instanceof LinearLayout) {
                    LinearLayout parentLayout = (LinearLayout) view;
                    if (parentLayout.getChildCount() > 0) {
                        View childView1 = parentLayout.getChildAt(0);
                        if (childView1 instanceof LinearLayout) {
                            LinearLayout horizontalLayout1 = (LinearLayout) childView1;
                            ImageView itemImage = (ImageView) horizontalLayout1.getChildAt(0);
                            TextView sizeTextView = (TextView) horizontalLayout1.getChildAt(2);
                            TextView quantityTextView = (TextView) horizontalLayout1.getChildAt(3);
                             editText = (EditText) horizontalLayout1.getChildAt(4);
                            ImageView deleteBtn = (ImageView) horizontalLayout1.getChildAt(5);
                            deleteBtn.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outline_expand_more_white_48);
                            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, dpToPx(20), dpToPx(20), false);
                            Drawable drawable = new BitmapDrawable(getResources(), scaledBitmap);
                            drawable.setColorFilter(getResources().getColor(R.color.grey),PorterDuff.Mode.SRC_IN);
                            editText.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
           
                            setupQuantityEditText(editText);
                        }
                        View childView2 = parentLayout.getChildAt(1);
                        if (childView2 instanceof LinearLayout) {
                            LinearLayout horizontalLayout2 = (LinearLayout) childView2;
                            TextView itemNameTextView = (TextView) horizontalLayout2.getChildAt(0);
                            TextView itemPrice = (TextView) horizontalLayout2.getChildAt(1);
                            TextView subTotalTextViewLabel = (TextView) horizontalLayout2.getChildAt(2);
                            TextView subTotalTextView = (TextView) horizontalLayout2.getChildAt(3);
                         if(editText != null){
                            setPrice(itemPrice,subTotalTextView,editText);   
                         }
                        }
                    }
                }
            }
        }
    } catch (Exception e) {
        CustomToast.show(this, e.getMessage());
    }
}
 
 
private void setupQuantityEditText(EditText editText) {
    final EditText currentEditText = editText;
    editText.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FrameLayout list_view_layout = findViewById(R.id.list_view_layout);
            list_view_layout.setVisibility(View.VISIBLE);
            ListView listView = findViewById(R.id.listView);
            String[] numbers = new String[99];
            for (int j = 0; j < 99; j++) {
                numbers[j] = String.valueOf(j + 1);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(cart_items.this, android.R.layout.simple_list_item_1, numbers);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedText = (String) parent.getItemAtPosition(position);
                    currentEditText.setText(selectedText);
                    FrameLayout list_view_layout = findViewById(R.id.list_view_layout);
                    list_view_layout.setVisibility(View.GONE);
                    hideSoftKeyboard();
                }
            });
        }
    });
   FrameLayout list_view_layout = findViewById(R.id.list_view_layout);
    ImageView close_layout = findViewById(R.id.close_layout);
     close_layout.setColorFilter(Color.BLACK);    
     close_layout.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v){
      list_view_layout.setVisibility(View.GONE);
      hideSoftKeyboard();
     }
     }); 
     
}
 
 private void setPrice(TextView price, TextView subTotal, EditText editText) {
    try {
        String data = editText.getText().toString();
        long newData = Long.parseLong(data);
        String oldPriceString = price.getText().toString().replace("₦", "").replace(",", "");
        long oldPrice = Long.parseLong(oldPriceString);
        long newPrice = oldPrice * newData;
        subTotal.setText("₦" + String.format("%,d", newPrice));
        updateTotalPrice();
    } catch (Exception e) {
        CustomToast.show(this, e.getMessage());
    }
}

private void updateTotalPrice() {
    LinearLayout cart_items_layout = findViewById(R.id.cart_items_layout);
    long totalPrice = 0;
    try {
        if (cart_items_layout != null && cart_items_layout.getChildCount() > 0) {
            for (int i = 0; i < cart_items_layout.getChildCount(); i++) {
                View view = cart_items_layout.getChildAt(i);
                if (view instanceof LinearLayout) {
                    LinearLayout parentLayout = (LinearLayout) view;
                    if (parentLayout.getChildCount() > 1) {
                        View childView2 = parentLayout.getChildAt(1);
                        if (childView2 instanceof LinearLayout) {
                            LinearLayout horizontalLayout2 = (LinearLayout) childView2;
                            TextView subTotalTextView = (TextView) horizontalLayout2.getChildAt(2);
                            String subTotalString = subTotalTextView.getText().toString().replace("₦", "").replace(",", "");
                            totalPrice += Long.parseLong(subTotalString);
                        }
                    }
                }
            }
        }
        TextView total = findViewById(R.id.all_total);
        total.setText("₦" + String.format("%,d", totalPrice));
    } catch (Exception e) {
        CustomToast.show(this, e.getMessage());
    }
}
 */
 
// Your existing code...

private void changeCartTheme() {
    LinearLayout cart_items_layout = findViewById(R.id.cart_items_layout);
    try {
        if (cart_items_layout != null && cart_items_layout.getChildCount() > 0) {
            for (int i = 0; i < cart_items_layout.getChildCount(); i++) {
                View view = cart_items_layout.getChildAt(i);
                if (view instanceof LinearLayout) {
                    LinearLayout parentLayout = (LinearLayout) view;
                    if (parentLayout.getChildCount() > 0) {
                        View childView1 = parentLayout.getChildAt(0);
                        if (childView1 instanceof LinearLayout) {
                            LinearLayout horizontalLayout1 = (LinearLayout) childView1;
                            ImageView itemImage = (ImageView) horizontalLayout1.getChildAt(0);
                            TextView sizeTextView = (TextView) horizontalLayout1.getChildAt(2);
                            TextView quantityTextView = (TextView) horizontalLayout1.getChildAt(3);
                            EditText editText = (EditText) horizontalLayout1.getChildAt(4);
                            ImageView deleteBtn = (ImageView) horizontalLayout1.getChildAt(5);
                            deleteBtn.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outline_expand_more_white_48);
                            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, dpToPx(20), dpToPx(20), false);
                            Drawable drawable = new BitmapDrawable(getResources(), scaledBitmap);
                            drawable.setColorFilter(getResources().getColor(R.color.grey),PorterDuff.Mode.SRC_IN);
                            editText.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
                            View childView2 = parentLayout.getChildAt(1);
                            if (childView2 instanceof LinearLayout) {
                                LinearLayout horizontalLayout2 = (LinearLayout) childView2;
                                TextView itemNameTextView = (TextView) horizontalLayout2.getChildAt(0);
                                TextView itemPrice = (TextView) horizontalLayout2.getChildAt(1);
                                String basePriceString = itemPrice.getText().toString().replaceAll("[^0-9]", "");
                                itemPrice.setTag(basePriceString);
                                TextView subTotalTextViewLabel = (TextView) horizontalLayout2.getChildAt(2);
                                TextView subTotalTextView = (TextView) horizontalLayout2.getChildAt(3);
                                setupQuantityEditText(editText, itemPrice, subTotalTextView);
                                setPrice(itemPrice, subTotalTextView, editText);
                            }
                        }
                    }
                }
            }
        }
    } catch (Exception e) {
        CustomToast.show(this, e.getMessage());
    }
}

private void setupQuantityEditText(final EditText editText, final TextView price, final TextView subTotal) {
    final TextView priceView = price;
    final TextView subTotalView = subTotal;
    editText.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FrameLayout list_view_layout = findViewById(R.id.list_view_layout);
            list_view_layout.setVisibility(View.VISIBLE);
            ListView listView = findViewById(R.id.listView);
            String[] numbers = new String[99];
            for (int j = 0; j < 99; j++) {
                numbers[j] = String.valueOf(j + 1);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(cart_items.this, android.R.layout.simple_list_item_1, numbers);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedText = (String) parent.getItemAtPosition(position);
                    editText.setText(selectedText);
                    setPrice(priceView, subTotalView, editText);
                    FrameLayout list_view_layout = findViewById(R.id.list_view_layout);
                    list_view_layout.setVisibility(View.GONE);
                    hideSoftKeyboard();
                }
            });
        }
    });
    FrameLayout list_view_layout = findViewById(R.id.list_view_layout);
    ImageView close_layout = findViewById(R.id.close_layout);
    close_layout.setColorFilter(Color.BLACK);
    close_layout.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            list_view_layout.setVisibility(View.GONE);
            hideSoftKeyboard();
        }
    });
}

private void setPrice(TextView priceView, TextView subTotal, EditText editText) {
    try {
        String basePriceString = priceView.getTag().toString();
        long basePrice = Long.parseLong(basePriceString);
        String data = editText.getText().toString();
        long newData = Long.parseLong(data);
        long newPrice = basePrice * newData;
        priceView.setText("₦" + String.format("%,d", basePrice) + " * " + newData);
        subTotal.setText("₦" + String.format("%,d", newPrice));
        updateTotalPrice();
    } catch (Exception e) {
        CustomToast.show(this, e.getMessage());
    }
}

private void updateTotalPrice() {
    LinearLayout cart_items_layout = findViewById(R.id.cart_items_layout);
    long totalPrice = 0;
    try {
        if (cart_items_layout != null && cart_items_layout.getChildCount() > 0) {
            for (int i = 0; i < cart_items_layout.getChildCount(); i++) {
                View view = cart_items_layout.getChildAt(i);
                if (view instanceof LinearLayout) {
                    LinearLayout parentLayout = (LinearLayout) view;
                    if (parentLayout.getChildCount() > 1) {
                        View childView2 = parentLayout.getChildAt(1);
                        if (childView2 instanceof LinearLayout) {
                            LinearLayout horizontalLayout2 = (LinearLayout) childView2;
                            TextView priceTextView = (TextView) horizontalLayout2.getChildAt(1);
                            String priceString = priceTextView.getText().toString().replaceAll("[^0-9]", "");
                            if (!priceString.isEmpty()) {
                                totalPrice += Long.parseLong(priceString);
                            }
                        }
                    }
                }
            }
        }
        TextView total = findViewById(R.id.all_total);
        total.setText("₦" + String.format("%,d", totalPrice));
    } catch (Exception e) {
        CustomToast.show(this, e.getMessage());
    }
} 
 
 private void filterList(String query) {
    ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
    adapter.getFilter().filter(query);
    if (adapter.getCount() == 0) {
      //  listView.setVisibility(View.GONE);
    } else {
        //listView.setVisibility(View.VISIBLE);
    }
}
 
  private ArrayAdapter<String> createAdapter(String[] items) {
    return new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items) {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            //view.setBackgroundResource(R.drawable.border_spinner);
            return view;
        }
    };
    }
 private void hideSoftKeyboard(){
    View view = getWindow().getCurrentFocus();
    if (view != null) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    } else {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }
}
 public int dpToPx (int dp){
 float density = getResources().getDisplayMetrics().density;
  return Math.round((float) dp * density);
 
 }
 
 private void getCartItems() {
    SharedPreferences prefs = getSharedPreferences("sets", MODE_PRIVATE);
    try {
        CryptoManager cryptoManager = new CryptoManager(this);
        String data = prefs.getString("data", "");
        if (!data.isEmpty()) {
            String decryptedData = cryptoManager.decrypt(data);
            String[] items = decryptedData.split("\\|");
            int total = 0;
            for (String item : items) {
                String[] itemData = item.split(",");
                String itemName = itemData[0];
                int price = Integer.parseInt(itemData[1]);
                String itemId = itemData[2];
                int quantity = Integer.parseInt(itemData[3]);
                int subTotal = price * quantity;
                total += subTotal;
                // You can use the item data as needed
              //  Log.d("Cart Item", "Item Name: " + itemName + ", Price: " + price + ", Quantity: " + quantity + ", Subtotal: " + subTotal);
            }
           // Log.d("Total", "Total: " + total);
        } else {
            CustomToast.show(this, "Cart is empty");
        }
    } catch (Exception e) {
        CustomToast.show(this, "Error loading cart");
    }
}
 
  @Override
 protected void onResume(){
  super.onResume();
  getCartItems();
 }
   
   @Override
   public void onBackPressed(){
       finish();
   }  


}