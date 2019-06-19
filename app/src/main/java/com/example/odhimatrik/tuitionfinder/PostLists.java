package com.example.odhimatrik.tuitionfinder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ODHIMATRIK on 11/13/2017.
 */

public class PostLists extends ArrayAdapter<TuitionInformation> {
    private Activity context;
    private List<TuitionInformation> postlist;
    public PostLists(Activity context, List<TuitionInformation> postlist){
        super(context, R.layout.list_layout,postlist);
        this.context=context;
        this.postlist=postlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.list_layout,null,true);
        TextView textLoc= (TextView) listViewItem.findViewById(R.id.tvloc);
        TextView textclass= (TextView) listViewItem.findViewById(R.id.tvclass);
        TextView textsub= (TextView) listViewItem.findViewById(R.id.tvsubjects);
        TextView textWdays= (TextView) listViewItem.findViewById(R.id.tvweekdays);
        TextView textsalary= (TextView) listViewItem.findViewById(R.id.tvsalary);
        TextView textcontact= (TextView) listViewItem.findViewById(R.id.tvcontact);
        //TextView textmail= (TextView) listViewItem.findViewById(R.id.tvmail);
        TuitionInformation tuitionInformation= postlist.get(position);
        textLoc.setText("Location: "+ tuitionInformation.getLocation1());
        textclass.setText("Class: "+tuitionInformation.getClass1());
        textsub.setText("Subjects: "+tuitionInformation.getSubject1());
        textWdays.setText("Weekly Days: "+tuitionInformation.getWeek_days1());
        textsalary.setText("Salary: "+tuitionInformation.getSalary1());
        textcontact.setText("Contact: "+tuitionInformation.getContact1());
        //textmail.setText("Mail: "+tuitionInformation.getMail1());
        return listViewItem;
    }
}
