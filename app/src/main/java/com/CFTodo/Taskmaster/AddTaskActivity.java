package com.CFTodo.Taskmaster;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.*;

import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    // Setup shared pref
    SharedPreferences sharedPreferences;
    // preferences tags all activities can reference this tag
    public static final String Tasks = "Task";
    public static final String TaskDescription = "TaskDescription";
    public static final String Tag = "AddTaskActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String task = sharedPreferences.getString(Tasks,"No tasks found");
        String description = sharedPreferences.getString(TaskDescription,"No tasks found");
        // if it has value, lets display that to the page
        if(!task.isEmpty()){
            EditText taskEdited = findViewById(R.id.AddTextActivityTextViewInputTask);
        }
        setUpSubmitButton(sharedPreferences);
        //initialize DB

        setUpStateSpinner();

    }

    private void setUpStateSpinner(){
        //TODO Wire up spinner from design
        Spinner typeStateSpinner = findViewById(R.id.AddTaskViewSpinner);
        typeStateSpinner.setAdapter(new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                TaskStateEnum.values()
        ));
    }
    private void setUpSubmitButton(SharedPreferences sharedPreferences) {
        Button submitTaskButton = AddTaskActivity.this.findViewById(R.id.AddTextActivityTextViewAddTaskBTN);
        TextView submitted = AddTaskActivity.this.findViewById(R.id.AddTextActivityTextViewHiddenTxt);
        Spinner taskStateSpinner = findViewById(R.id.AddTaskViewSpinner);

        submitTaskButton.setOnClickListener(view -> {
            submitted.setText("Task Submitted");
            //setup shared preferences to edit. It is implicitly read-only
            SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();
            //Grabbing values from the form
            //TODO gather all data from inputs
            String taskInput = ((EditText) findViewById(R.id.AddTextActivityTextViewInputTask)).getText().toString();
            String taskDescription = ((EditText) findViewById(R.id.AddTextActivityTextViewInputEntry)).getText().toString();

            //save to shared preferences
            preferenceEditor.putString(Tasks, taskInput);
            preferenceEditor.putString(TaskDescription, taskDescription);
            preferenceEditor.apply(); // Have to put this in to save

            Toast.makeText(AddTaskActivity.this, "Task saved", Toast.LENGTH_SHORT).show();

            //TODO Create a new date object
            String currentDateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
            //TODO from string to enumStart here!!!! gotta get this fixed
//            Task.TaskStateEnum TaskStateEnum = Task.TaskStateEnum.fromString(taskStateSpinner.getSelectedItem().toString());
            //TODO create a new task obj.
            Task newTask = Task.builder()
                    .title(taskInput)
                    .description(taskDescription)
                    .state((TaskStateEnum) taskStateSpinner.getSelectedItem())
                    .dateCreated(new Temporal.DateTime(currentDateString))
                    .build();
            //TODO insert into DB
            Amplify.API.mutate(
                    ModelMutation.create(newTask),
                    successResponse -> Log.i(Tag, "AddTaskActivity made a task successfully"),
                    failureResponse -> Log.i(Tag, "AddTaskActivity failed with this response: " + failureResponse)
            );
            //TODO redirect to main
            Intent goToMainActivity = new Intent(AddTaskActivity.this,MainActivity.class);
            startActivity(goToMainActivity);

        });
    }




}