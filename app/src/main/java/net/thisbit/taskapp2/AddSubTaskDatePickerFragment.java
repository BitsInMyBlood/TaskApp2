package net.thisbit.taskapp2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddSubTaskDatePickerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddSubTaskDatePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSubTaskDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public static Calendar cal = Calendar.getInstance();

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // Set the calendar object with what was picked
        cal.set(year, monthOfYear, dayOfMonth);
        // now set the TextView to reflect the new date
        String thisDate = cal.getTime().toString().substring(0,10)+ ", "+cal.getTime().toString().substring(24,28);
        AddSubTaskActivity.subtaskTextViewObj.setText(thisDate);
        AddSubTaskActivity.thisCal = cal;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public static Calendar getCal(){
        return cal;
    }


}