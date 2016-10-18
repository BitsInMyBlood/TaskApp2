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

public class AddSubTaskActivity extends AppCompatActivity {

    EditText subtaskTitleEditText;
    EditText subtaskDescrEditText;
    public static TextView subtaskTextViewObj;
    public static Calendar thisCal;
    String thisTitle = "";
    String thisDescr = "";
    String thisEDOC = "EDOC";
    int currentTaskItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_task);
        setTitle("Add a SubTask");

        // grab the task and subtask positions
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

        // setup the textview objects
        thisCal = AddSubTaskDatePickerFragment.getCal();
        subtaskTextViewObj = (TextView) findViewById(R.id.addSubTaskEDOCTextView);
        subtaskTextViewObj.setText(thisCal.getTime().toString().substring(0,10) + "," + thisCal.getTime().toString().substring(24,28));
        subtaskTitleEditText = (EditText) findViewById(R.id.subTaskTitleEditText);
        subtaskDescrEditText = (EditText) findViewById(R.id.subTaskDescrEditText);

        final Button saveButton = (Button) findViewById(R.id.subTaskSaveButton);
        assert saveButton != null;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();

                thisTitle = subtaskTitleEditText.getText().toString();
                thisDescr = subtaskDescrEditText.getText().toString();
                thisEDOC = subtaskTextViewObj.getText().toString();

                SubTask thisSubTask = new SubTask();
                thisSubTask.setTitle(thisTitle);
                thisSubTask.setDescription(thisDescr);
                thisSubTask.setTaskEDOS(thisCal);
                thisSubTask.setEDOCString();
                // Add the SubTask
                Singleton.getInstance().getTask(currentTaskItem).addSubTask(thisSubTask);

                // Declare that its done
                CharSequence text = "Saved";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

                // Clear the Fields
                subtaskTitleEditText.setText("");
                subtaskDescrEditText.setText("");
                subtaskTextViewObj.setText("");

                // Save the changes
                write();

                startActivity(new Intent(getApplicationContext(), ShowTaskActivity.class));
                Intent i = new Intent(AddSubTaskActivity.this, ShowTaskActivity.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("position", currentTaskItem);
                startActivity(i);


            }
        });


    }

    public void endThisActivity() {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void cancelButtonOnClick(View v) {
        Intent addSubTaskIntent = new Intent(v.getContext(), ShowTaskActivity.class);
        addSubTaskIntent.putExtra("position", currentTaskItem);
        startActivityForResult(addSubTaskIntent, 0);
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
        DialogFragment newFragment = new AddSubTaskDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
