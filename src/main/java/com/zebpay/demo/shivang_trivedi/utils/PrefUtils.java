package com.zebpay.demo.shivang_trivedi.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vcollabera-st Shivang Trivedi on 06-04-2017.
 */

public class PrefUtils {
    private final Activity mActivity;


    public PrefUtils(Activity mActivity) {
        this.mActivity = mActivity;
    }


    public static final String CONSTANT_FILE_NAME="ehs_safety_preferences";

   /* public PrefUtils(Context applicationContext) {

    }*/

    public  void clearPreference(){
        SharedPreferences settings = mActivity.getSharedPreferences(CONSTANT_FILE_NAME, Context.MODE_PRIVATE);
        settings.edit().clear().apply();
    }

    public  void saveInPreference( String key, String value){
        SharedPreferences prefs = mActivity.getSharedPreferences(CONSTANT_FILE_NAME,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public  String retrieveFromPref( String Key){
        SharedPreferences prefs = mActivity.getSharedPreferences(CONSTANT_FILE_NAME,
                Context.MODE_PRIVATE);
        return prefs.getString(Key, "");
    }
}
