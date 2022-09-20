package com.CFTodo.Taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class All_Tasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        Button backBttn = All_Tasks.this.findViewById(R.id.AllTasksActivityBackBTN);

        backBttn.setOnClickListener(view -> {
            Intent goToMainTaskActivity = new Intent(All_Tasks.this, MainActivity.class);
            startActivity(goToMainTaskActivity);
        });
    }
}