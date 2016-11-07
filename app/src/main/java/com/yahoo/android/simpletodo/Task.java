package com.yahoo.android.simpletodo;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by yboini on 11/6/16.
 */
@Table(database = TaskDatabase.class)
public class Task extends BaseModel implements Serializable {
    @PrimaryKey
    @Column
    private String name;
    @Column
    private String date;
    @Column
    private String priority;
    @Column
    private String status;

    public Task() {

    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
