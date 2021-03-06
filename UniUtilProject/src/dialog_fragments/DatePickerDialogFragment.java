/**
 *
 */
package dialog_fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


/**
 * @author desmond
 */
public class DatePickerDialogFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    private int year;
    private int month;
    private int day;
    private TextView activity_textvTextView;

    public DatePickerDialogFragment() {
        // nothing to see here, move along
    }

    public DatePickerDialogFragment(TextView textView) {
        activity_textvTextView = textView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        // use the current date as the default date in picker
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    // when the dialog box is closed, this method will be called
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;

        // set selected date into textview
        activity_textvTextView.setText(new StringBuilder().append(month + 1)
                .append("-").append(day).append("-").append(year).append(" "));

        // set selected date into datepicker also
        // view.init(year, month, day, null);

    }

}
