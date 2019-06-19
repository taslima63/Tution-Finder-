package com.example.odhimatrik.tuitionfinder;

/**
 * Created by ODHIMATRIK on 11/13/2017.
 */

public class TuitionInformation {


    public String location1;
    public String class1;
    public String subject1;
    public String salary1;
    public String week_days1;
    public String contact1;
    public String tid1;
    public String mail1;
    public TuitionInformation(){

    }

    public TuitionInformation(String tid1, String location1, String class1, String subject1, String salary1, String week_days1, String contact1, String mail1) {
        this.location1 = location1;
        this.tid1 = tid1;
        this.class1 = class1;
        this.subject1 = subject1;
        this.salary1 = salary1;
        this.week_days1 = week_days1;
        this.contact1 = contact1;
        this.mail1 =mail1;
    }

    public String getLocation1() {
        return location1;
    }

    public String getClass1() {
        return class1;
    }

    public String getSubject1() {
        return subject1;
    }

    public String getSalary1() {
        return salary1;
    }

    public String getWeek_days1() {
        return week_days1;
    }

    public String getContact1() {
        return contact1;
    }

    public String getTid1() {
        return tid1;
    }

    public String getMail1() { return mail1; }
}
