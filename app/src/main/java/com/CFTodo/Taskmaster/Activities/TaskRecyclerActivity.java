package com.CFTodo.Taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.CFTodo.Taskmaster.R;
import com.CFTodo.Taskmaster.adapter.TaskRecyclerViewAdapter;
import com.CFTodo.Taskmaster.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRecyclerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_recycler);
    }



}