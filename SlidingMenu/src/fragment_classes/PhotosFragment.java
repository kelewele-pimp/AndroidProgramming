/**
 *
 */
package fragment_classes;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slidingmenu.R;

/**
 * @author desmond
 */
public class PhotosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootVIew = inflater.inflate(R.layout.photos_fragment, container,
                false);

        return rootVIew;
    }

}
