package net.thisbit.taskapp2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_add_task);
        setTitle("Add a Task");
        thisCal = DatePickerFragment.getCal();
        textViewObj = (TextView) findViewById(R.id.taskDOCTextView);
        taskTitleEditText = (EditText) findViewById(R.id.TaskTitleEditText);
        taskDescrEditText = (EditText) findViewById(R.id.TaskDescriptionEditText);


        final Button saveButton = (Button) findViewById(R.id.saveButton);
        assert saveButton != null;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();

                thisTitle = taskTitleEditText.getText().toString();
                thisDescr = taskDescrEditText.getText().toString();
                thisEDOC = textViewObj.getText().toString();
               // thisId = thisTitle.substring(0,2) + thisDescr.substring(0,2);

                // Create the task, set the attributes
                MainTask thisTask = new MainTask();
                thisTask.setTitle(thisTitle);
                thisTask.setDescription(thisDescr);
                //thisTask.setTaskId(thisId);
                thisTask.setTaskEDOS(thisCal);

                // Add the Task
                Singleton.getInstance().addTask(thisTask);


                // Declare that its done
                CharSequence text = "Saved";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

                // Clear the Fields
                taskTitleEditText.setText("");
                taskDescrEditText.setText("");
                textViewObj.setText("");



                write();
            }
        });



    }

    public void write(){
        ArrayList<MainTask> myTasks = Singleton.getInstance().getMyTasks();
        String filename = "myTasks.dat";
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
            oos.writeObject(myTasks);
            oos.close();
        } catch (FileNotFoundException e) { e.printStackTrace();
        } catch (IOException e) { e.printStackTrace();
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
