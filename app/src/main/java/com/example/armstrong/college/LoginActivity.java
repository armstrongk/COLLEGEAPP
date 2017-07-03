package com.example.armstrong.college;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.armstrong.college.activity.StudentDrawer;
import com.example.armstrong.college.activity1.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends MainActivity {

    private EditText editEmail, editPassword;
    private FirebaseAuth mAuth;
    private String email, password;
    private CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        //For Signin
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        findViewById(R.id.btnSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_reset_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBoxRememberMe);
        //Here we will validate saved preferences
        if (!new PrefManager(this).isUserLogedOut()) {
            //user's email and password both are saved in preferences
            startHomeActivity();
        }

    }

    private void login() {
        if (isValidate()) {
            hideKeyboard();
            if (isNetworkAvailable()) {
                showProgress();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    hideProgress();
                                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (null != user) {
                                        Intent intent = new Intent(LoginActivity.this, StudentDrawer.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    hideProgress();
                                    Utility.alert(LoginActivity.this, null, task.getException().getMessage());
                                }
                            }
                        });

            } else
                showNetworkError();
        }
    }


    private void startHomeActivity() {
        Intent intent = new Intent(this, AdminRecylerCardView.class);
        startActivity(intent);
        finish();
    }

    private void saveLoginDetails(String email, String password) {
        new PrefManager(this).saveLoginDetails(email, password);
    }

    private boolean isValidate() {
        boolean isValidate = true;
        email = editEmail.getText().toString().trim();
        password = editPassword.getText().toString().trim();
        if (email.length() == 0) {
            editEmail.setError("Required Field");
            isValidate = false;
        }
        if (password.length() == 0) {
            editPassword.setError("Required Field");
            isValidate = false;
        }
        return isValidate;
    }


}
