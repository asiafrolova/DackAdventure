package com.example.dackadventure;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "score")
public class Count {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "count")
    private int count;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;


    public Count(int count, String date, String time) {
        this.count = count;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
