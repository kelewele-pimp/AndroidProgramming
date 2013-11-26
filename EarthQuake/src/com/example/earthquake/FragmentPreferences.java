/**
 * 
 */
package com.example.earthquake;

import java.util.List;

import Fragments.UserPreferenceFragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.preference.PreferenceActivity;

/**
 * @author desmond
 *
 */
public class FragmentPreferences extends PreferenceActivity {
	
	public static final String USER_PREFERENCE = "USER_PREFERENCE"; 
	public static final String PREF_AUTO_UPDATE = "PREF_AUTO_UPDATE"; 
	public static final String PREF_MIN_MAG = "PREF_MIN_MAG"; 
	public static final String PREF_UPDATE_FREQ = "PREF_UPDATE_FREQ"; 

	@Override
	public void onBuildHeaders(List<Header> target) {
		super.onBuildHeaders(target);
		loadHeadersFromResource(R.layout.userpreferences_headers, target);
	}

	@Override
	protected boolean isValidFragment(String fragmentName) {
		return UserPreferenceFragment.class.getName().equals(fragmentName);
	}	
	
	
}
