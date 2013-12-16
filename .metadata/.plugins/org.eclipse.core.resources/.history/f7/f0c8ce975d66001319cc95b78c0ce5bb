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

public class HomeCardAdapter extends BaseAdapter {

	private Context context;
	private String values[];

	public HomeCardAdapter(Context context, String[] values) {
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

		TextView card_content = (TextView) convertView.findViewById(R.id.homecard_content);
		TextView card_date = (TextView) convertView.findViewById(R.id.homecard_date);

		card_content.setText(getItem(position).toString());
		card_date.setText(getItem(position).toString());

		return convertView;
	}

}
