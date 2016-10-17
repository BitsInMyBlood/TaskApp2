package net.thisbit.taskapp2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class AddTaskActivity extends AppCompatActivity {
    EditText taskTitleEditText;
    EditText taskDescrEditText;
    public static TextView textViewObj;
    public static Calendar thisCal;
    String thisTitle = "";
    String thisDescr = "";
    String thisEDOC = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setTitle("Add a Task");

        thisCal = DatePickerFragment.getCal();
        thisEDOC = thisCal.toString().substring(0,10)+thisCal.toString().substring(24,28);
        textViewObj = (TextView) findViewById(R.id.taskEDOCTextView);
        textViewObj.setText(thisCal.getTime().toString().substring(0,10) + "," + thisCal.getTime().toString().substring(24,28));
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

                // Create the task, set the attributes
                MainTask thisTask = new MainTask();
                thisTask.setTitle(thisTitle);
                thisTask.setDescription(thisDescr);
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

                endThisActivity();
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

    public void cancelButtonOnClick(View v) {
        Intent showTaskIntent = new Intent(v.getContext(), MainActivity.class);
        startActivityForResult(showTaskIntent, 0);
    }

    public void endThisActivity() {
        startActivity(new Intent(getApplicationContext(), ShowTasksListActivity.class));
        Intent i = new Intent(AddTaskActivity.this, ShowTasksListActivity.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}
