package com.todor.lunchmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.PushService;
import com.todor.lunchmanager.ParseService;
import com.todor.lunchmanager.R;
import com.todor.lunchmanager.callback.FindUserListener;


public class MainActivity extends AppCompatActivity implements FindUserListener {
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.enableLocalDatastore(this);
        Parse.initialize(MainActivity.this, "7Uolb4eMmY1z8QhGbqG96HIpCfNq0EsHEAi8WEpr", "gfTpyuVtoOAZ4zbNNJKAQ7UhXRbF4SwcgvFn7bP6");
        PushService.setDefaultPushCallback(this, MainActivity.class);

        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.userEmail);
        password = (EditText) findViewById(R.id.userPassword);
        Button registrationButton = (Button) findViewById(R.id.registration);
        Button signIn = (Button) findViewById(R.id.singIn);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistationActivity.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("") | password.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Email or password is empty", Toast.LENGTH_SHORT).show();
                }
                ParseService.getInstance().findUser(email.getText().toString(), password.getText().toString(),
                        MainActivity.this);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    @Override
    public void getUserSuccess(ParseObject object) {
        Intent intent = new Intent(MainActivity.this, CabinetActivity.class);
        intent.putExtra("email", email.getText().toString());
        startActivity(intent);
    }

    @Override
    public void getUserError(ParseException e) {
        Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
    }
}
