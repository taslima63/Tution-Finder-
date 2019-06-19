package com.example.odhimatrik.tuitionfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Searching extends AppCompatActivity {
    EditText LocSearch;
    String locSearch1;
    Button ref_to_tuilist1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_searching);
        LocSearch= (EditText)findViewById(R.id.locSearch);

        ref_to_tuilist1= (Button)findViewById(R.id.ref_to_tuilist);

        ref_to_tuilist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lets_convert();
                Intent intent= new Intent(Searching.this,tuition_lists.class );
                intent.putExtra("key", locSearch1);
                startActivity(intent);
            }
        });
    }
    public void lets_convert(){
        locSearch1=  LocSearch.getText().toString();

    }
}
