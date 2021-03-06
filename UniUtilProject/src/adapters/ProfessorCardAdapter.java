package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uniutilproject.R;

public class ProfessorCardAdapter extends BaseAdapter {

    private String[] values;
    private Context context;

    public ProfessorCardAdapter(Context context, String[] values) {
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
            convertView = mInflater.inflate(R.layout.professors_list_item, null);
        }

        TextView professor_name = (TextView) convertView.findViewById(R.id.professor_card_pname);
        TextView course_name = (TextView) convertView.findViewById(R.id.professor_card_coursename);

        professor_name.setText(getItem(position).toString());
        course_name.setText(getItem(position).toString());

        return convertView;
    }

}
