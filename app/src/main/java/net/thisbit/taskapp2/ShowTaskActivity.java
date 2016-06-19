package net.thisbit.taskapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ShowTaskActivity extends AppCompatActivity {
    private int currentTaskItem = 0;
    private String thisTitle;
    private String thisDescription;
    private String thisTaskId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);


        if(savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                currentTaskItem = 0;
            } else {
                currentTaskItem = extras.getInt("position");

            }
        } else {
            currentTaskItem = (int) savedInstanceState.getSerializable("position");

        }
        //
        MainTask thisMainTask = Singleton.getInstance().getMainTask(currentTaskItem);
        thisTitle = thisMainTask.getTitle();
        thisDescription = thisMainTask.getDescription();
        thisTaskId = thisMainTask.getTaskId();

        TextView taskTitleFieldTextView = (TextView) findViewById(R.id.showTaskTitleTextView);
        taskTitleFieldTextView.setText(thisTitle);
        TextView taskDescFieldTextView = (TextView) findViewById(R.id.showTaskDescrTextView);
        taskDescFieldTextView.setText(thisDescription);
        TextView taskIdFieldTextView = (TextView) findViewById(R.id.showTaskIdTextView);
        taskIdFieldTextView.setText(thisTaskId);
    }

    public void isCompleteOnClick(View v) {
        MainTask thisTask = Singleton.getInstance().getMainTask(currentTaskItem);
        thisTask.isComplete = true;
        Singleton.getInstance().getMyTasks().remove(currentTaskItem);
        write();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));


    }

    public void editTaskOnClick(View v) {
        Intent showTaskIntent = new Intent(v.getContext(), EditTaskActivity.class);
        showTaskIntent.putExtra("position", currentTaskItem);


        startActivityForResult(showTaskIntent, 0);
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
