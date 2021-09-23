package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 14/09/2021
 */

public class TaskDataRepository {

    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) { this.taskDao = taskDao; }

    // --- GET ---
    public LiveData<List<Task>> getTasks() {
        MutableLiveData<List<Task>> response = new MutableLiveData<>();
        response.setValue(this.taskDao.getTasks());
        return response;
    }

    // --- CREATE ---
    public void createTask(Task task) { taskDao.insertTask(task); }

    // --- DELETE ---
    public void deleteTask(Task task) { taskDao.deleteTask(task); }

    // --- UPDATE ---
    public void updateTask(Task task) { taskDao.updateTask(task); }

}
