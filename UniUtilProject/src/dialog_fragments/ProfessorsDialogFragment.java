/**
 *
 */
package dialog_fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uniutilproject.R;

/**
 * @author desmond
 */
public class ProfessorsDialogFragment extends DialogFragment {

    private EditText professor_name;
    private EditText professor_course;
    private EditText professor_course_room;
    private EditText professor_email;

    public static ProfessorsDialogFragment newInstance(String title) {
        ProfessorsDialogFragment frag = new ProfessorsDialogFragment();
        Bundle args = new Bundle();
        args.putString("professors_title", title);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String btnOk = "Submit";
        String btnCancel = "Cancel";

        // create the new fragment using the alert builder
        String title = getArguments().getString("professors_title");
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
        ad.setTitle(title);

        // Get the Layout inflater
        // pass null as the parent view because its going in the dialog layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.professor_dialogview, null);

        // Inflate and set the layout for the dialog
        ad.setView(view);

        // get views
        professor_name = (EditText) view
                .findViewById(R.id.professor_dialog_pname);
        professor_email = (EditText) view
                .findViewById(R.id.professor_dialog_email);
        professor_course = (EditText) view
                .findViewById(R.id.professor_dialog_course);
        professor_course_room = (EditText) view
                .findViewById(R.id.professor_dialog_room);

        // add action buttons
        ad.setPositiveButton(btnOk, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Send the positive button event back to the host activity
                doPositiveClick();
            }
        });

        ad.setNegativeButton(btnCancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Send the negative button event back to the host activity
                doNegativeClick();

            }
        });

        // Create the dialog box and perform other operations on it
        final AlertDialog dialog = ad.create();

        // Call the keyboard to show automatically
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        return dialog;
    }

    // Methods for custom dialog fragment
    private void doPositiveClick() {
        Toast.makeText(getActivity(), "OK button pressed", Toast.LENGTH_LONG)
                .show();
        Log.i("FragmentAlertDialog", "Positive click!");
    }

    private void doNegativeClick() {
        Toast.makeText(getActivity(), "Negative button pressed",
                Toast.LENGTH_LONG).show();
        Log.i("FragmentAlertDialog", "Negative click!");
    }

}
