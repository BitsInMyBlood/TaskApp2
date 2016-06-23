package net.thisbit.taskapp2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Created by christian on 4/18/2016.
 */
public class Singleton {


    private static Singleton mInstance = null;
    public static ArrayList<MainTask> myTasks = new ArrayList<MainTask>();
    public static ArrayList<MainTask> completedTasks = new ArrayList<MainTask>();

    public static Singleton getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton();
        }

        return mInstance;

    }

    public void addTask(MainTask t) {
        t.setTaskId(" " + (myTasks.size()*2+1));
        myTasks.add(t);

    }

    public void removeTask(int position) {
        completedTasks.add(myTasks.get(position));
        myTasks.remove(position);
    }

    public int getNumCompletedTasks() { return completedTasks.size();}

    public int getNumberOfTasks() {
        return myTasks.size();
    }

    public MainTask getMainTask(int position) {
        return myTasks.get(position);
    }

    public ArrayList getMyTasks() {
        return myTasks;
    }

    public void restoreMyTasks(ArrayList a) {
        myTasks = a;
    }
}
