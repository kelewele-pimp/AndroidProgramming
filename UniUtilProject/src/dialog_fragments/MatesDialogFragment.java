package dialog_fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.uniutilproject.R;

import fragments.MatesFragment;


public class MatesDialogFragment extends DialogFragment {

	MatesFragment matesFragment = (MatesFragment) getTargetFragment().getFragmentManager().findFragmentByTag("title");

	public static MatesDialogFragment newInstance(String title) {
		MatesDialogFragment frag = new MatesDialogFragment();
		Bundle args = new Bundle();
		args.putString("title", title);
		frag.setArguments(args);

		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		String btnOk = "Submit";
		String btnCancel = "Cancel";

		// create the new fragment using the alert builder
		String title = getArguments().getString("title");
		AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
		ad.setTitle(title);

		// Get the Layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		// Inflate and set the layout for the dialog
		// pass null as the parent view because its going in the dialog layout
		ad.setView(inflater.inflate(R.layout.mates_dialogview, null));

		// add action buttons
		ad.setPositiveButton(btnOk, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Send the positive button event back to the host activity 
				matesFragment.doPositiveClick();
				//Toast.makeText(getActivity(), "OK button pressed", Toast.LENGTH_LONG).show();
			}
		});

		ad.setNegativeButton(btnCancel, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Send the negative button event back to the host activity
				//Toast.makeText(getActivity(), "Negative button pressed", Toast.LENGTH_LONG).show();
				matesFragment.doNegativeClick();
			}
		});

		return ad.create();
	}

}
