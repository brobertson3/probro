package com.beli.probro.Databases;

/**
 * Created by allmine on 4/14/15.
 */
public class Tasks {
//TODO in this have to add g/setters for each of the other columns
    private long id;
    private String task;
    private int totalTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //Getter for the task
    public String getTask() {
        return task;
    }

    //Setter for the task
    public void setTask(String task) {
        this.task = task;
    }

    //Getter for the total time
    public int getTotalTime() {
        return totalTime;
    }

    //Setter for the total time
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return task;
    }

}
