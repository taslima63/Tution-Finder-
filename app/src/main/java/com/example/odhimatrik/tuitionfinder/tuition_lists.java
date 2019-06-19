package com.example.odhimatrik.tuitionfinder;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class tuition_lists extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ListView listViewposts;
    List<TuitionInformation> postlists;
    String gotLoc1;
    TextView tvSearch;
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuition_lists);
        databaseReference = FirebaseDatabase.getInstance().getReference("tuitionposts");

        firebaseAuth = FirebaseAuth.getInstance();
        mail = firebaseAuth.getCurrentUser().getEmail().toString();

        Bundle bundle = getIntent().getExtras();
        gotLoc1= bundle.getString("key");
        tvSearch= (TextView) findViewById(R.id.tvSloc);
        tvSearch.setText(gotLoc1);
        listViewposts= (ListView)findViewById(R.id.listviewposts);
        postlists= new ArrayList<>();
        listViewposts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TuitionInformation tuitionInformation = postlists.get(i);
                showUpdateDeleteDialog(tuitionInformation.getTid1(), tuitionInformation.getMail1());
                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postlists.clear();
                for(DataSnapshot tuitionSnapshot : dataSnapshot.getChildren()){
                    TuitionInformation tuitionInformation = tuitionSnapshot.getValue(TuitionInformation.class);
                    if(tuitionInformation.getLocation1().equals(gotLoc1))
                        postlists.add(tuitionInformation);
                }
                PostLists adapter = new PostLists(tuition_lists.this, postlists);
                listViewposts.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void updateTuition(String loc, String cls, String subs, String Wdays, String sal, String con, String tid, String Omail){
        DatabaseReference dR= FirebaseDatabase.getInstance().getReference("tuitionposts").child(tid);
        if(!Omail.equals(mail)){
            Toast.makeText(getApplicationContext(),"Unauthorized Update Attempt", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(getApplicationContext(), Searching.class));
        }
        else{
            TuitionInformation tuitionInformation= new TuitionInformation(tid,loc,cls,subs,sal,Wdays,con,mail);
            dR.setValue(tuitionInformation);
            //work to be done
            Toast.makeText(getApplicationContext(),"Tuition Updated", Toast.LENGTH_LONG).show();
        }
    }
    private void deleteTuition(String tid, String Omail){
        DatabaseReference dR= FirebaseDatabase.getInstance().getReference("tuitionposts").child(tid);
        if(!Omail.equals(mail)){
            Toast.makeText(getApplicationContext(),"Unauthorized Delete Attempt", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(getApplicationContext(), Searching.class));
        }
        else{
            dR.removeValue();
            Toast.makeText(getApplicationContext(),"Tuition Deleted", Toast.LENGTH_LONG).show();
        }
    }

    private void showUpdateDeleteDialog(final String id, final String ownerMail){
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.update_dailog,null);
        dialogbuilder.setView(dialogView);
        final EditText editNloc= (EditText) dialogView.findViewById(R.id.ETNloc);
        final EditText editNclass= (EditText) dialogView.findViewById(R.id.ETNclass);
        final EditText editNsub= (EditText) dialogView.findViewById(R.id.ETNsub);
        final EditText editNWD= (EditText) dialogView.findViewById(R.id.ETNWD);
        final EditText editNsalary= (EditText) dialogView.findViewById(R.id.ETNsalary);
        final EditText editNcontact= (EditText) dialogView.findViewById(R.id.ETNcontact);
        final Button btnUpdate= (Button) dialogView.findViewById(R.id.buttonUpdate);
        final Button btnDelete= (Button) dialogView.findViewById(R.id.buttonDelete);

        //dialogbuilder.setTitle(id);
        final AlertDialog b= dialogbuilder.create();
        b.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loc1=  editNloc.getText().toString().trim();
                String cls1=  editNclass.getText().toString().trim();
                String sub1=  editNsub.getText().toString().trim();
                String wd1=  editNWD.getText().toString().trim();
                String sal1=  editNsalary.getText().toString().trim();
                String con1=  editNcontact.getText().toString().trim();
                if(!TextUtils.isEmpty(loc1)){
                    updateTuition(loc1,cls1,sub1, wd1,sal1, con1,id, ownerMail);
                    b.dismiss();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteTuition(id, ownerMail);
                b.dismiss();
            }
        });
    }
}
