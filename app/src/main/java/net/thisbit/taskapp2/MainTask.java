package net.thisbit.taskapp2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by christian on 4/18/2016.
 */
public class MainTask extends Task implements Serializable{

    private ArrayList<SubTask> subTasks;
    private int taskId;
    private Date taskEDOC;
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


    public void addSubTask(SubTask thisSubTask) {
        thisSubTask.setTaskId(subTasks.size() + 1);
        subTasks.add(thisSubTask);
    }
}
