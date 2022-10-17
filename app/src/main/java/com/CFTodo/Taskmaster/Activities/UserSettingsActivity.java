package com.CFTodo.Taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.CFTodo.Taskmaster.R;

public class UserSettingsActivity extends AppCompatActivity {
        SharedPreferences sharedPreferences;

        public static final String USER_NAME_TAG = "UserName";

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String userName = sharedPreferences.getString(USER_NAME_TAG,"No User found");
            saveUserName();
            SetUpAcctCreationRedirect();

    }
    private void SetUpAcctCreationRedirect(){

        Button accountPage = findViewById(R.id.VerifyAccountButton);
        accountPage.setOnClickListener(view -> {

            Intent createAccount = new Intent(UserSettingsActivity.this,UserLoginActivity.class);
            startActivity(createAccount);
        });


    }
    private void saveUserName() {
        Button saveButton = UserSettingsActivity.this.findViewById(R.id.UserSettingsSaveBtn);

        saveButton.setOnClickListener(view ->{
            SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();

            String UsernameInput = ((EditText) findViewById(R.id.UserSettingsEditUsername)).getText().toString();

            preferenceEditor.putString(USER_NAME_TAG, UsernameInput);
            preferenceEditor.apply();

            Intent GoHome = new Intent(UserSettingsActivity.this,MainActivity.class);
            startActivity(GoHome);

            Toast.makeText(UserSettingsActivity.this, "User saved", Toast.LENGTH_SHORT).show();
        });
    }
}