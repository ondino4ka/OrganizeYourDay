package com.dreamteam.organizeyourday;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ThemeManager {

    public static int index = 0;
    public static void setCurrentMainTheme(Activity activity)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        switch (sp.getString("pref_themes_list", "0")) {
            case "0":
                activity.setTheme(R.style.AppTheme_NoActionBar);
                break;
            case "1":
                activity.setTheme(R.style.Blue_NoActionBar);
                break;
            case "2":
                activity.setTheme(R.style.Pink_NoActionBar);
                break;
            default:
                break;
        }

    }

    public static void setCurrentTheme(Activity activity)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        switch(sp.getString("pref_themes_list", "0")) {
            case "0":
                activity.setTheme(R.style.AppTheme);
                break;
            case "1":
                activity.setTheme(R.style.Blue);
                break;
            case "2":
                activity.setTheme(R.style.Pink);
                break;
            default:
                break;
        }

    }


}
