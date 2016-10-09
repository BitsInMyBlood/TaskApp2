package net.thisbit.taskapp2;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by christian on 4/18/2016.
 */
public class SubTask extends Task implements Serializable {


    private String title;
    private ArrayList<SubTask> subTasks;
    private Calendar taskEDOC;
    private String taskEDOCString;


    public SubTask() {

    }

    public String toString() {
        return title + " , " + description;
    }

    public ArrayList getSubTasks() {
        return subTasks;
    }

    public void setEDOCString(String s){
        this.taskEDOCString = taskEDOC.getTime().toString();
    }

    public void setTaskEDOS(Calendar d) {
        this.taskEDOC = d;
        setEDOCString(d.getTime().toString());
    }

    public void addSubTask(SubTask subTask) {
        this.addSubTask(subTask);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
