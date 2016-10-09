package net.thisbit.taskapp2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by christian on 4/18/2016.
 */
public class MainTask extends Task implements Serializable{

    private ArrayList<SubTask> subTasks = new ArrayList<SubTask>();
    private Calendar taskEDOC;
    private String taskEDOCString;
    /**
     *
     */
    public MainTask() {

    }

    /**
     *
     * @param title

     */
    public MainTask(String title, String desc, String taskId) {
        this.title = title;
        this.description = desc;
        this.taskId = taskId;
    }

    public String getEDOCString(){
        return taskEDOCString.substring(0,10)+ ", "+taskEDOCString.substring(24,28);
    }

    public String getNumSubtasks() {
        return "" + subTasks.size();
    }

    public void setEDOCString(String s){
        this.taskEDOCString = taskEDOC.getTime().toString();
    }

    public Calendar getDOC() {
        return taskEDOC;
    }

    public void setTaskEDOS(Calendar d) {
        this.taskEDOC = d;
        setEDOCString(d.getTime().toString());
    }

    public void addSubTask(SubTask thisSubTask) {
        subTasks.add(thisSubTask);
    }
}
