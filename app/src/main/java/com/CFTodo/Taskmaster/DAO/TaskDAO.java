package com.CFTodo.Taskmaster.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.CFTodo.Taskmaster.models.Task;

import java.util.List;

// TODO-2:Create the dao to handle database.
@Dao
public interface TaskDAO {

    @Insert
    public void insertATask(Task task);

    //TODO-2 create the find all database query
    @Query("SELECT * FROM Task")
    public List<Task> findAll();

    //TODO-3 find by type
    @Query("SELECT * FROM Task WHERE state = :state")
    public List<Task> findAllByState(Task.TaskStateEnum state);

    @Query("SELECT * FROM Task ORDER BY state ASC")
    public List<Task> findAllSortedByName();

    //TODO-4 find by id
    @Query("SELECT * FROM Task WHERE id = :id")
    Task findByAnId(Long id);
}
