package com.fastshop.myApp;

import android.os.Build;
import android.content.Context;
import android.content.res.Configuration;
import java.util.Locale;

public class language_selector {
    private Context context;

    public language_selector(Context context) {
        this.context = context;
    }

    public void select() {
        Locale locale = new Locale("fr");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}