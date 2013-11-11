package Adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anothertodolistactivity.R;
import com.example.anothertodolistactivity.ToDoItem;

public class ToDoItemAdapter extends ArrayAdapter<ToDoItem> {

	int resource;
	
	public ToDoItemAdapter(Context context, int resource,
			List<ToDoItem> objects) {
		super(context, resource, objects);
		this.resource = resource; 
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout todoView;  
		ToDoItem item = getItem(position); 
		
		String taskString = item.getTask(); 
		Date date_created = item.getDate_created(); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String dateString = sdf.format(date_created);
		
		if(convertView == null){
			todoView = new LinearLayout(getContext()); 
			String inflater = Context.LAYOUT_INFLATER_SERVICE; 
			LayoutInflater li = (LayoutInflater)getContext().getSystemService(inflater); 
			li.inflate(resource, todoView, true); 
		} else {
			todoView = (LinearLayout) convertView; 
		}
		
		TextView dateTextView = (TextView) todoView.findViewById(R.id.rowDate); 
		TextView taskView = (TextView) todoView.findViewById(R.id.row);
		
		dateTextView.setText(dateString);
		taskView.setText(taskString);
		
		return todoView;
		
	} 
	
}
