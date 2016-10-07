package net.thisbit.taskapp2;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by christian on 6/15/2016.
 */
public class EditTaskDatePickerFragment extends DialogFragment implements OnDateSetListener {
    public static Calendar cal = Calendar.getInstance();


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // Set the calendar object with what was picked
        cal.set(year, monthOfYear, dayOfMonth);
        // now set the TextView to reflect the new date
        String thisDate = cal.getTime().toString().substring(0,10)+ ", "+cal.getTime().toString().substring(24,28);
        EditTaskActivity.thisEditTaskEDOCTextView.setText(thisDate);
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
