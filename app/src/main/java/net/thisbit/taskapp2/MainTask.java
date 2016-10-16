package net.thisbit.taskapp2;
import java.io.Serializable;
import java.lang.reflect.Array;
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
    private ArrayList<SubTask> completedSubTasks = new ArrayList<SubTask>();
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

    public ArrayList<SubTask> getSubTasks(){ return subTasks;}

    public SubTask getSubTask(int p) {
        return this.subTasks.get(p);
    }

    public void addSubTask(SubTask t, int p) {
        subTasks.add(t);
        subTasks.remove(p);
    }
    public void removeSubTask(int p) {
        completedSubTasks.add(subTasks.get(p));
        subTasks.remove(p);
    }

    public void setEDOCString(String s){
        this.taskEDOCString = taskEDOC.getTime().toString();
    }

    public Calendar getDOC() {
        return taskEDOC;
    }

    public void setTaskEDOS(Calendar d) {
        this.taskEDOC = d;
        this.setEDOCString(d.getTime().toString());
    }

    public void addSubTask(SubTask thisSubTask) {
        subTasks.add(thisSubTask);
    }
}
