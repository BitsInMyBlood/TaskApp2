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
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    EditText taskTitleEditText;
    EditText taskDescrEditText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setTitle("Add a Task");

        taskTitleEditText = (EditText) findViewById(R.id.TaskTitleEditText);
        taskDescrEditText = (EditText) findViewById(R.id.TaskDescriptionEditText);



        final Button saveButton = (Button) findViewById(R.id.saveButton);
        assert saveButton != null;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Loaded";

                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

                String thisTitle = taskTitleEditText.getText().toString();
                String thisDescr = taskDescrEditText.getText().toString();
                Date thisDOC = taskDOCEditText.toString();
                int thisId = Singleton.myTasks.size() + 1;

                // Create the task, set the attributes
                MainTask thisTask = new MainTask();
                thisTask.setTitle(thisTitle);
                thisTask.setDescription(thisDescr);
                thisTask.setTaskId(thisId);

                // Add the Task
                Singleton.getInstance().addTask(thisTask);

                // Clear the Fields
                taskTitleEditText.setText("");
                taskDescrEditText.setText("");


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
