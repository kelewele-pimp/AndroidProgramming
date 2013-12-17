package com.example.todolist;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ToDoListActivity extends Activity implements OnNewItemAddedListener {

    private ArrayList<String> todoItems;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate view
        setContentView(R.layout.activity_to_do_list);

        //Get references to the Fragments
        FragmentManager fm = getFragmentManager();
        ToDoListFragment toDoListFragment = (ToDoListFragment) fm.findFragmentById(R.id.TodoListFragment);

        //Create the array list of to do items
        todoItems = new ArrayList<String>();

        //Create the array adapter to bind the array to the list view
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);

        toDoListFragment.setListAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.to_do_list, menu);
        return true;
    }

    @Override
    public void onNewItemAdded(String newItem) {
        todoItems.add(newItem);
        adapter.notifyDataSetChanged();
    }

}
