package com.example.dackadventure;

import android.app.Application;

import androidx.room.Room;

import java.util.List;

public class Repository {
    private boolean returnStart;
    private int count;
    private AppDataBase db;
    private static Repository instance;

    public Repository() {
        returnStart=false;
        count=0;

    }
    public static Repository getInstance(){
        if(instance==null){
            instance=new Repository();
        }
        return instance;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setNull() {
        this.count = 0;
    }
    public int increment(){
        count++;
        return count;
    }

    public boolean isReturnStart() {
        return returnStart;
    }

    public void setReturnStart(boolean returnStart) {
        this.returnStart = returnStart;
    }
}
