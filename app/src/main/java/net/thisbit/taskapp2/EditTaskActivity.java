package net.thisbit.taskapp2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EditTaskActivity extends AppCompatActivity {
    EditText thisEditTaskTitleEditText;
    EditText thisEditTaskDescrEditText;
    private int currentTaskItem = 0;
    private String thisTitle;
    private String thisDescription;
    private String thisTaskId;
    private int thisPosition;
    private MainTask thisMainTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        thisEditTaskTitleEditText = (EditText) findViewById(R.id.editTaskTitleEditText);
        thisEditTaskDescrEditText = (EditText) findViewById(R.id.editTaskDescriptionEditText);

        if(savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                currentTaskItem = 0;
            } else {
                currentTaskItem = extras.getInt("position");
                thisPosition = extras.getInt("position");
            }
        } else {
            currentTaskItem = (int) savedInstanceState.getSerializable("position");

        }

    }

    public void editTaskSaveButtonOnClick(View v) {
        // Grab the current contents of Title and Desc. fields
        thisTitle = thisEditTaskTitleEditText.getText().toString();
        thisDescription = thisEditTaskDescrEditText.getText().toString();
        // Get the MainTask to update
        thisMainTask = Singleton.getInstance().getMainTask(currentTaskItem);
        // Update the task
        thisMainTask.setTitle(thisTitle);
        thisMainTask.setDescription(thisDescription);
        // Remove the old one
        Singleton.getInstance().removeTask(thisPosition);
        // Add the updated
        Singleton.getInstance().addTask(thisMainTask);
        // Save it
        write();
        // Close this activity
        finish();
        // Start a new activity, show tasks list
        startActivity(new Intent(getApplicationContext(), ShowTasksListActivity.class));
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


}
