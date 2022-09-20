package com.CFTodo.Taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button submitTaskButton = AddTaskActivity.this.findViewById(R.id.AddTextActivityTextViewAddTaskBTN);
        TextView submitted = AddTaskActivity.this.findViewById(R.id.AddTextActivityTextViewHiddenTxt);

        submitTaskButton.setOnClickListener( view -> submitted.setText("Task Submitted"));


    }
}