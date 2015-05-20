package com.beli.probro.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by allmine on 4/8/15.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "activities";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACTIVITY = "activity";
    public static final String COLUMN_TOTAL_TIME = "total";
    public static final String COLUMN_ELAPSED_TIME = "elapsed";
    public static final String COLUMN_COMPLETED = "completed";

    private static final String DATABASE_NAME = "activities.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE_ENTRIES = "create table "
            + TABLE_NAME + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_ACTIVITY
            + " text not null, " + COLUMN_TOTAL_TIME
            + " integer, " + COLUMN_ELAPSED_TIME
            + " integer, " + COLUMN_COMPLETED
            + " integer);";

    private static final String DATABASE_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DATABASE_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
