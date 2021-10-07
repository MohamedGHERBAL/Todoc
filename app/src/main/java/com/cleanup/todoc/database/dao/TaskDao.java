package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 14/09/2021
 */

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Tasks")
    LiveData<List<Task>> getTasks();

    @Insert
    long insertTask(Task task);

    @Update
    int updateTask(Task task);

    @Delete
    int deleteTask(Task task);
}
