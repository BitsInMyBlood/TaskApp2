package net.thisbit.taskapp2;
import java.io.Serializable;

/**
 * Created by christian on 4/18/2016.
 */
public class SubTask extends Task implements Serializable {


    private String title;


    public SubTask() {

    }

    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
