package com.CFTodo.Taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String TASK_TITLE_TAG = "taskTitle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

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


    @Override
    protected void onResume() {
        super.onResume();
        //get user name
        String userName = preferences.getString(UserSettingsActivity.USER_NAME_TAG, "No name");
        //display name to page
        TextView userNameEdited = findViewById(R.id.mainActivityTextView);
        userNameEdited.setText(userName + "'s Tasks");
        SelectTask();
    }

    private void SelectTask() {
        Button task1 = findViewById(R.id.MainActivityTask1Btn);
        Button task2 = findViewById(R.id.MainActivityTask2Btn);
        Button task3 = findViewById(R.id.MainActivityTask3Btn);

        Intent goToTaskDetails = new Intent(MainActivity.this,TaskDetailActivity.class);

        task1.setOnClickListener(view -> {
            goToTaskDetails.putExtra(TASK_TITLE_TAG,"Make My Bed");
            startActivity(goToTaskDetails);});
        task2.setOnClickListener(view -> {
            goToTaskDetails.putExtra(TASK_TITLE_TAG,"Take Out The Trash");
            startActivity(goToTaskDetails);});
        task3.setOnClickListener(view -> {
            goToTaskDetails.putExtra(TASK_TITLE_TAG,"Mow The Lawn");
            startActivity(goToTaskDetails);});


    }


    private void SetUpTaskDisplay(){
        Intent callingIntent = getIntent();
        String TaskList = null;
        if(callingIntent != null){
            TaskList = callingIntent.getStringExtra(All_Tasks.PRODUCT_NAME_EXTRA);
        }
        //put in list text view area here
//        TextView OrderFormTaskDisplay = findViewById();

    }


}