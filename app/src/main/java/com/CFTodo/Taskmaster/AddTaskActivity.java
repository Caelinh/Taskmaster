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
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddTaskActivity extends AppCompatActivity {

    public static final String Tasks = "Task";
    public static final String TaskDescription = "TaskDescription";
    public static final String Tag = "AddTaskActivity";
    CompletableFuture<List<Team>> teamFuture;
    ArrayList<String> teamNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        teamFuture = new CompletableFuture<>();
        teamNames = new ArrayList<>();

        completeTeamsFuture();
        setUpStateSpinner();
        setUpTeamSpinner();
        setUpSubmitButton();

    }
    private void completeTeamsFuture(){
        Amplify.API.query(
                ModelQuery.list(Team.class),
                success -> {
                    ArrayList<Team> teams = new ArrayList<>();
                    for (Team t : success.getData()){
                        teams.add(t);
                    }
                    teamFuture.complete(teams);
                },
                failure -> teamFuture.complete(null)
        );
    }
    private ArrayList<Team> getTeamsList(){
        ArrayList<Team> teams = new ArrayList<>();
        try {
            teams = (ArrayList<Team>)teamFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return teams;
    }

    private void setUpTeamSpinner(){
        ArrayList<String> names = new ArrayList<>();
        for(Team t : getTeamsList()){
            names.add(t.getName());
        }

        Spinner teamSpinner = findViewById(R.id.TeamSelectionSpinner);
        runOnUiThread(() -> teamSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                names)));
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
    private void setUpSubmitButton() {
        Button submitTaskButton = AddTaskActivity.this.findViewById(R.id.AddTextActivityTextViewAddTaskBTN);
        TextView submitted = AddTaskActivity.this.findViewById(R.id.AddTextActivityTextViewHiddenTxt);
        Spinner taskStateSpinner = findViewById(R.id.AddTaskViewSpinner);
        Spinner TeamSelectionSpinner = findViewById(R.id.TeamSelectionSpinner);
        String selectedTeamString = TeamSelectionSpinner.getSelectedItem().toString();
        List<Team> teams = getTeamsList();

        Team selectedTeam = teams.stream().filter(t -> t.getName().equals(selectedTeamString)).findAny().orElseThrow(RuntimeException::new);

        submitTaskButton.setOnClickListener(view -> {
            submitted.setText("Task Submitted");

            //Grabbing values from the form
            //TODO gather all data from inputs
            String taskInput = ((EditText) findViewById(R.id.AddTextActivityTextViewInputTask)).getText().toString();
            String taskDescription = ((EditText) findViewById(R.id.AddTextActivityTextViewInputEntry)).getText().toString();

            Toast.makeText(AddTaskActivity.this, "Task saved", Toast.LENGTH_SHORT).show();

            //TODO Create a new date object
            String currentDateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
            //TODO create a new task obj.
            Task newTask = Task.builder()
                    .title(taskInput)
                    .description(taskDescription)
                    .state((TaskStateEnum) taskStateSpinner.getSelectedItem())
                    .dateCreated(new Temporal.DateTime(currentDateString))
                    .team(selectedTeam)
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