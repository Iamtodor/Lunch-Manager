package com.todor.lunchmanager.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.todor.lunchmanager.MyDialog;
import com.todor.lunchmanager.R;

/**
 * Created by todor on 23.07.15.
 */
public class RegistationActivity extends AppCompatActivity implements MyDialog.CustomDialog {

    EditText email;
    EditText name;
    EditText password;
    Button addButton;
    Button singIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        email = (EditText) findViewById(R.id.userEmail);
        name = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.userPassword);
        addButton = (Button) findViewById(R.id.addRegistrationButton);
        singIn = (Button) findViewById(R.id.singIn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                hideSoftKeyboard(RegistationActivity.this);
            }
        });

        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showDialog() {
        MyDialog dialog = MyDialog.newInstance("Registration?");
        dialog.show(getFragmentManager(), "Fragment");
    }

    @Override
    public void onButtonClick(String nameButton, Dialog dialog) {
        if(nameButton.equals("Yes")) {
            String emailStr = email.getText().toString();
            String userName = emailStr.substring(0, emailStr.lastIndexOf("@"));
            System.out.println(userName);
            String nameStr = name.getText().toString();
            String passwordStr = password.getText().toString();
            if(!isValidEmail(emailStr) | nameStr.equals("") | passwordStr.length() < 6)
                Toast.makeText(RegistationActivity.this, "Input correct data", Toast.LENGTH_SHORT).show();
            else {
                ParseObject person = new ParseObject("Person");
                person.put("email", emailStr);
                person.put("name", nameStr);
                person.put("password", passwordStr);
                person.saveInBackground();
                Toast.makeText(RegistationActivity.this, "Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if(nameButton.equals("No")) {
            dialog.dismiss();
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
