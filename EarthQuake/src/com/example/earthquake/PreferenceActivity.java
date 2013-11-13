package com.example.earthquake;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class PreferenceActivity extends Activity{
	
	CheckBox autoUpdate; 
	Spinner updateFrequencySpinner; 
	Spinner magnitudeSpinner; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferences);
		
		autoUpdate = (CheckBox) findViewById(R.id.checkbox_autoupdate); 
		updateFrequencySpinner = (Spinner) findViewById(R.id.spinner_update_freq); 
		magnitudeSpinner = (Spinner) findViewById(R.id.spinner_quake_mag); 
		
		populateSpinners();
	}

	private void populateSpinners() {
		// POPULATE AND UPDATE FREQUENCY SPINNERS
		ArrayAdapter<CharSequence> frequency_adapter = 
				ArrayAdapter.createFromResource(this, R.array.update_freq_options, android.R.layout.simple_list_item_1); 
		
		int spinner_dd_item = android.R.layout.simple_spinner_dropdown_item; 
		frequency_adapter.setDropDownViewResource(spinner_dd_item);
		updateFrequencySpinner.setAdapter(frequency_adapter);
		
		//Populate the minimum magnitude spinner
		ArrayAdapter<CharSequence> magnitude_adapter = 
				ArrayAdapter.createFromResource(this, R.array.magnitude_options, android.R.layout.simple_list_item_1); 
		magnitude_adapter.setDropDownViewResource(spinner_dd_item);
		magnitudeSpinner.setAdapter(magnitude_adapter);
		
	}

	
}
