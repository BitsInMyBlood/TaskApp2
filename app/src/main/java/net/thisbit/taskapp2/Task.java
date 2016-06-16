package net.thisbit.taskapp2;
import java.io.Serializable;
/**
 * Created by christian on 4/18/2016.
 */
public class Task implements Serializable {
    String title;
    boolean isComplete = false;
    String description = "";
    int taskId;

    public Task(){

    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;



    }

    public String toString() {
        return "ID:" + this.getTaskId() + "Title:" + this.getTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {return description;}

    public void setDescription(String desc) {this.description = desc;}

    public void setComplete() {this.isComplete = true;}

    public boolean getComplete() {return isComplete;}

    public int getTaskId() { return taskId;}

    public void setTaskId(int Id) {
        this.taskId = Id;
    }


    }




}
