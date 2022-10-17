package com.CFTodo.Taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import com.CFTodo.Taskmaster.R;

public class All_Tasks extends AppCompatActivity {
    public static final String PRODUCT_NAME_EXTRA = "productName";
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        Button backBttn = All_Tasks.this.findViewById(R.id.AllTasksActivityBackBTN);

        backBttn.setOnClickListener(view -> {
            Intent goToMainTaskActivity = new Intent(All_Tasks.this, MainActivity.class);
            startActivity(goToMainTaskActivity);
            //include extra(s) with the intent this sends data around. This should send a few tasks over
            goToMainTaskActivity.putExtra(PRODUCT_NAME_EXTRA,"Task thing");
        });

        preferences = PreferenceManager.getDefaultSharedPreferences(this);




    }

    @Override
    protected void onResume() {
        super.onResume();
        //Recalls requests and methods without resetting
        String Task = preferences.getString(AddTaskActivity.Tasks,"no Task");
        String TaskDescription = preferences.getString(AddTaskActivity.TaskDescription,"no Task description");
    }
}