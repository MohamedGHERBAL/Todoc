package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 14/09/2021
 */

@Dao
public interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);

    @Query("SELECT * FROM Projects")
    LiveData<List<Project>> getAllProjects();

    @Query("SELECT * FROM Projects WHERE id = :projectId")
    LiveData<Project> getProject(long projectId);
}
