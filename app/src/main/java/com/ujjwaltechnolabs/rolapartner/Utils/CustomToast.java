package com.ujjwaltechnolabs.rolapartner.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ujjwaltechnolabs.rolapartner.R;


public class CustomToast {

    // Private constructor to prevent instantiation
    private CustomToast() {
    }

    public static void showToast(Context context, String message) {
        // Inflate the custom layout
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null);

        // Set the custom message
        TextView textView = view.findViewById(R.id.customToastText);
        textView.setText(message);

        // Set the custom icon (optional)
        ImageView imageView = view.findViewById(R.id.customToastIcon);
        imageView.setImageResource(R.drawable.ic_rola_car); // replace with your own drawable

        // Create and show the Toast
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    public static void showToastShort(Context context, String message) {
        // Inflate the custom layout
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null);

        // Set the custom message
        TextView textView = view.findViewById(R.id.customToastText);
        textView.setText(message);

        // Set the custom icon (optional)
        ImageView imageView = view.findViewById(R.id.customToastIcon);
        imageView.setImageResource(R.drawable.ic_rola_car); // replace with your own drawable

        // Create and show the Toast
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}
