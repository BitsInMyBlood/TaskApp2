package net.thisbit.taskapp2;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by christian on 4/18/2016.
 */
public class MainTask extends Task implements Serializable{

    private ArrayList<SubTask> subTasks;
    private int taskId;
    /**
     *
     */
    public MainTask() {

    }

    /**
     *
     * @param title

     */
    public MainTask(String title, String desc, int taskId) {
        this.title = title;
        this.description = desc;
        this.taskId = taskId;

    }

    public int getTaskId() { return taskId;}

    public void setTaskId(int taskId) { this.taskId = taskId;}

    public void addSubTask(SubTask thisSubTask) {
        subTasks.add(thisSubTask);
    }
}
