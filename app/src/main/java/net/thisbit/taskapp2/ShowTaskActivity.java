package net.thisbit.taskapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowTaskActivity extends AppCompatActivity {
    private int currentTaskItem = 0;
    private String thisTitle;
    private String thisDescription;
    private int thisTaskId;

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

        TextView taskTitleFieldTextView = (TextView) findViewById(R.id.showTaskTitleTextView);
        taskTitleFieldTextView.setText(thisTitle);
        TextView taskDescFieldTextView = (TextView) findViewById(R.id.showTaskDescrTextView);
        taskDescFieldTextView.setText(thisDescription);
        TextView taskIdFieldTextView = (TextView) findViewById(R.id.showTaskIdTextView);
        taskIdFieldTextView.setText(thisTaskId);
    }
}
