/**
 * 
 */
package fragments;

import java.util.ArrayList;




import com.example.uniutilproject.R;

import adapters.Card_Adapter;
import android.app.Fragment;
import android.app.ListFragment;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author desmond
 * 
 */
public class HomeFragment extends Fragment {
	
	private ListView listview;
	/*
	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2" };
	    
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	        android.R.layout.simple_list_item_1, values);
	    
	    
	    
	    setListAdapter(adapter);
	  }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data

	  } */

	
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
	    
	    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2" };
	    
	    ArrayList<String> values_list = new ArrayList<String>(); 
	    for (String s : values) {
			values_list.add(s); 
		}
	    
	   Card_Adapter adapter = new Card_Adapter(getActivity(), values); 
	    
	   /*
	    Card_Adapter<String> adapter = new Card_Adapter()<String>(getActivity(),
	        R.layout.home_listitem, values);*/
	    listview.setAdapter(adapter);
	  }
}
