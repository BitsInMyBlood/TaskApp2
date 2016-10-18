package net.thisbit.taskapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ShowTaskActivity extends AppCompatActivity {
    private int currentTaskItem = 0;
    private String thisTitle = "";
    private String thisDescription;
    private String thisEDOC;
    private String thisNumSubTasks;
    private ArrayList<SubTask> thisSubTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        // grab the extras to show the task
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
        // Load the current task
        MainTask thisMainTask = Singleton.getInstance().getMainTask(currentTaskItem);
        thisTitle = thisMainTask.getTitle();
        thisDescription = thisMainTask.getDescription();
        thisEDOC = thisMainTask.getEDOCString();
        thisSubTasks = thisMainTask.getSubTasks();
        thisNumSubTasks = thisMainTask.getNumSubtasks();

        // populate the ListView with subtasks
        final ArrayAdapter<SubTask> subTaskListArrayAdapter = new ArrayAdapter<SubTask>(this, android.R.layout.simple_list_item_1, Singleton.myTasks.get(currentTaskItem).getSubTasks());
        final ListView listTasksListView = (ListView) findViewById(R.id.showTaskListView);
        listTasksListView.setAdapter(subTaskListArrayAdapter);
        listTasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent showSubTaskIntent = new Intent(view.getContext(), ShowSubTaskActivity.class);
                showSubTaskIntent.putExtra("subtaskposition", position);
                showSubTaskIntent.putExtra("position", currentTaskItem);
                finish();
                startActivityForResult(showSubTaskIntent, 0);

            }

        });
        // finished ListView population


        // populate the textviews with data
        TextView taskTitleFieldTextView = (TextView) findViewById(R.id.showTaskTitleTextView);
        taskTitleFieldTextView.setText(thisTitle);
        TextView taskDescFieldTextView = (TextView) findViewById(R.id.showTaskDescrTextView);
        taskDescFieldTextView.setText(thisDescription);
        TextView numSubTasksTextView = (TextView) findViewById(R.id.numSubTasksTextView);
        numSubTasksTextView.setText(thisNumSubTasks);

        TextView taskEDOCFieldTextView = (TextView) findViewById(R.id.showTaskEDOCTextView);
        assert taskEDOCFieldTextView != null;
        taskEDOCFieldTextView.setText(thisEDOC);
    }

    public void isCompleteOnClick(View v) {
        MainTask thisTask = Singleton.getInstance().getMainTask(currentTaskItem);
        thisTask.isComplete = true;
        Singleton.getInstance().removeTask(currentTaskItem);
        write();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void addSubTaskOnClick(View v) {
        Intent addSubTaskIntent = new Intent(v.getContext(), AddSubTaskActivity.class);
        addSubTaskIntent.putExtra("position", currentTaskItem);
        startActivityForResult(addSubTaskIntent, 0);

    }

    public void editTaskOnClick(View v) {
        Intent showTaskIntent = new Intent(v.getContext(), EditTaskActivity.class);
        showTaskIntent.putExtra("position", currentTaskItem);
        startActivityForResult(showTaskIntent, 0);
    }

    public void homeButtonOnClick(View v) {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
