package com.fastshop.myApp;

import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
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
import android.view.inputmethod.InputMethodManager;

public class Add_card_account extends Activity{
    private LinearLayout bank_options_menu;
    private String selectedBank;
    private int selectedBankImage;
   @Override
    protected void onCreate(Bundle saveInstanceState){
     super.onCreate(saveInstanceState);
     setContentView(R.layout.add_card_account);   
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
              picker = findViewById(R.id.picker);
     Button close_picker = findViewById(R.id.close_picker);
     close_picker.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
     picker.setVisibility(View.GONE);
     }
     }); 
     
     load_top_up();
     add_Adapter();
     loadDate();
    }
    
   private TextView textView1,textView2;
   private View view1,view2;
private void load_top_up(){
    LinearLayout subLayout = findViewById(R.id.subLayout);
    LinearLayout textLayout = (LinearLayout) subLayout.getChildAt(0);
    textView1 = (TextView) textLayout.getChildAt(0);
    textView2 = (TextView) textLayout.getChildAt(1);

    LinearLayout viewLayout = (LinearLayout) subLayout.getChildAt(1);
    view1 = viewLayout.getChildAt(0);
    view2 = viewLayout.getChildAt(1);
   LinearLayout cardLayout = findViewById(R.id.card_top_up_container_main);
   LinearLayout accountLayout = findViewById(R.id.bank_top_up_container_main);
   
   textView1.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
     view1.setBackgroundResource(R.drawable.cornered_default);
     view2.setBackground(null);
     cardLayout.setVisibility(View.VISIBLE);
     accountLayout.setVisibility(View.GONE);
     textView1.setTypeface(null, Typeface.BOLD);
     textView2.setTypeface(null, Typeface.NORMAL);
    }
   });
 
    textView2.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
     view2.setBackgroundResource(R.drawable.cornered_default);
     view1.setBackground(null);
     accountLayout.setVisibility(View.VISIBLE);
     cardLayout.setVisibility(View.GONE);
     textView2.setTypeface(null, Typeface.BOLD);
     textView1.setTypeface(null, Typeface.NORMAL);
    }
   });
 
 selectDefault(cardLayout,accountLayout,view1,view2,textView1,textView2);
}
 private void selectDefault(LinearLayout cardLayout,LinearLayout accountLayout,View view1,View view2, TextView textView1, TextView textView2){
     view1.setBackgroundResource(R.drawable.cornered_default);
     view2.setBackground(null);
     cardLayout.setVisibility(View.VISIBLE);
     accountLayout.setVisibility(View.GONE);
     textView1.setTypeface(null, Typeface.BOLD);
     textView2.setTypeface(null, Typeface.NORMAL);
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
   LinearLayout bank_options_menu = findViewById(R.id.bank_options_menu);
   //bank_options_menu.setVisibility(View.VISIBLE);
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
   ImageView closeList = findViewById(R.id.close_bank_options);
   closeList.setColorFilter(Color.BLACK);
   closeList.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
     bank_options_menu.setVisibility(View.GONE);
    }
   });
   
   ImageView imageView = findViewById(R.id.banks_option_prompt);
   imageView.setColorFilter(Color.BLACK);
   imageView.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
     bank_options_menu.setVisibility(View.VISIBLE);
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
      hideSoftKeyboard();
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
 private void hideSoftKeyboard(){
  View view = getCurrentFocus();
  if(view != null){
   InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
   imm.hideSoftInputFromWindow(view.getWindowToken(),0);
  }
 }
 
}