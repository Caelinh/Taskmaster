package com.CFTodo.Taskmaster.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.CFTodo.Taskmaster.MainActivity;
import com.CFTodo.Taskmaster.R;
import com.CFTodo.Taskmaster.TaskDetailActivity;
import com.CFTodo.Taskmaster.TaskRecyclerActivity;
import com.CFTodo.Taskmaster.models.Task;

import java.util.List;

//step 3 create this
//step 13 clean this up by adding view holder inside of carrots after the extend wwhaever.adapter
public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskListViewHolder> {
    //10 hand in data items
    List<Task> tasks;
    // 14 hand in activity context
    Context callingActivity;

    //11 create a constructor

    public TaskRecyclerViewAdapter(List<Task> tasks, Context callingActivity) {
        this.tasks = tasks;
        this.callingActivity = callingActivity;
    }

    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //6. Inflate fragment. This is taking the XML and converting it to java objects. Basically a constructor for the fragment. assigned in taskfragment.java
        View taskFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent,false);
        return new TaskListViewHolder(taskFragment);// 8 bring in the class here and pass the fragment
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {
        // 11 bind the data to our fragment
        TextView taskTitleFragment = holder.itemView.findViewById(R.id.FragTextTaskTitle);
        String taskTitle = tasks.get(position).getTitle();
        String taskDescription = tasks.get(position).getDescription();
        String TaskStatus = tasks.get(position).getState().toString();
        taskTitleFragment.setText(taskTitle);

        // Make an on click handler so we can interact with recyclerview items
        View taskViewHolder = holder.itemView;

        taskViewHolder.setOnClickListener(view -> {
            Intent goToTaskDetails = new Intent(callingActivity, TaskDetailActivity.class);
            //TODO: pass data to task details here
            goToTaskDetails.putExtra(MainActivity.TASK_TITLE_TAG,taskTitle);
            goToTaskDetails.putExtra(MainActivity.TASK_DESCRIPTION_TAG,taskDescription);
            goToTaskDetails.putExtra(MainActivity.TASK_STATUS_TAG,TaskStatus);

            callingActivity.startActivity(goToTaskDetails);
        });
    }

    @Override
    public int getItemCount() {
//        return 100;// 9 Hardcode a large number of items for testing
        return tasks.size();
        // 12 make the size of list dynamic
    }
    // 7 create a class to hold the fragment in a viewholder

    public static class TaskListViewHolder extends RecyclerView.ViewHolder{

        public TaskListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
