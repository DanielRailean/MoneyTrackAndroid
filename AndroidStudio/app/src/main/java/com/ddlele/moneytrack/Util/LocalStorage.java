package com.ddlele.moneytrack.Util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ddlele.moneytrack.View.MainActivity;


public class LocalStorage {
    private static LocalStorage localStorage;

    public static LocalStorage getInstance(){
        if (localStorage == null)
            localStorage = new LocalStorage();
        return  localStorage;
    }

    public void set(String key, String value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.getContextOfApplication());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String get(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.getContextOfApplication());
        return prefs.getString(key,"default");
    }
}
