package com.example.anothertodolistactivity;

import java.util.ArrayList;
import java.util.List;

import Adapters.ToDoItemAdapter;
import Fragments.ToDoListFragment;
import Interfaces.OnItemAddedListener;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements OnItemAddedListener {
	
	private ArrayList<ToDoItem> toDoList; 
	private ToDoItemAdapter adapter; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FragmentManager fm = getFragmentManager(); 
		ToDoListFragment toDoListFragment = (ToDoListFragment) fm.findFragmentById(R.id.list_fragment); 
		
		toDoList = new ArrayList<ToDoItem>(); 
		
		//call the custom view
		int resID = R.layout.custom_text_item;
		
		adapter = new ToDoItemAdapter(this, resID, toDoList); 
		
		toDoListFragment.setListAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onNewItemAdded(String newItem) {
		ToDoItem toDoItem = new ToDoItem(newItem); 
		toDoList.add(toDoItem); 
		adapter.notifyDataSetChanged();
	}
}
