package com.fastshop.myApp;


import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import java.text.DecimalFormat;

public class CartSortingInflater{
    private Context context;
    private View view;
    private ArrayAdapter<String> adapter;
    public CartSortingInflater(Context context){
        this.context = context;
    }
    public void LoadUi(FrameLayout container){
        LayoutInflater flater = LayoutInflater.from(context);
             container.removeAllViewsInLayout();
              container.setVisibility(View.VISIBLE);
              view = flater.inflate(R.layout.cart_sorting_inflater,container, true);
       /* imageView.setOnClickListener(new View.OnClickListener(){
         @Override
          public void onClick(View v){
              container.removeAllViewsInLayout();
              container.setVisibility(View.VISIBLE);
              view = flater.inflate(R.layout.cart_sorting_inflater,container, true);  
          }  
        });*/
        closeInflater(container);
        loadAdapter(container);
        startSorting(container);
    }
    private void closeInflater(FrameLayout container){
     LinearLayout layout = view.findViewById(R.id.parentLayout);
     ImageView closeBtn = (ImageView) layout.getChildAt(0);
       closeBtn.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);
        closeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                container.setVisibility(View.GONE);
                container.removeAllViews();
            }
        });
    }
    
 
private String selectedCategory = "";

private void loadAdapter(FrameLayout container){
    Button button = view.findViewById(R.id.sort_btn);
    Spinner spinner = view.findViewById(R.id.spinner);
    String[] array = context.getResources().getStringArray(R.array.sorting_categories);
    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, array);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedCategory = (String) parent.getItemAtPosition(position);
            openPricingRange(selectedCategory);
            brandMethod(container, selectedCategory,button);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    });
}

private void startSorting(FrameLayout container){
    Button button = view.findViewById(R.id.sort_btn);
    button.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            EditText editText = view.findViewById(R.id.editText);
            String Value = editText.getText().toString();
            if (selectedCategory.equalsIgnoreCase("Pricing range")) {
                if (Value.isEmpty() || Integer.parseInt(Value.replace(",", "")) < 500) {
                    CustomToast.show(context, "Please enter a value >= 500");
                    return;
                }
            } else {
                if (Value.isEmpty()) {
                    container.setVisibility(View.GONE);
                    container.removeAllViews();
                    CustomToast.show(context, "Sorting by " + selectedCategory);
                    return;
                }
            }
            try {
                long newValue = Long.parseLong(Value.replace(",", ""));
                container.setVisibility(View.GONE);
                container.removeAllViews();
                CustomToast.show(context, "Sorting by " + selectedCategory);
            } catch (NumberFormatException e) {
                CustomToast.show(context, "Invalid value");
            }
        }
    });
}
private void openPricingRange(String selectedText){
    LinearLayout rangeContainer = view.findViewById(R.id.pricing_range_container);
    Button button = view.findViewById(R.id.sort_btn);
    if(selectedText.equalsIgnoreCase("Pricing range") || selectedText.equalsIgnoreCase("price range")){
        rangeContainer.setVisibility(View.VISIBLE);
        seekbarProgess();
        button.setEnabled(false);
        button.setBackgroundResource(R.drawable.cornered_lighter_default);
    }else{
        rangeContainer.setVisibility(View.GONE);
        button.setEnabled(true);
        button.setBackgroundResource(R.drawable.cornered_default);
    }
}

private void seekbarProgess(){
    SeekBar seekBar = view.findViewById(R.id.seekBar);
    EditText editText = view.findViewById(R.id.editText);
    Button button = view.findViewById(R.id.sort_btn);
    seekBar.setMax(1000000 - 500);
    seekBar.setProgress(0);
    editText.setText("");
    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int amount = progress + 500;
            editText.setText(formatNumber(amount));
            if (selectedCategory.equalsIgnoreCase("Pricing range")) {
                button.setEnabled(true);
                button.setBackgroundResource(R.drawable.cornered_default);
            }
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) { }
    });
    editText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after) { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.toString().isEmpty()) {
                if (selectedCategory.equalsIgnoreCase("Pricing range")) {
                    button.setEnabled(false);
                    button.setBackgroundResource(R.drawable.cornered_lighter_default);
                }
                return;
            }
            try {
                String text = s.toString().replace(",", "");
                int amount = Integer.parseInt(text);
                if (selectedCategory.equalsIgnoreCase("Pricing range")) {
                    if (amount >= 500) {
                        seekBar.setProgress(amount - 500);
                        button.setEnabled(true);
                        button.setBackgroundResource(R.drawable.cornered_default);
                    } else {
                        button.setEnabled(false);
                        button.setBackgroundResource(R.drawable.cornered_lighter_default);
                    }
                }
            } catch (NumberFormatException e) {
            }
        }
        @Override
        public void afterTextChanged(Editable s) { }
    });
}
    private int selectedPosition = -1;
    private void brandMethod(FrameLayout container, String selected, Button button){
        try{
     LinearLayout layout = view.findViewById(R.id.brand_container);
        if(selected.equalsIgnoreCase("Brand") || selected.equalsIgnoreCase("Brands")){
            layout.setVisibility(View.VISIBLE);
            button.setEnabled(false);
            button.setBackgroundResource(R.drawable.cornered_lighter_default);
            for(int j= 0; j < layout.getChildCount(); j++){
                View Xview = layout.getChildAt(j);
                if(Xview instanceof EditText){
                   EditText editText = (EditText) Xview;
                  //EditText editText = view.findViewById(R.id.brand_editText);
                    String text = editText.getText().toString();
                    editText.addTextChangedListener(new TextWatcher(){
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
                            @Override
                            public void onTextChanged(CharSequence s, int start, int count, int before){}
                            @Override 
                            public void afterTextChanged(Editable s){
                                if(s.toString().length() > 0) {
                                    filterList(s.toString()); 
                                    //CustomToast.show(context, "Hello!!");
                                }
                            }
                    });
                }else if(Xview instanceof ListView){
                    ListView listView = (ListView) Xview;
                String[] brands = context.getResources().getStringArray(R.array.brands);
                int[] images = {R.drawable.lg_logo, R.drawable.samsung_logo,R.drawable.nike_logo,
                    R.drawable.hisense_logo, R.drawable.puma_logo, R.drawable.gucci_logo, R.drawable.off_white_logo, R.drawable.apple_logo};
                 //CustomAdapter adapter = new CustomAdapter(context);
                 CustomAdapter adapter = new CustomAdapter(context, brands, images);
                 listView.setAdapter(adapter);
                 listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                     @Override
                     public void onItemClick(AdapterView<?> parent, View view,int position, long id ){
                        // String selectedList = ((CustomAdapter) listView.getAdapter()).getFilteredItem(position);
                         selectedPosition = position;
                         adapter.notifyDataSetChanged();
                         String selectedList = (String) parent.getItemAtPosition(position);
                         button.setEnabled(true);
                         button.setBackgroundResource(R.drawable.cornered_default);
                     }
                 
                @Override
                public View getView(int position, View convertView, ViewGroup parent ){
                    if(position == selectedPosition){
                        view.setBackgroundColor(context.getResources().getColor(R.color.black));
                    } else{
                        view.setBackgroundColor(context.getResources().getColor(R.color.white));
                    } 
                }
                });
            }       
            }
        }else{
            layout.setVisibility(View.GONE);
        }
    }catch(Exception e){
        CustomToast.show(context, e.getMessage());
    }
    }
    private void filterList(String query) {
    ListView listView = view.findViewById(R.id.listView);
    CustomAdapter adapter = (CustomAdapter) listView.getAdapter();
    adapter.getFilter().filter(query);
    if (adapter.getCount() == 0) {
        //no match 
    } else {
        
    }

}
private String formatNumber(int number) {
    return String.format("%,d", number);
}
}