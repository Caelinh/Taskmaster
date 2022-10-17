package com.CFTodo.Taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.CFTodo.Taskmaster.R;
import com.amplifyframework.core.Amplify;

public class AuthenticateUserActivity extends AppCompatActivity {
    public static final String TAG = "VerifyAccountActivity";
    public static final String VERIFY_ACCOUNT_EMAIL_TAG = "Verify_Account_Email_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate_user);
        setUpVerifyForm();

    }

    private void setUpVerifyForm() {
        Intent callingIntent = getIntent();
        String userEmail = callingIntent.getStringExtra(UserLoginActivity.SIGNUP_EMAIL_TAG);
        findViewById(R.id.AuthenticateAccountSubmitBtn).setOnClickListener(view -> {
            String verificationCode = ((EditText) findViewById(R.id.AuthenticationCode)).getText().toString();

            Amplify.Auth.confirmSignUp(
                    userEmail,
                    verificationCode,
                    success -> {
                        Log.i(TAG, "Verification succeeded: " + success.toString());
                        Intent goToLogInActivity = new Intent(AuthenticateUserActivity.this, MainActivity.class);
                        goToLogInActivity.putExtra(VERIFY_ACCOUNT_EMAIL_TAG, userEmail);
                        startActivity(goToLogInActivity);
                    },
                    failure -> {
                        Log.i(TAG, "Verification failed with username: " + "ed@codefellows.com" + " with this message: " + failure.toString());
                        runOnUiThread(() -> Toast.makeText(AuthenticateUserActivity.this, "Verify account failed!", Toast.LENGTH_SHORT));
                    }
            );
        });
    }

}