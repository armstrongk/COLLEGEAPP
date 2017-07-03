package com.example.armstrong.college.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.armstrong.college.LoginActivity;
import com.example.armstrong.college.R;
import com.example.armstrong.college.RemainderActivity;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            Intent intent1 = new Intent(PrivacyPolicyActivity.this, LoginActivity.class);
            startActivity(intent1);
           // onBackPressed();
           // return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
