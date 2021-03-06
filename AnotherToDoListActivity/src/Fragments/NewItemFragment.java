package Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.anothertodolistactivity.R;

import Interfaces.OnItemAddedListener;

public class NewItemFragment extends Fragment {

    private OnItemAddedListener onItemAddedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.text_item, container, false);
        final EditText myEditText = (EditText) view
                .findViewById(R.id.myEditText);

        myEditText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
                            || (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        String newItem = myEditText.getText().toString();
                        onItemAddedListener.onNewItemAdded(newItem);
                        myEditText.setText("");
                        return true;
                    }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onItemAddedListener = (OnItemAddedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnNewItemAddListener");
        }
    }

}
