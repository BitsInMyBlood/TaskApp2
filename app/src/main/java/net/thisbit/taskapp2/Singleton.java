package net.thisbit.taskapp2;
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
        myTasks.add(t);

    }

    public MainTask getTask(int p) {
        return myTasks.get(p);
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

    public ArrayList getCompletedTasks() { return completedTasks;}

    public void restoreMyTasks(ArrayList a) {
        myTasks = a;
    }
}
