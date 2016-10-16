package net.thisbit.taskapp2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class EditSubTaskActivity extends AppCompatActivity {

    private EditText editSubTaskTitleEditText;
    private EditText editSubTaskDescrEditText;
    public static TextView editSubTaskEDOCTextView;
    private int currentTaskItem = 0;
    private int currentSubTask = 0;
    private String thisTitle;
    private String thisDescription;
    private String thisEDOC;
    public static Calendar thisCal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sub_task);
        setTitle("Edit SubTask");

        editSubTaskTitleEditText =  (EditText) findViewById(R.id.editSubTaskTitleEditText);
        editSubTaskDescrEditText = (EditText) findViewById(R.id.editSubTaskDescrEditText);
        editSubTaskEDOCTextView = (TextView) findViewById(R.id.editSubTaskEDOCTextView);

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

        // setText for the current data to be modififed
        editSubTaskTitleEditText.setText(Singleton.getInstance().getMainTask(currentTaskItem).getSubTask(currentSubTask).getTitle());
        editSubTaskDescrEditText.setText(Singleton.getInstance().getMainTask(currentTaskItem).getSubTask(currentSubTask).getDescription());
        editSubTaskEDOCTextView.setText(Singleton.getInstance().getMainTask(currentTaskItem).getSubTask(currentSubTask).getEDOCString());

        // now we wait for the user to modify the fields, and press SAVE

        final Button saveButton = (Button) findViewById(R.id.editSubTaskSaveButton);
        assert saveButton != null;

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();

                // We set the variables with current text value

                thisTitle = ((EditText) findViewById(R.id.editSubTaskTitleEditText)).getText().toString();
                thisDescription = ((EditText) findViewById(R.id.editSubTaskDescrEditText)).getText().toString();
                thisEDOC = ((TextView) findViewById(R.id.editSubTaskEDOCTextView)).getText().toString();
                thisCal = EditSubTaskDatePickerFragment.getCal();

                // Create the task, set the attributes
                SubTask thisSubTask = new SubTask();
                thisSubTask.setTitle(thisTitle);
                thisSubTask.setDescription(thisDescription);
                thisSubTask.setTaskEDOS(thisCal);

                //
                Singleton.getInstance().getMainTask(currentTaskItem).addSubTask(thisSubTask, currentSubTask);

                // Declare that its done
                CharSequence text = "Saved";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

                write();

                endThisActivity();

            }
        });






    }

    public void endThisActivity() {
        startActivity(new Intent(getApplicationContext(), ShowTasksListActivity.class));
        Intent i = new Intent(EditSubTaskActivity.this, ShowTasksListActivity.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    public void editSubTaskShowDatePickerDialog(View v) {
        DialogFragment newFragment = new EditSubTaskDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
