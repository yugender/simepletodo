package com.yahoo.android.simpletodo;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by yboini on 11/6/16.
 */
@Database(name = TaskDatabase.NAME, version = TaskDatabase.VERSION)
public class TaskDatabase {
    public static final String NAME = "TaskDataBase";
    public static final int VERSION = 1;
}
