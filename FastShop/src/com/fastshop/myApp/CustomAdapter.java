package com.fastshop.myApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.graphics.Color;

public class CustomAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private String[] options;
    private Object[] images;
    private String[] filteredOptions;
    private Object[] filteredImages;

    public CustomAdapter(Context context, String[] options, int[] images) {
        this.context = context;
        this.options = options;
        this.images = new Object[images.length];
        for (int i = 0; i < images.length; i++) {
            this.images[i] = images[i];
        }
        this.filteredOptions = options;
        this.filteredImages = this.images;
    }

    public CustomAdapter(Context context, String[] options, Object[] images) {
        this.context = context;
        this.options = options;
        this.images = images;
        this.filteredOptions = options;
        this.filteredImages = images;
    }

    @Override
    public int getCount() {
        return filteredOptions.length;
    }

    @Override
    public Object getItem(int position) {
        return filteredOptions[position];
    }
 @Override
public long getItemId(int position) {
    if (position < filteredOptions.length) {
        return position;
    } else {
        return 0;
    }
}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.spinner_items, parent, false);
            view.setBackgroundColor(Color.WHITE);
           // view.setBackgroundResource(R.drawable.spinner);
        }
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(filteredOptions[position]);
        if (filteredImages[position] instanceof Bitmap) {
            imageView.setImageBitmap((Bitmap) filteredImages[position]);
        } else if (filteredImages[position] instanceof Integer) {
            imageView.setImageResource((Integer) filteredImages[position]);
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<String> filtered = new ArrayList<>();
                List<Object> filteredImage = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filtered.addAll(Arrays.asList(options));
                    filteredImage.addAll(Arrays.asList(images));
                } else {
                    String query = constraint.toString().toLowerCase();
                    for (int i = 0; i < options.length; i++) {
                        if (options[i].toLowerCase().contains(query)) {
                            filtered.add(options[i]);
                            filteredImage.add(images[i]);
                        }
                    }
                }

                results.values = filtered;
                results.count = filtered.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredOptions = ((List<String>) results.values).toArray(new String[0]);
                List<Object> imageList = new ArrayList<>();
                for (String option : filteredOptions) {
                    for (int j = 0; j < options.length; j++) {
                        if (options[j].equals(option)) {
                            imageList.add(images[j]);
                            break;
                        }
                    }
                }
                filteredImages = imageList.toArray();
                notifyDataSetChanged();
            }
        };
    }
}





/*package com.fastshop.myApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private String[] options;
    private Object[] images;

    public CustomAdapter(Context context, String[] options, int[] images) {
        this.context = context;
        this.options = options;
        this.images = new Object[images.length];
        for (int i = 0; i < images.length; i++) {
            this.images[i] = images[i];
        }
    }

    public CustomAdapter(Context context, String[] options, Object[] images) {
        this.context = context;
        this.options = options;
        this.images = images;
    }

    @Override
    public int getCount() {
        return options.length;
    }

    @Override
    public Object getItem(int position) {
        return options[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.spinner_items, parent, false);
        }

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);

        textView.setText(options[position]);

        if (images[position] instanceof Bitmap) {
            imageView.setImageBitmap((Bitmap) images[position]);
        } else if (images[position] instanceof Integer) {
            imageView.setImageResource((Integer) images[position]);
        }

        return view;
    }
}
*/
/*
 *         String [] options = {"Nigeria","Ghana","South Africa","Kenya","France"};
        int []   images = {R.drawable.nigeria_flag,R.drawable.ghana_flag,R.drawable.south_africa_flag,R.drawable.kenya_flag,R.drawable.france_flag};  
         CustomAdapter adapter = new CustomAdapter(this,options,images);
        spinner.setAdapter(adapter);
        
 */