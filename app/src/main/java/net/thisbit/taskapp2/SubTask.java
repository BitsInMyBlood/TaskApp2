package net.thisbit.taskapp2;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by christian on 4/18/2016.
 */
public class SubTask extends Task implements Serializable {


    private String title;
    private String description;
    private Calendar taskEDOC;
    private String taskEDOCString;


    public SubTask() {

    }

    public SubTask(String t, String d, Calendar c, String e) {
        this.title = t;
        this.description = d;
        this.taskEDOC = c;
        this.taskEDOCString = e;
    }

    public String toString() {
        return "" + title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() { return description;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getEDOCString(){
        return taskEDOCString;
    }

    public void setEDOCString(){
        this.taskEDOCString = taskEDOC.getTime().toString().substring(0,10) + "," + taskEDOC.getTime().toString().substring(24,28);
    }

    public Calendar getDOC() {
        return taskEDOC;
    }

    public void setTaskEDOS(Calendar d) {
        this.taskEDOC = d;
        this.setEDOCString();
    }





}
