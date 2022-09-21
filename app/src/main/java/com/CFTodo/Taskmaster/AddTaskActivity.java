package com.CFTodo.Taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {
    // Setup shared pref
    SharedPreferences sharedPreferences;
    // preferences tags all activities can reference this tag
    public static final String Task = "Task";
    public static final String TaskDescription = "TaskDescription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String task = sharedPreferences.getString(Task,"No tasks found");
        String description = sharedPreferences.getString(TaskDescription,"No tasks found");
        // if it has value, lets display that to the page
        if(!task.isEmpty()){
            EditText taskEdited = findViewById(R.id.AddTextActivityTextViewInputTask);
        }
        setUpSubmitButton(sharedPreferences);


    }

    private void setUpSubmitButton(SharedPreferences sharedPreferences) {
        Button submitTaskButton = AddTaskActivity.this.findViewById(R.id.AddTextActivityTextViewAddTaskBTN);
        TextView submitted = AddTaskActivity.this.findViewById(R.id.AddTextActivityTextViewHiddenTxt);

        submitTaskButton.setOnClickListener(view -> {
            submitted.setText("Task Submitted");
            //setup shared preferences to edit. It is implicitly read-only
            SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();
            //Grabbing values from the form
            String taskInput = ((EditText) findViewById(R.id.AddTextActivityTextViewInputTask)).getText().toString();
            String taskDescription = ((EditText) findViewById(R.id.AddTextActivityTextViewInputEntry)).getText().toString();

            //save to shared preferences
            preferenceEditor.putString(Task, taskInput);
            preferenceEditor.putString(TaskDescription, taskDescription);
            preferenceEditor.apply(); // Have to put this in to save

            Toast.makeText(AddTaskActivity.this, "Task saved", Toast.LENGTH_SHORT).show();

        });
    }


}