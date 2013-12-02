/**
 * 
 */
package fragment_classes;

import com.example.slidingmenu.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author desmond
 * 
 */
public class CommunityFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.community_fragment,
				container, false);

		return rootView; 
	}

}
