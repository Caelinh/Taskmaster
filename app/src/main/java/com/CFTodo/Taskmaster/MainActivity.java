package com.CFTodo.Taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.CFTodo.Taskmaster.adapter.TaskRecyclerViewAdapter;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String TASK_TITLE_TAG = "taskTitle";
    public static final String TASK_DESCRIPTION_TAG = "taskDescription";
    public static final String TASK_STATUS_TAG = "taskStatus";
    public static final String Tag = "MainActivity";

    //Instantiating list
    List<Task> Tasks = null;
    TaskRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Tasks = new ArrayList<>();
        setUpButtons();
        setUpTaskRecyclerView();
        QueryDB();

//        Team newTeam = Team.builder()
//                .name("Red")
//                .build();
//        Amplify.API.mutate(
//                ModelMutation.create(newTeam),
//                success -> Log.i(Tag, "Worked"),
//                failure -> Log.i(Tag, "Didn't work")
//        );
//        Team newTeam2 = Team.builder()
//                .name("Blue")
//                .build();
//        Amplify.API.mutate(
//                ModelMutation.create(newTeam2),
//                success -> Log.i(Tag, "Worked"),
//                failure -> Log.i(Tag, "Didn't work")
//        );
//        Team newTeam3 = Team.builder()
//                .name("Yellow")
//                .build();
//        Amplify.API.mutate(
//                ModelMutation.create(newTeam3),
//                success -> Log.i(Tag, "Worked"),
//                failure -> Log.i(Tag, "Didn't work")
//        );
    }


    @Override
    protected void onResume() {
        super.onResume();
        //get user name
        String userName = preferences.getString(UserSettingsActivity.USER_NAME_TAG, "No name");
        //display name to page
        TextView userNameEdited = findViewById(R.id.mainActivityTextView);
        userNameEdited.setText(userName + "'s Tasks");
        QueryDB();
    }

    private void setUpTaskRecyclerView() {
        // 1. Grabbing recycler
        RecyclerView TaskRecyclerView = findViewById(R.id.MainActivityTaskRecyclerVIew);

        //2. setup layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        TaskRecyclerView.setLayoutManager(layoutManager);

        adapter = new TaskRecyclerViewAdapter(Tasks, this);
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

    private void QueryDB(){
        Amplify.API.query(
                ModelQuery.list(Task.class),
                successResponse -> {
                    Log.i(Tag,"Read Task successfully");
                    Tasks.clear();
                    for (Task dataBaseTasks : successResponse.getData()){
                        Tasks.add(dataBaseTasks);
                    }
                    runOnUiThread(() ->{
                        adapter.notifyDataSetChanged();
                    });
                },
                failureResponse -> {
                    Log.i(Tag,"Did not read Tasks successfully.");
                }
        );
    }



}