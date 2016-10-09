package net.thisbit.taskapp2;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class AddSubTaskActivity extends AppCompatActivity {

    EditText taskTitleEditText;
    EditText taskDescrEditText;
    public static TextView textViewObj;
    Calendar thisCal;
    String thisTitle = "";
    String thisDescr = "";
    String thisEDOC = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_task);
        setTitle("Add a SubTask");

        thisCal = DatePickerFragment.getCal();
        textViewObj = (TextView) findViewById(R.id.taskDOCTextView);
        taskTitleEditText = (EditText) findViewById(R.id.TaskTitleEditText);
        taskDescrEditText = (EditText) findViewById(R.id.TaskDescriptionEditText);

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
