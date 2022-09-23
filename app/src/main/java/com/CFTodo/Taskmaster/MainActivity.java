package com.CFTodo.Taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.CFTodo.Taskmaster.adapter.TaskRecyclerViewAdapter;
import com.CFTodo.Taskmaster.database.TaskMasterDatabase;
import com.CFTodo.Taskmaster.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String TASK_TITLE_TAG = "taskTitle";
    public static final String TASK_DESCRIPTION_TAG = "taskDescription";
    public static final String TASK_STATUS_TAG = "taskStatus";
    public static final String DATABASE_NAME = "TaskMaster_db";
    TaskMasterDatabase taskMasterDatabase;
    //Instantiating list
    List<Task> Tasks = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        setUpButtons();

        //TODO-7 Initialize DB
        taskMasterDatabase = Room.databaseBuilder(
                        getApplicationContext(),
                        TaskMasterDatabase.class,
                        DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        Tasks = taskMasterDatabase.taskDao().findAll();
        setUpTaskRecyclerView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //get user name
        String userName = preferences.getString(UserSettingsActivity.USER_NAME_TAG, "No name");
        //display name to page
        TextView userNameEdited = findViewById(R.id.mainActivityTextView);
        userNameEdited.setText(userName + "'s Tasks");

        //TODO updating task list everytime
        Tasks.clear();
        Tasks.addAll(taskMasterDatabase.taskDao().findAll());
        //TODO left off here add recycler to be viewed wait for alex to post
    }

    private void setUpTaskRecyclerView() {
        // 1. Grabbing recycler
        RecyclerView TaskRecyclerView = findViewById(R.id.MainActivityTaskRecyclerVIew);

        //2. setup layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        TaskRecyclerView.setLayoutManager(layoutManager);

        TaskRecyclerViewAdapter adapter = new TaskRecyclerViewAdapter(Tasks, this);
        TaskRecyclerView.setAdapter(adapter);

    }

    private void setUpButtons() {
        Button addTaskBttn = MainActivity.this.findViewById(R.id.MainActivityAddTskBtn);

        addTaskBttn.setOnClickListener(view -> {
            Intent goToAddTaskActivity = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(goToAddTaskActivity);
        });
        Button ViewAllBtn = MainActivity.this.findViewById(R.id.MainActivityAllTasksBtn);

        ViewAllBtn.setOnClickListener(view -> {
            Intent goToAllTaskActivity = new Intent(MainActivity.this, All_Tasks.class);
            startActivity(goToAllTaskActivity);
        });

        Button settings = MainActivity.this.findViewById(R.id.MainActivityUsrSettingsBtn);

        settings.setOnClickListener(view -> {
            Intent goToSettings = new Intent(MainActivity.this, UserSettingsActivity.class);
            startActivity(goToSettings);
        });
    }



}