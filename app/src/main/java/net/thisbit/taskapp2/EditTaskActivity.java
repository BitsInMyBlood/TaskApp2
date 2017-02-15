package net.thisbit.taskapp2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class EditTaskActivity extends AppCompatActivity {
    private EditText thisEditTaskTitleEditText;
    private EditText thisEditTaskDescrEditText;
    public static TextView thisEditTaskEDOCTextView;
    private int currentTaskItem = 0;
    private String thisTitle;
    private String thisDescription;
    private String thisEDOC;
    public static Calendar thisCal;
    private ArrayList<SubTask> subTasks = new ArrayList<SubTask>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        setTitle("Edit Task");

        thisEditTaskTitleEditText = (EditText)findViewById(R.id.editTaskTitleEditText);
        thisEditTaskDescrEditText = (EditText)findViewById(R.id.editTaskDescriptionEditText);
        thisEditTaskEDOCTextView = (TextView)findViewById(R.id.editTaskEDOCTextView);


        // Grab the position of this task
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

        // setText for the current data to be modified

        thisEditTaskTitleEditText.setText(Singleton.getInstance().getMainTask(currentTaskItem).getTitle());
        thisEditTaskDescrEditText.setText(Singleton.getInstance().getMainTask(currentTaskItem).getDescription());
        thisEditTaskEDOCTextView.setText(Singleton.getInstance().getMainTask(currentTaskItem).getEDOCString());
        thisCal = Singleton.getInstance().getMainTask(currentTaskItem).getDOC();
        subTasks = Singleton.getInstance().getMainTask(currentTaskItem).getSubTasks();

        // Now we wait for the user to select "Save"

        final Button saveButton = (Button) findViewById(R.id.editTaskSaveButton);
        assert saveButton != null;

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();

                // We set the variables with current text value

                thisTitle = thisEditTaskTitleEditText.getText().toString();
                thisDescription = thisEditTaskDescrEditText.getText().toString();
                thisEDOC = thisEditTaskEDOCTextView.getText().toString();
                thisCal = EditTaskDatePickerFragment.getCal();

                // Create the task, set the attributes
                MainTask thisMainTask= new MainTask();
                thisMainTask.setTitle(thisTitle);
                thisMainTask.setDescription(thisDescription);
                thisMainTask.setTaskEDOS(thisCal);
                thisMainTask.addSubTasks(subTasks);

                // remove the old task
                Singleton.getInstance().removeTask(currentTaskItem);

                // ReAdd the Task
                Singleton.getInstance().addTask(thisMainTask);

                // Declare that its done
                CharSequence text = "Saved";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

                write();

                Intent addSubTaskIntent = new Intent(v.getContext(), ShowTasksListActivity.class);
                addSubTaskIntent.putExtra("position", currentTaskItem);
                startActivityForResult(addSubTaskIntent, 0);

            }
        });



    }

    public void homeButtonOnClick(View v) {
        Intent addSubTaskIntent = new Intent(v.getContext(), ShowTaskActivity.class);
        addSubTaskIntent.putExtra("position", currentTaskItem);
        startActivityForResult(addSubTaskIntent, 0);
    }


    public void endThisActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        Intent i = new Intent(EditTaskActivity.this, MainActivity.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }


    public void write(){
        ArrayList<MainTask> myTasks = Singleton.getInstance().getMyTasks();
        String filename = "myTasks";
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(getExternalFilesDir(null),"")+File.separator+filename));
            oos.writeObject(myTasks);
            oos.close();
        } catch (FileNotFoundException e) { e.printStackTrace();
        } catch (IOException e) { e.printStackTrace();
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new EditTaskDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
