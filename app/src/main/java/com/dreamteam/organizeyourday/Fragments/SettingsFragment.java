package com.dreamteam.organizeyourday.Fragments;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.dreamteam.organizeyourday.MainActivity;
import com.dreamteam.organizeyourday.R;
import com.dreamteam.organizeyourday.SettingsActivity;

public class SettingsFragment extends PreferenceFragment{




    private  Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener
            = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;

                if(MainActivity.index!=listPreference.findIndexOfValue(stringValue))
                {
                    MainActivity.isCurrentThemeChanged = true;
                }
                else {
                    MainActivity.isCurrentThemeChanged = false;
                }

                 MainActivity.index = listPreference.findIndexOfValue(stringValue);
                preference.setSummary(
                        MainActivity.index >= 0
                                ? listPreference.getEntries()[MainActivity.index]
                                : null);

            }

            /*
            Intent intent = new Intent(, MainActivity.class);
            startActivity(intent);*/

            return true;

        }
    };

        private  void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(), ""));

        }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        setHasOptionsMenu(true);
        bindPreferenceSummaryToValue(findPreference("pref_themes_list"));



    }


}
