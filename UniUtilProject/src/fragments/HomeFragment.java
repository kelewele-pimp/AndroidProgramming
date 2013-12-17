/**
 *
 */
package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uniutilproject.R;

import java.util.ArrayList;

import adapters.HomeCardAdapter;


/**
 * @author desmond
 */
public class HomeFragment extends Fragment {

    private ListView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        listview = (ListView) view.findViewById(R.id.home_fragment_list);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2"};

        ArrayList<String> values_list = new ArrayList<String>();
        for (String s : values) {
            values_list.add(s);
        }

        HomeCardAdapter adapter = new HomeCardAdapter(getActivity(), values);

        listview.setAdapter(adapter);
    }
}
