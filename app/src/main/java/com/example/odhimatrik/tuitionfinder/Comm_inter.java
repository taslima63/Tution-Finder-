package com.example.odhimatrik.tuitionfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Comm_inter extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    Button ref_to_tutor, ref_to_prov, ref_to_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_inter);
        firebaseAuth = FirebaseAuth.getInstance();
        ref_to_prov= (Button)findViewById(R.id.ref_to_prov);
        ref_to_tutor= (Button)findViewById(R.id.ref_to_tutor);
        ref_to_logout= (Button)findViewById(R.id.ref_to_logout);

        ref_to_prov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent= new Intent(getApplicationContext(),AdvertingActivity.class );
                startActivity(intent);
            }
        });
        ref_to_tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent= new Intent(getApplicationContext(),Searching.class );
                startActivity(intent);
            }
        });
        ref_to_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                //closing activity
                finish();
                Intent intent= new Intent(getApplicationContext(),MainActivity.class );
                startActivity(intent);
            }
        });
    }
}
