package net.thisbit.taskapp2;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import java.util.Calendar;


/**
 * Created by christian on 6/15/2016.
 */
public class DatePickerFragment extends DialogFragment implements OnDateSetListener {
    public static Calendar cal = Calendar.getInstance();


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // Set the calendar object with what was picked
        cal.set(year, monthOfYear, dayOfMonth);

        // now set the TextView to reflect the new date
        String thisDate = cal.getTime().toString().substring(0,10)+ ", "+cal.getTime().toString().substring(24,28);
        AddTaskActivity.textViewObj.setText(thisDate);
        AddTaskActivity.thisCal = cal;


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
