package com.example.armstrong.college.FireBaseMessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.armstrong.college.R;
import com.firebase.client.Firebase;

import java.util.Calendar;

public class Edit extends AppCompatActivity {
EditText txt1,txt2,txt3,txt4;
    Button btn1,btn2;
    String count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Firebase.setAndroidContext(this);
        txt1=(EditText)findViewById(R.id.prodid);
        txt2=(EditText)findViewById(R.id.pnames);
        txt3=(EditText)findViewById(R.id.pprice);
        txt4=(EditText)findViewById(R.id.hind);
        btn1=(Button) findViewById(R.id.update);
        btn2=(Button) findViewById(R.id.delete);

        txt1.setText(getIntent().getStringExtra("pid"));
        txt2.setText(getIntent().getStringExtra("name"));
        txt3.setText(getIntent().getStringExtra("price"));
        String count2=getIntent().getStringExtra("count");
         txt4.setText(getIntent().getStringExtra("count"));
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txt1!=null && txt2!=null && txt3!=null && txt4!=null){
                    String txt=txt4.getText().toString().trim();
                    String test=txt.replaceFirst ("^0*", "");
                    Firebase m_objFireBaseRef = new Firebase(Config.FIREBASE_PRODUCTS_URL3);
                    Firebase objRef = m_objFireBaseRef.child("programs");
                    objRef.child("program"+test).child("price").setValue(txt3.getText().toString().trim());
                    objRef.child("program"+test).child("pid").setValue(txt1.getText().toString().trim());
                    objRef.child("program"+test).child("name").setValue(txt2.getText().toString().trim());
                    objRef.child("program"+test).child("edittedat").setValue(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));

                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt=txt4.getText().toString().trim();
                String test=txt.replaceFirst ("^0*", "");
                Firebase m_objFireBaseRef = new Firebase(Config.FIREBASE_PRODUCTS_URL4);
                Firebase objRef = m_objFireBaseRef.child("programs");
                objRef.child("program"+test).removeValue();
            }
        });
    }
}
