package net.thisbit.taskapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class ShowSubTaskActivity extends AppCompatActivity {

    private int currentTaskItem;
    private int currentSubTask;
    private String thisTitle;
    private String thisDescription;
    private String thisEDOCString;
    private Calendar thisEDOC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sub_task);
        setTitle("Show SubTask");

        // Grab the Extras task position, subtask position
        if(savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                currentTaskItem = 0;
                currentSubTask = 0;
            } else {
                currentTaskItem = extras.getInt("position");
                currentSubTask = extras.getInt("subtaskposition");
            }
        } else {
            currentTaskItem = (int) savedInstanceState.getSerializable("position");
            currentSubTask = (int) savedInstanceState.getSerializable("subtaskposition");

        }

        // Load the current MainTask

        SubTask thisSubTask = Singleton.getInstance().getMainTask(currentTaskItem).getSubTask(currentSubTask);
        thisTitle = thisSubTask.getTitle();
        thisDescription = thisSubTask.getDescription();
        thisEDOCString = thisSubTask.getEDOCString();

        // Populate the fields
        TextView taskTitleFieldTextView = (TextView) findViewById(R.id.showSubTaskTitleTextView);
        taskTitleFieldTextView.setText(thisTitle);

        TextView taskDescrTextView = (TextView) findViewById(R.id.showSubTaskDescrTextView);
        taskDescrTextView.setText(thisDescription);
        taskDescrTextView.setMovementMethod(new ScrollingMovementMethod());

        TextView subTaskEDOCTextView = (TextView) findViewById(R.id.editSubTaskEDOCTextView);
        assert subTaskEDOCTextView != null;
        subTaskEDOCTextView.setText(thisEDOCString);
    }

    public void editSubTaskOnClick(View v) {
        Intent showTaskIntent = new Intent(v.getContext(), EditSubTaskActivity.class);
        showTaskIntent.putExtra("position", currentTaskItem);
        showTaskIntent.putExtra("subtaskposition", currentSubTask);
        startActivityForResult(showTaskIntent, 0);
    }

    public void subTaskIsCompleteOnClick(View v) {
        Singleton.getInstance().getMainTask(currentTaskItem).removeSubTask(currentSubTask);
        write();
        finish();
        // Start the ShowTaskActivity
        startActivity(new Intent(getApplicationContext(), ShowTaskActivity.class));
        Intent i = new Intent(ShowSubTaskActivity.this, ShowTaskActivity.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("position", currentTaskItem);
        i.putExtra("subtaskposition", currentSubTask);
        startActivity(i);

    }

    public void backButtonOnClick(View v) {
        startActivity(new Intent(getApplicationContext(), ShowTaskActivity.class));
        Intent i = new Intent(ShowSubTaskActivity.this, ShowTaskActivity.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("position", currentTaskItem);
        i.putExtra("subtaskposition", currentSubTask);
        startActivity(i);
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
