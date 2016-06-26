package net.thisbit.taskapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class ShowTasksListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tasks_list);
        setTitle("Total Tasks: " + Singleton.getInstance().getNumberOfTasks());
        Load();

        final ArrayAdapter<MainTask> taskListArrayAdapter = new ArrayAdapter<MainTask>(this, android.R.layout.simple_list_item_1, Singleton.myTasks);
        final ListView listTasksListView = (ListView) findViewById(R.id.showTasksListView);
        listTasksListView.setAdapter(taskListArrayAdapter);
        listTasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent showTaskIntent = new Intent(view.getContext(), ShowTaskActivity.class);
                showTaskIntent.putExtra("position", position);

                startActivityForResult(showTaskIntent, 0);

            }

        });


    }

    public void Load() {
        ObjectInputStream ois = null;
        String filename = "myTasks.dat";

        try {
            ois = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(), "")+File.separator+filename)));
            ArrayList<MainTask> loadedTasks = (ArrayList) ois.readObject();
            Log.v("serialization", "Size of ArrayList: " + loadedTasks.size());
            ois.close();
            Singleton.getInstance().restoreMyTasks(loadedTasks);
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
