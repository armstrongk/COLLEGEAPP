package com.example.armstrong.college;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.armstrong.college.activity1.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignUpActivityAdmin extends MainActivity {

    private EditText editFirstName,  editSignUpEmail,editSchedule,editSex,editPosition, editMobileNumber, editSignUpPassword, editSignUpConfirmPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_admin);

        mAuth = FirebaseAuth.getInstance();

        //For Signup
        editFirstName = (EditText) findViewById(R.id.editFirstName);
        editSignUpEmail = (EditText) findViewById(R.id.editSignUpEmail);
        editSex = (EditText) findViewById(R.id.editSex);
        editPosition = (EditText) findViewById(R.id.editPosition);
        editSchedule = (EditText) findViewById(R.id.editSchedule);

        editMobileNumber = (EditText) findViewById(R.id.editMobileNumber);
        editSignUpPassword = (EditText) findViewById(R.id.editSignUpPassword);
        editSignUpConfirmPassword = (EditText) findViewById(R.id.editSignUpConfirmPassword);

        findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable())
                    signUp();
                else showNetworkError();
            }
        });


    }


    private void signUp() {

        if (isValidateSignUp()) {
            showProgress();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                final FirebaseUser userInfo = task.getResult().getUser();
                                if (userInfo != null) {

                                    mAuth.signInWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(SignUpActivityAdmin.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {

                                                        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
                                                        database = database.child(userInfo.getUid());

                                                        final UserModel userModel = new UserModel();
                                                        userModel.setFirst_name(firstName);
                                                        userModel.setEmail(email);
                                                        userModel.setSchedule(schedule);
                                                        userModel.setSex(sex);
                                                        userModel.setPosition(position);
                                                        userModel.setMobile_number(mobileNumber);
                                                        database.setValue(userModel);

                                                        database.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                hideProgress();
                                                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                                if (null != user) {
                                                                    Intent intent = new Intent(SignUpActivityAdmin.this, AdminDrawer.class);
                                                                    startActivity(intent);
                                                                    finishAffinity();
                                                                    Toast.makeText(SignUpActivityAdmin.this, "Your registration has been successfully done.", Toast.LENGTH_LONG).show();
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(DatabaseError databaseError) {
                                                                hideProgress();
                                                                Log.d("==>","==>"+databaseError);
                                                            }
                                                        });


                                                    } else {
                                                        hideProgress();
                                                        Utility.alert(SignUpActivityAdmin.this, null, task.getException().getMessage());
                                                    }
                                                }
                                            });


                                } else {
                                    hideProgress();
                                    Utility.alert(SignUpActivityAdmin.this, null, task.getException().getMessage());
                                }


                            } else {
                                hideProgress();
                                Utility.alert(SignUpActivityAdmin.this, null, task.getException().getMessage());
                            }
                        }

                    });

        }
    }

    private String firstName, schedule, email,position, sex,  mobileNumber, password,confirm_password;

    private boolean isValidateSignUp() {

        boolean isValidate = true;

        firstName = editFirstName.getText().toString().trim();
        email = editSignUpEmail.getText().toString().trim();
        sex = editSex.getText().toString().trim();
        position = editPosition.getText().toString().trim();
        schedule = editSchedule.getText().toString().trim();
        mobileNumber = editMobileNumber.getText().toString().trim();
        password = editSignUpPassword.getText().toString().trim();
        confirm_password = editSignUpConfirmPassword.getText().toString().trim();

        if (firstName.length() == 0) {
            editFirstName.setError("Required Field");
            isValidate = false;
        }
        if (position.length() == 0) {
            editPosition.setError("Required Field");
            isValidate = false;
        }

        if (email.length() == 0) {
            editSignUpEmail.setError("Required Field");
            isValidate = false;
        } else if (!Utility.isValidEmail(email)) {
            editSignUpEmail.setError("Please enter valid Email Id.");
            isValidate = false;
        }

        if (schedule.length() == 0) {
            editSchedule.setError("Required Field");
            isValidate = false;}

        if (sex.length() == 0) {
            editSex.setError("Required Field");
            isValidate = false;}




        if (mobileNumber.length() == 0) {
            editMobileNumber.setError("Required Field");
            isValidate = false;
        }

        if (password.length() == 0) {
            editSignUpPassword.setError("Required Field");
            isValidate = false;
        } else if (password.length() < 8) {
            editSignUpPassword.setError("Please enter password with minimum 8 characters");
            isValidate = false;
        }

        if (confirm_password.length() == 0) {
            editSignUpConfirmPassword.setError("Required Field");
            isValidate = false;
        } else if (!password.equals(confirm_password)) {
            editSignUpConfirmPassword.setError("Confirm Password do not match.");
            isValidate = false;
        }
        return isValidate;
    }


}