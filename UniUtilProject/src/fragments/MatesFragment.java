/**
 * 
 */
package fragments;

import com.example.uniutilproject.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author desmond
 *
 */
public class MatesFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.mates_fragment, container, false);
		
		return view; 
	}

	
	
}