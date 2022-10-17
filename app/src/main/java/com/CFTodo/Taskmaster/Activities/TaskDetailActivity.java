package com.CFTodo.Taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.CFTodo.Taskmaster.R;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        ChangeTaskTitle();

    }

    private void ChangeTaskTitle(){
        Intent callingIntent = getIntent();
        String TaskTitle = "";
        String TaskDescription = "";
        String TaskStatus = "";
        if(callingIntent != null){
            TaskTitle = callingIntent.getStringExtra(MainActivity.TASK_TITLE_TAG);
            TaskDescription = callingIntent.getStringExtra(MainActivity.TASK_DESCRIPTION_TAG);
            TaskStatus = callingIntent.getStringExtra(MainActivity.TASK_STATUS_TAG);
            TextView defaultTitle = findViewById(R.id.TaskDetailActivityTitleTextView);
            TextView defaultDescription = findViewById(R.id.TaskDetailActivityTaskDecriptionTextView);
            TextView defaultStatus = findViewById(R.id.TaskDetailTextViewTaskStatus);
            defaultTitle.setText(TaskTitle);
            defaultDescription.setText(TaskDescription);
            defaultStatus.setText(TaskStatus);
        }
    }
}