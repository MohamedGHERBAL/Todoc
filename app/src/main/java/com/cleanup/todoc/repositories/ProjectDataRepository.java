package com.cleanup.todoc.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 14/09/2021
 */

public class ProjectDataRepository {

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) { this.projectDao = projectDao; }

    // --- GET PROJECT ---
    public LiveData<List<Project>> getProjects() {
        MutableLiveData<List<Project>> response = new MutableLiveData<>();
        response.setValue(this.projectDao.getAllProjects());
        return response;
    }

    public MutableLiveData<Project> getProject(long projectId) {
        MutableLiveData<Project> response = new MutableLiveData<>();
        response.setValue(this.projectDao.getProject(projectId));
        return response;
    }
}
