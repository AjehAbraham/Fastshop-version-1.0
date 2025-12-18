package com.fastshop.myApp;

import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.PorterDuff;
import android.graphics.Color;
import android.widget.ListView;
import android.widget.AdapterView;
import java.util.Calendar;
import android.widget.Button;
import android.widget.NumberPicker;
import android.content.Intent;
import android.content.SharedPreferences;


public class credit_bal extends Activity{
    private ImageView bal_visibility,close_bank_options;
    private LinearLayout bank_options_menu,open_bank_options;
    private String selectedBank;
    private int selectedBankImage;
    private TextView show_card_prompt,show_bank_prompt;
    private LinearLayout bank_top_up_container_main,card_top_up_container_main;
    private View line1,line2;
    private LinearLayout top_up_prompt;
    private TextView balance_text;
    private String MyBalance ;
    @Override
    protected void onCreate(Bundle saveInstanceState){
     super.onCreate(saveInstanceState);
     setContentView(R.layout.credit_bal);
     ActionBar actionBar = getActionBar();
     actionBar.hide();
     Window window = getWindow();
     window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
     window.setStatusBarColor(getResources().getColor(R.color.home_page));
      
     ImageView close_page = findViewById(R.id.close_page);
     close_page.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
     close_page.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
       finish();
      }
     }); 
     
    top_up_prompt = findViewById(R.id.top_up_prompt);
     
    ImageView close_top_up_btn = findViewById(R.id.close_top_up_btn);
    close_top_up_btn.setColorFilter(getResources().getColor(R.color.black));
     close_top_up_btn.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
       top_up_prompt.setVisibility(View.GONE);
      }
     });
     
    TextView open_top_up_btn = findViewById(R.id.open_top_up_btn);
     open_top_up_btn.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v){
      load_top_up();
      top_up_prompt.setVisibility(View.VISIBLE);
     }
     });     
    ImageView banks_option_prompt = findViewById(R.id.banks_option_prompt);
     banks_option_prompt.setColorFilter(Color.GRAY);
     
     
   bal_visibility = findViewById(R.id.bal_visibility);
    bal_visibility.setColorFilter(Color.BLACK); 
      
     LinearLayout parentLayout = findViewById(R.id.parentLayout);
     loadFont(parentLayout);
     
    bank_options_menu = findViewById(R.id.bank_options_menu); 
    open_bank_options = findViewById(R.id.open_bank_options);
    close_bank_options = findViewById(R.id.close_bank_options);
    close_bank_options.setColorFilter(Color.GRAY); 
     
    open_bank_options.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v){
     bank_options_menu.setVisibility(View.VISIBLE);
     }
    });  
    close_bank_options.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v){
     bank_options_menu.setVisibility(View.GONE);
     }
    });
   //top_up_prompt.inValidate();
     //top_up_prompt.bringToFront();
     
     card_top_up_container_main = findViewById(R.id.card_top_up_container_main);
     bank_top_up_container_main = findViewById(R.id.bank_top_up_container_main);
     show_card_prompt = findViewById(R.id.show_card_prompt);
     show_bank_prompt = findViewById(R.id.show_bank_prompt);
      line1 = findViewById(R.id.line_1);
      line2 = findViewById(R.id.line_2);
     
     show_bank_prompt.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
        bank_top_up_container_main.setVisibility(View.VISIBLE);
        card_top_up_container_main.setVisibility(View.GONE);
        line1.setBackgroundColor(getResources().getColor(R.color.grey));
        line2.setBackgroundColor(getResources().getColor(R.color.home_page));
    }
});

    show_card_prompt.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
        card_top_up_container_main.setVisibility(View.VISIBLE);
        bank_top_up_container_main.setVisibility(View.GONE);
        line1.setBackgroundColor(getResources().getColor(R.color.home_page));
        line2.setBackgroundColor(getResources().getColor(R.color.grey));
    }
});
     picker = findViewById(R.id.picker);
     Button close_picker = findViewById(R.id.close_picker);
     close_picker.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
     picker.setVisibility(View.GONE);
     }
     }); 
     
    TextView open_saved_details = findViewById(R.id.open_saved_details);
     open_saved_details.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
     Intent intent = new Intent(credit_bal.this, top_up_balance.class);
     startActivity(intent);
     }
     }); 
     
     load_top_up();
     add_Adapter();
     loadDate();
     
     MyBalance = "₦48,550.89";
     balance_text = findViewById(R.id.balance_text);
     toggleBalance(bal_visibility,MyBalance,balance_text);
     
     toggleBalance(bal_visibility,MyBalance,balance_text);
     
    }
 
    private void load_top_up(){
        bank_top_up_container_main.setVisibility(View.VISIBLE);
        card_top_up_container_main.setVisibility(View.GONE);
        line1.setBackgroundColor(getResources().getColor(R.color.grey));
        line2.setBackgroundColor(getResources().getColor(R.color.home_page));
    }
 
 private void loadFont(LinearLayout parentLayout){
  for(int i = 0; i < parentLayout.getChildCount(); i ++){
   View view = parentLayout.getChildAt(i);
   if(view instanceof TextView){
    TextView textView = (TextView) view;
    Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Poppins-Regular.ttf");
    textView.setTypeface(typeface);
   }
  }
 }
 
 
  private void add_Adapter() {
    ListView listView = findViewById(R.id.listView);
    String[] options = getResources().getStringArray(R.array.banks);
    int[] images = {R.drawable.lazerwave_logo, R.drawable.zenith_bank_logo,
     R.drawable.access_bank_logo, R.drawable.kuda_logo, R.drawable.opay_logo, 
     R.drawable.palmpay_logo, R.drawable.moniepoint_logo};
     
    CustomAdapter adapter = new CustomAdapter(this, options, images);
    listView.setAdapter(adapter);
    listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    //    ListView listView = findViewById(R.id.listView);
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedBank = options[position];
        selectedBankImage = images[position];
        bank_options_menu.setVisibility(View.GONE);
        loadSelectedBank();
    }
});
}
 
 
   private void loadSelectedBank() {
    TextView bankNameTextView = findViewById(R.id.bank_textView);
    ImageView bankLogoImageView = findViewById(R.id.bank_imageView);

    bankNameTextView.setText(selectedBank);
    bankLogoImageView.setImageResource(selectedBankImage);
}

 private int selectedMonth;
 private int selectedYear;
 private LinearLayout picker;
   private void loadDate() {
    NumberPicker monthPicker = findViewById(R.id.monthPicker);
    NumberPicker yearPicker = findViewById(R.id.yearPicker);
    final TextView show_selected_date = findViewById(R.id.show_selected_date);
    //picker = findViewById(R.id.picker);  
    show_selected_date.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v){
      picker.setVisibility(View.VISIBLE);
     }
    });
    
    String[] months = new String[12];
    for (int i = 0; i < 12; i++) {
        months[i] = String.format("%02d", i + 1);
    }
    monthPicker.setMinValue(0);
    monthPicker.setMaxValue(11);
    monthPicker.setDisplayedValues(months);

    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    yearPicker.setMinValue(currentYear);
    yearPicker.setMaxValue(currentYear + 6);

    NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            if (picker == monthPicker) {
                selectedMonth = newVal + 1;
            } else {
                selectedYear = newVal;
            }
            show_selected_date.setText(String.format("%02d/%d", selectedMonth, selectedYear));
        }
    };

    monthPicker.setOnValueChangedListener(onValueChangeListener);
    yearPicker.setOnValueChangedListener(onValueChangeListener);

}
 
   private void toggleBalance(ImageView visibility_btn, final String MyBalance, final TextView balance_text){
    SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
    String BalanceStatus = prefs.getString("myBalanceStatus","show");
    if(!BalanceStatus.isEmpty()){
        try {
            CryptoManager cryptoManger = new CryptoManager(this);
            BalanceStatus = cryptoManger.decrypt(BalanceStatus);
        } catch (Exception e) {
            BalanceStatus = "show";
        }
    }
    loadBalanceStatus(balance_text, MyBalance);
    visibility_btn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
            final SharedPreferences.Editor editor = prefs.edit();
            String BalanceStatus = prefs.getString("myBalanceStatus","show");
            if(!BalanceStatus.isEmpty()){
                try {
                    CryptoManager cryptoManger = new CryptoManager(credit_bal.this);
                    BalanceStatus = cryptoManger.decrypt(BalanceStatus);
                } catch (Exception e) {
                    BalanceStatus = "show";
                }
            }
            if(BalanceStatus.equalsIgnoreCase("hide")){
                try {
                    CryptoManager cryptoManager = new CryptoManager(credit_bal.this);
                    editor.putString("myBalanceStatus", cryptoManager.encrypt("show"));
                    editor.apply();
                } catch (Exception e) {
                    // Handle exception
                }
            }else{
                try {
                    CryptoManager cryptoManager = new CryptoManager(credit_bal.this);
                    editor.putString("myBalanceStatus", cryptoManager.encrypt("hide"));
                    editor.apply();
                } catch (Exception e) {
                    // Handle exception
                }
            }
            loadBalanceStatus(balance_text, MyBalance);
        }
    });
}
private void loadBalanceStatus(TextView balance_text, String MyBalance){
    SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
    String BalanceStatus = prefs.getString("myBalanceStatus","show");
    if(!BalanceStatus.isEmpty()){
        try {
            CryptoManager cryptoManger = new CryptoManager(this);
            BalanceStatus = cryptoManger.decrypt(BalanceStatus);
        } catch (Exception e) {
            BalanceStatus = "show";
        }
    }
    if(BalanceStatus.equalsIgnoreCase("hide")){
        balance_text.setText("*****");
        bal_visibility.setImageResource(R.drawable.outline_visibility_off_white_48);
    }else{
        balance_text.setText(MyBalance);
        bal_visibility.setImageResource(R.drawable.outline_visibility_white_48);
    }
}
 
 
     @Override
     protected void onResume(){
      super.onResume();
     }
 
 
 
}