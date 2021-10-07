package com.cleanup.todoc.roomdbtest;


import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 28/09/2021
 */
@RunWith(AndroidJUnit4.class)
public class ProjectAndTaskDaoTest {

    // FOR DATA
    private TodocDatabase database;
    private ProjectDao projectDao;
    private TaskDao taskDao;

    // DATA SET FOR TEST
    private static final long ID = 0;
    private static final long PROJECT_ID = 1;
    private static final Project PROJECT_TEST = new Project(PROJECT_ID, "PROJECT_TEST", 0xFFEADAD1);
    private static final Task TASK_TEST_1 = new Task(ID, PROJECT_ID, "Task Test 1", 1);
    private static final Task TASK_TEST_2 = new Task(ID, PROJECT_ID, "Task Test 2", 2);
    private static final Task TASK_TEST_3 = new Task(ID, PROJECT_ID, "Task Test 3", 3);

    // RULES
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() {
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();

        projectDao = database.projectDao();
        taskDao = database.taskDao();
    }

    @After
    public void closeDb() {
        database.close();
    }

    // TEST
    @Test
    public void insertAndGetTask() throws InterruptedException {
        // BEFORE : Adding a new project
        projectDao.createProject(PROJECT_TEST);
        taskDao.insertTask(TASK_TEST_1);
        taskDao.insertTask(TASK_TEST_2);
        taskDao.insertTask(TASK_TEST_3);

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(taskDao.getTasks());
        assertEquals(3, tasks.size());
    }

    @Test
    public void insertAndUpdateTask() throws InterruptedException {
        // BEFORE : Adding demo project & demo tasks. Next, update item added & re-save it
        projectDao.createProject(PROJECT_TEST);
        taskDao.insertTask(TASK_TEST_1);
        List<Task> taskAdded = LiveDataTestUtil.getValue(taskDao.getTasks());
        assertEquals("Task Test 1", taskAdded.get(0).getName());
        Task task = taskAdded.get(0);
        task.setName("Task Test 2");

        // TEST
        taskDao.updateTask(task);
        List<Task> listedTask = LiveDataTestUtil.getValue(this.taskDao.getTasks());
        assertEquals("Task Test 2", listedTask.get(0).getName());
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        //  BEFORE : Adding demo user & demo item. Next, get the item added & delete it.
        projectDao.createProject(PROJECT_TEST);
        taskDao.insertTask(TASK_TEST_1);
        List<Task> taskAdded = LiveDataTestUtil.getValue(this.taskDao.getTasks());
        assertEquals("Task Test 1", taskAdded.get(0).getName());
        assertEquals(1, taskAdded.size());

        // TEST
        taskDao.deleteTask(taskAdded.get(0));
        List<Task> listedTasks = LiveDataTestUtil.getValue(this.taskDao.getTasks());
        assertEquals(0, listedTasks.size());
    }
}