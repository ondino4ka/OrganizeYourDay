package com.dreamteam.organizeyourday;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class ThemeManager {

    public static int index = 0;

    public static void setCurrentMainTheme(Activity activity)
    {
        int theme;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        switch(index) {
            case 0:
                theme = sp.getInt("THEME", R.style.AppTheme_NoActionBar);
                activity.setTheme(theme);
                break;
            case 1:
                theme = sp.getInt("THEME", R.style.Blue_NoActionBar);
                activity.setTheme(theme);
                break;
            case 2:
                theme = sp.getInt("THEME", R.style.Pink_NoActionBar);
                activity.setTheme(theme);
                break;
            default:
                break;
        }



    }

    public static void setCurrentTheme(Activity activity)
    {
        int theme;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        switch(index) {
            case 0:
                theme = sp.getInt("THEME", R.style.AppTheme);
                activity.setTheme(theme);
                break;
            case 1:
                theme = sp.getInt("THEME", R.style.Blue);
                activity.setTheme(theme);
                break;
            case 2:
                theme = sp.getInt("THEME", R.style.Pink);
                activity.setTheme(theme);
                break;
            default:
                break;
        }



    }

}
