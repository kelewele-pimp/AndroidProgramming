package com.example.anothertodolistactivity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;

import java.util.ArrayList;

import Adapters.ToDoItemAdapter;
import Fragments.ToDoListFragment;
import Interfaces.OnItemAddedListener;
import db_package.ToDoContentProvider;

public class MainActivity extends Activity implements OnItemAddedListener,
        LoaderCallbacks<Cursor> {

    private ArrayList<ToDoItem> toDoList;
    private ToDoItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        ToDoListFragment toDoListFragment = (ToDoListFragment) fm
                .findFragmentById(R.id.list_fragment);

        toDoList = new ArrayList<ToDoItem>();

        // call the custom view
        int resID = R.layout.custom_text_item;

        adapter = new ToDoItemAdapter(this, resID, toDoList);

        toDoListFragment.setListAdapter(adapter);

        // Initiate the loader when the Activity is created
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Restart the loader when the Activity is restarted
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Directly add items to the Content Provider using the content resolver
    @Override
    public void onNewItemAdded(String newItem) {
        ContentResolver cr = getContentResolver();

        ContentValues values = new ContentValues();
        values.put(ToDoContentProvider.KEY_TASK, newItem);

        cr.insert(ToDoContentProvider.CONTENT_URI, values);
        getLoaderManager().restartLoader(0, null, this);

		/*
         * ToDoItem toDoItem = new ToDoItem(newItem); toDoList.add(toDoItem);
		 * adapter.notifyDataSetChanged();
		 */
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(this,
                ToDoContentProvider.CONTENT_URI, null, null, null, null);
        return loader;
    }

    // When the Loader's query completes, the result Cursor will be returned
    // to the onLoadFinished handler.Update it to iterate over the result Cursor
    // and repopulate the todolist array adapter accordingly
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // TODO Auto-generated method stub
        int keyTaskIndex = cursor
                .getColumnIndexOrThrow(ToDoContentProvider.KEY_TASK);

        toDoList.clear();
        while (cursor.moveToNext()) {
            ToDoItem newItem = new ToDoItem(cursor.getString(keyTaskIndex));
            toDoList.add(newItem);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        // TODO Auto-generated method stub

    }
}
