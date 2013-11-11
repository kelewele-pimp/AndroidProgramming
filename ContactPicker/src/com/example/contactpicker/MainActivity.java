package com.example.contactpicker;

import java.net.URI;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// CREATE A NEW CURSOR TO RETRIEVE THE PEOPLE STORED
		// IN THE CONTACT LIST AND BIND IT TO THE LISTVIEW
		// USING A SIMPLCURSORADAPTER
		final Cursor c = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		
		String[] from = new String[] {
				Contacts.DISPLAY_NAME_PRIMARY
		}; 
		
		int[] to = new int[] {R.id.itemTextview}; 
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listitemlayout, c, from, to); 
		ListView lv = (ListView) findViewById(R.id.contactListview);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) { 
				//Move the cursor to the selected item
				c.moveToPosition(position); 
				//Extract the row id 
				int rowId = c.getInt(c.getColumnIndexOrThrow("_id")); 
				//Construct the result URI
				Uri outUri = 
						ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, rowId); 
				Intent outData = new Intent(); 
				outData.setData(outUri);
				setResult(Activity.RESULT_OK, outData);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
