package com.ujjwaltechnolabs.rolapartner.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "rolapartner";
    private static SharedPrefManager instance;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private SharedPrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public void putString(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return sharedPreferences.contains(key) ? sharedPreferences.getString(key, null) : null;
    }

    public void putInt(String key, int value) {
        editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

//    public Integer getInt(String key) {
//        return sharedPreferences.contains(key) ? sharedPreferences.getInt(key, -1) : null; // Here, -1 is just a default value and won't be used.
//    }
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void putBoolean(String key, Boolean value) {
        editor = sharedPreferences.edit();
        if (value != null) {
            editor.putBoolean(key, value);
        } else {
            editor.remove(key); // remove the key if the value is set as null
        }
        editor.apply();
    }

    public Boolean getBoolean(String key) {
        return sharedPreferences.contains(key) ? sharedPreferences.getBoolean(key, false) : null; // Here, false is just a default value and won't be used.
    }
    public void putBoolean(String key, boolean value) {
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }
    public void clear() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public void removeValue(String key) {
        editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }
    // Add other utility methods as needed...
}