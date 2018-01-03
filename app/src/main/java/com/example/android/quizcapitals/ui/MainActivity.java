package com.example.android.quizcapitals.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.quizcapitals.R;

public class MainActivity extends AppCompatActivity {

    private static final String USER_NAME_PATTERN = "[\\w.-]{1,20}";
    private EditText mUserNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserNameInput = findViewById(R.id.ed_user_name);
    }

    public void startQuizGame(View v) {
        String userName = mUserNameInput.getText().toString();

        if (isUserNameNotCorrect(userName)) {
            Toast.makeText(this, R.string.input_name_error_msg, Toast.LENGTH_LONG).show();
            return;
        }

        Intent startQuizIntent = new Intent(this, QuizActivity.class);
        startQuizIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startQuizIntent.putExtra(QuizActivity.USER_NAME_EXTRA, userName);
        startActivity(startQuizIntent);
    }

    private boolean isUserNameNotCorrect(String userName) {
        return userName.isEmpty() || !userName.matches(USER_NAME_PATTERN);
    }
}
