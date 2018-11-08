package com.dicoding.paul.dictionary;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreference {
    SharedPreferences preferences;
    Context context;

    public AppPreference(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = preferences.edit();
        String key = context.getResources().getString(R.string.key);
        editor.putBoolean(key, input);
        editor.commit();
    }

    public boolean getFirstRun() {
        String key = context.getResources().getString(R.string.key);
        return preferences.getBoolean(key, true);
    }
}
