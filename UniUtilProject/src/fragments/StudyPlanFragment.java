/**
 *
 */
package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uniutilproject.R;

import java.util.Calendar;

import dialog_fragments.DatePickerDialogFragment;
import dialog_fragments.TimePickerDialogFragment;

/**
 * @author desmond
 */
public class StudyPlanFragment extends Fragment {

    private EditText plan_name_editText;
    private EditText coursename_editText;
    private EditText location_editText;
    private TextView pickdate_textview;
    private TextView date_textview;
    private TextView picktime_textview;
    private TextView time_textview;
    private Button create_button;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.studyplan_fragment, container, false);

        plan_name_editText = (EditText) view
                .findViewById(R.id.studyplan_name_et);
        coursename_editText = (EditText) view
                .findViewById(R.id.studyplan_course_et);
        location_editText = (EditText) view
                .findViewById(R.id.studyplan_location_et);
        pickdate_textview = (TextView) view
                .findViewById(R.id.studyplan_dateText);
        date_textview = (TextView) view.findViewById(R.id.studyplan_dateView);
        picktime_textview = (TextView) view
                .findViewById(R.id.studyplan_timeText);
        time_textview = (TextView) view.findViewById(R.id.studyplan_timeView);


        date_textview.setOnClickListener(new DatePickerListener());
        time_textview.setOnClickListener(new TimePickerListener());

        create_button = (Button) view.findViewById(R.id.studyplan_btnsave);

        create_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
                //DO SOMETHING
            }
        });

        // set the current date
        setCurrentDate(date_textview);
        // set the current time
        setCurrentTime(time_textview);
        ;

        return view;
    }

    // SET DEFAULT DATE ON TEXTVIEW TO BE THE CURRENT DATE
    public void setCurrentDate(TextView date_textview) {

        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);

        date_textview.setText(new StringBuilder().append(month + 1).append("-")
                .append(date).append("-").append(year));
    }

    //SET DEFAULT TIME ON TEXTVIEW TO BE THE CURRENT DATE
    public void setCurrentTime(TextView time_textview) {

        final Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        if (hourOfDay > 12) {
            time_textview.setText(new StringBuilder().append(hourOfDay)
                    .append(":").append(minute).append("pm").append(""));
        } else {
            time_textview.setText(new StringBuilder().append(hourOfDay)
                    .append(":").append(minute).append("am").append(""));
        }
    }

    private class DatePickerListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            showDatePickerFragment(v);
        }
    }

    // SHOW THE DATEPICKER DIALOG FRAGMENT
    public void showDatePickerFragment(View v) {
        DatePickerDialogFragment datepicker = new DatePickerDialogFragment(
                date_textview);
        datepicker.show(getFragmentManager(), "datePicker");
    }

    //SHOW THE TIMEPICKER DIALOG FRAGMENT
    public void showTimePickerFragment(View v) {
        TimePickerDialogFragment timepicker = new TimePickerDialogFragment(time_textview);
        timepicker.show(getFragmentManager(), "timePicker");
    }

    private class TimePickerListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            showTimePickerFragment(v);
        }
    }
}
