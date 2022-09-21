package com.CFTodo.Taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
        if(callingIntent != null){
            TaskTitle = callingIntent.getStringExtra(MainActivity.TASK_TITLE_TAG);
            TextView defaultTitle = findViewById(R.id.TaskDetailActivityTitleTextView);
            defaultTitle.setText(TaskTitle);
        }
    }
}