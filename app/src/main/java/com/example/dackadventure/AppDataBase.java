package com.example.dackadventure;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Count.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static final String DB_NAME="score";
    private static AppDataBase instance;
    public static synchronized AppDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context,AppDataBase.class,DB_NAME).fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract CountDao countDao();
}
