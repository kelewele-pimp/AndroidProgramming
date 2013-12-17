/**
 *
 */

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uniutilproject.R;

import adapters.ProfessorCardAdapter;
import dialog_fragments.ProfessorsDialogFragment;

/**
 * @author desmond
 */
public class ProfessorsFragment extends Fragment {

    private ListView professors_listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.professors_fragment, container,
                false);

        professors_listview = (ListView) view
                .findViewById(R.id.professors_listview);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2"};

        ProfessorCardAdapter adapter = new ProfessorCardAdapter(getActivity(),
                values);

        professors_listview.setAdapter(adapter);

    }

    // Register the fragment as a contributor to the Options Menu
    // call setHasOptions within its onCreate Handler
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    // add a menu item when this fragment is called
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // custom menu (action bar layout)
        inflater.inflate(R.menu.professors_menu_fragment, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        // Find which menu item has been selected
        switch (item.getItemId()) {
            // Check for each known menu item
            case R.id.professors_menu_addbtn:
                showDialog();
                return true;
            // Return false if the menu item has not been handled
            default:
                return false;
        }
    }

    // Display the custom dialog
    private void showDialog() {
        DialogFragment newFragment = ProfessorsDialogFragment
                .newInstance("Add Professor");
        newFragment.show(getFragmentManager(), "professor_dialog");

    }

}
