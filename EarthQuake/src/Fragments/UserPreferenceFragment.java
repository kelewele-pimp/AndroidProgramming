package Fragments;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.earthquake.R;

public class UserPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.userpreferences);
    }


}
