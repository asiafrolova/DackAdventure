package com.example.dackadventure;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CountDao {
    @Query("Select * from score")
    public List<Count> getAll();


    @Insert
    void insert(Count count);

    @Query("DELETE FROM score")
    public void deleteAll();
    @Update
    public void updateCount(Count count);

    @Delete
    public  void delete(Count count);
}
