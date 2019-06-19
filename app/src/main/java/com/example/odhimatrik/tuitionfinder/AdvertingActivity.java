package com.example.odhimatrik.tuitionfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdvertingActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects

    DatabaseReference databaseReference;
    EditText _location, _class1, _salary, _subjects, _weekly_days, _contact;
    Button buttonsave1, buttonLogout1;
    String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adverting);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
        mail = firebaseAuth.getCurrentUser().getEmail().toString();

        databaseReference = FirebaseDatabase.getInstance().getReference("tuitionposts");
        _location= (EditText) findViewById(R.id.Elocation);
        _class1= (EditText) findViewById(R.id.Eclass);
        _salary= (EditText) findViewById(R.id.Esalary);
        _subjects= (EditText) findViewById(R.id.Esubjects);
        _weekly_days= (EditText) findViewById(R.id.Eweek);
        _contact= (EditText) findViewById(R.id.Econtact);
        buttonsave1 = (Button) findViewById(R.id.submitinfo);
        buttonLogout1 = (Button) findViewById(R.id.buttonLogout1);

        //getting current user
        //FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        //buttonLogout = (Button) findViewById(R.id.buttonLogout1);

        //adding listener to button
        buttonLogout1.setOnClickListener(this);
        buttonsave1.setOnClickListener(this);
    }
    private void saveTuitionInfo(){
        String location1 =_location.getText().toString();
        String class1 =_class1.getText().toString();
        String subject1 =_subjects.getText().toString();
        String salary1 =_salary.getText().toString();
        String week_day1 =_weekly_days.getText().toString();
        String contact1 =_contact.getText().toString();

        FirebaseUser user= firebaseAuth.getCurrentUser();
        if(!TextUtils.isEmpty(location1)&&!TextUtils.isEmpty(class1) &&!TextUtils.isEmpty(subject1)){
            String tid1= databaseReference.push().getKey();
            TuitionInformation tuitionInformation = new TuitionInformation(tid1,location1, class1, subject1, salary1, week_day1, contact1,mail);
            databaseReference.child(tid1).setValue(tuitionInformation);
            Toast.makeText(this, "Tuition Advertisement Posted...", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(getApplicationContext(), Comm_inter.class));
        }
        else{
            Toast.makeText(this, "one or more textfield missing", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout1){
            //logging out the user

            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
        if(view==buttonsave1){
            saveTuitionInfo();
        }
    }


}
