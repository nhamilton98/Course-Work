package com.hamilton.nathan.nhamiltonfinal;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
{
    public SettingsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences preferences = getPreferenceScreen().getSharedPreferences();

        onSharedPreferenceChanged(preferences, "ghosts_per_level");
        onSharedPreferenceChanged(preferences, "new_ghosts");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String string)
    {
        Preference preference = findPreference(string);

        if (string == "ghosts_per_level")
            preference.setSummary(Integer.toString(sharedPreferences.getInt(string, 11)));
        else if (string == "new_ghosts")
            preference.setSummary(Integer.toString(sharedPreferences.getInt(string, 0)));
    }
}
