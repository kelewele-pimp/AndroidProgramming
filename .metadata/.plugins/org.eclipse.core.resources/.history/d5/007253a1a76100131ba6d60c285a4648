package adapters;

import java.util.ArrayList;

import model.NavDrawerItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uniutilproject.R;

public class Card_Adapter extends BaseAdapter {

	private Context context;
	private String values[];
	private ArrayList<String> stringItems;

	/*
	 * public Card_Adapter(Context context, String[] values) { super(context,
	 * R.layout.home_listitem, values); this.context = context; this.values =
	 * values; }
	 */

	public Card_Adapter(Context context, String[] values) {
		this.context = context;
		this.values = values; 
	}

	@Override
	public int getCount() {
		return values.length;
	}

	@Override
	public Object getItem(int position) {
		return values[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.home_listitem, null);
		}

		TextView imgIcon = (TextView) convertView.findViewById(R.id.homecard_content);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.homecard_date);

		imgIcon.setText(getItem(position).toString());
		txtTitle.setText(getItem(position).toString());

		return convertView;
	}

}
