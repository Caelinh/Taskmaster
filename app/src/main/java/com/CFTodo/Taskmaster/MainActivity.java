package com.CFTodo.Taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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






    }


}