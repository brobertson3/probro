package com.beli.probro.Databases;

/**
 * Created by allmine on 4/14/15.
 */
public class Tasks {
//TODO in this have to add g/setters for each of the other columns
    private long id;
    private String task;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return task;
    }

}
