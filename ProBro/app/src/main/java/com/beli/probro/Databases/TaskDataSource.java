package com.beli.probro.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allmine on 4/14/15.
 */
public class TaskDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_ACTIVITY, MySQLiteHelper.COLUMN_TOTAL_TIME,
            MySQLiteHelper.COLUMN_ELAPSED_TIME, MySQLiteHelper.COLUMN_COMPLETED};

    public TaskDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Tasks createTask(String task) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ACTIVITY, task);
        //TODO Need to add puts for the other 3 columns...also need to be added to the parameters
        //This inserts a row into the database
        long insertId = database.insert(MySQLiteHelper.TABLE_NAME, null,
                values);
        //query the database and return all columns of the row that it to be inserted???
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Tasks newTask = cursorToTask(cursor);
        cursor.close();
        return newTask;
    }

    public void deleteTask(Tasks task) {
        long id = task.getId();
        System.out.println("Task deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }
    //Creates a new list of tasks that gets filled with each row of the database...Returns list
    public List<Tasks> getAllTasks() {
        List<Tasks> tasks = new ArrayList<Tasks>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tasks task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return tasks;
    }
    //Takes the current cursor position and sets new task object with data from the database
    private Tasks cursorToTask(Cursor cursor) {
        Tasks task = new Tasks();
        task.setId(cursor.getLong(0));
        task.setTask(cursor.getString(1));
        return task;
    }

}
