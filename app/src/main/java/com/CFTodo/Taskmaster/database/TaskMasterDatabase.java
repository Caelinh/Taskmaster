package com.CFTodo.Taskmaster.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.CFTodo.Taskmaster.DAO.TaskDAO;
import com.CFTodo.Taskmaster.models.Task;

//TODO needs annotation
//TODO-6 Type Converter for the util.date
//TODO-6c pass in newly created converters to be used in the database
@TypeConverters({TaskMasterDatabaseConverters.class})
//TODO extend room database
@Database(entities = {Task.class}, version = 1)// if this is updated it will delete the db
public abstract class TaskMasterDatabase extends RoomDatabase {
        //TODO-5 add the dao's gives access to all the functions created to run inside of the dao
    public abstract TaskDAO taskDao();
}
