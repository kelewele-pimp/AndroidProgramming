/**
 *
 */
package dialog_fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


/**
 * @author desmond
 */
public class TimePickerDialogFragment extends DialogFragment implements
        TimePickerDialog.OnTimeSetListener {

    private TextView activity_textview;
    private int hour;
    private int minute;

    public TimePickerDialogFragment() {
    }

    public TimePickerDialogFragment(TextView activity_textview) {
        this.activity_textview = activity_textview;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        this.hour = hourOfDay;
        this.minute = minute;

        // set the selected time to textview
        if (hourOfDay > 12) {
            activity_textview.setText(new StringBuilder().append(hourOfDay)
                    .append(":").append(minute).append("pm").append(""));
        } else {
            activity_textview.setText(new StringBuilder().append(hourOfDay)
                    .append(":").append(minute).append("am").append(""));
        }
    }

}
