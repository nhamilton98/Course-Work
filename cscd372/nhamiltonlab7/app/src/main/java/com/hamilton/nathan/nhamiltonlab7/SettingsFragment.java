package com.hamilton.nathan.nhamiltonlab7;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences prefs = getPreferenceScreen().getSharedPreferences();

        onSharedPreferenceChanged(prefs, "spring_stiff_pref");
        onSharedPreferenceChanged(prefs, "num_coils_pref");
        onSharedPreferenceChanged(prefs, "start_pos_pref");
        onSharedPreferenceChanged(prefs, "mass_type_pref");
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
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        Preference preference = findPreference(key);

        switch (key)
        {
            case "spring_stiff_pref" :
                String valueString = sharedPreferences.getString(key, "1.5");
                Float valueNum = Float.valueOf(valueString);

                if (valueNum > 10)
                    valueNum = 10f;
                else if (valueNum < 0.5)
                    valueNum = 0.5f;

                String value = valueNum.toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("sprint_stiff_pref", value);
                editor.commit();

                preference.setSummary(value);
                break;
            case "num_coils_pref" :
                preference.setSummary(Integer.toString(sharedPreferences.getInt(key, 11)));
                break;
            case "start_pos_pref" :
                preference.setSummary(Integer.toString(sharedPreferences.getInt(key, 0)));
                break;
            case "mass_type_pref" :
                ListPreference listPreference = (ListPreference) findPreference(key);

                String type = (sharedPreferences.getString(key, key));
                String shape = (String) listPreference.getEntries()[listPreference.findIndexOfValue(type)];

                listPreference.setSummary(shape);
        }
    }
}
