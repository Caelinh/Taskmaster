package com.CFTodo.Taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.CFTodo.Taskmaster.R;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class UserLoginActivity extends AppCompatActivity {
    public static final String TAG = "SignUpActivity";
    public static final String SIGNUP_EMAIL_TAG = "Signup_Email_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Intent goToVerifyActivity = new Intent(UserLoginActivity.this, AuthenticateUserActivity.class);
        Button VerifyBttn = UserLoginActivity.this.findViewById(R.id.VerifyAccountButton);
        VerifyBttn.setOnClickListener(view -> startActivity(goToVerifyActivity));
        setUpSignUpForm();

    }



    private void setUpSignUpForm() {

        findViewById(R.id.createAccountButton).setOnClickListener(view -> {
            String userName = ((EditText) findViewById(R.id.CreateUserUserName)).getText().toString();
            String userEmail = ((EditText) findViewById(R.id.CreateUserEmail)).getText().toString();
            String userPassword = ((EditText) findViewById(R.id.CreateUserPassword)).getText().toString();
            Amplify.Auth.signUp(userEmail,
                    userPassword,
                    AuthSignUpOptions.builder()
                            .userAttribute(AuthUserAttributeKey.preferredUsername(), userName)
                            .userAttribute(AuthUserAttributeKey.email(), userEmail)
                            .build(),
                    success -> {
                        Log.i(TAG, "Signup success! " + success);
                        Intent goToVerifyActivity = new Intent(UserLoginActivity.this, AuthenticateUserActivity.class);
                        goToVerifyActivity.putExtra(SIGNUP_EMAIL_TAG, userEmail);
                        startActivity(goToVerifyActivity);
                    },
                    failure -> {
                        Log.i(TAG, "Signup failed with username " + userEmail + "with message: " + failure);
                        runOnUiThread(() -> Toast.makeText(UserLoginActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show());
                    }
            );

        });
    }
}